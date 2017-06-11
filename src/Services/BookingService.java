package Services;

import DAO.DAO;
import DAO.DAOBooking;
import models.Booking;
import models.Hotel;
import models.Room;
import models.User;
import userInterfaceClasses.Requester;

import java.util.Date;
import java.util.List;

public class BookingService {
    private DAO<Booking> daoBooking;
    private DAO<Hotel> hotelDAO;
    private DAO<Room> roomDAO;
    private DAO<User> userDAO;
    private Session session;

    public BookingService(DAO<Booking> daoBooking, DAO<Hotel> daoHotel, DAO<Room> roomDAO, Session session){
        this.daoBooking = daoBooking;
        this.hotelDAO = daoHotel;
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.userDAO = userDAO;
    }

    public void makeBooking() {
        if(session.getSelectedUser().getUserRights().equals("admin")){
            Requester.select(userDAO);
        }
        String city = Requester.selectCity(hotelDAO);
        Date dateBegin = Requester.requestDate();
        Date dateEnd = Requester.requestDate();
        Hotel hotel = Requester.select(hotelDAO);
        Room room = Requester.selectRoom(hotel.getId(), roomDAO);
        if(checkBookingPossible(dateBegin, dateEnd, room.getId())) {
            if (Requester.requestConfirm(" сделать бронирование ")){
                daoBooking.add(new Booking(room.getId(), session.getSelectedUser().getId(),dateBegin,dateEnd));
            }
        }
    }


    /**
     * Checks if booking is possible. Returns false if some existing booking period of a room intersects
     * with desired booking period or starting date > ending date.
     * @param   dateBegin   starting date.
     * @param   dateEnd     ending date.
     * @param   roomId      room Id.
     * @return              boolean, true if booking is available.
     */
    public  boolean checkBookingPossible(Date dateBegin, Date dateEnd, int roomId){
        //Если дата начала и дата конца не попадают ни в один из существующих промежутков бронирования, вернуть true
        List<Booking> bookingsOfRoom = ((DAOBooking)daoBooking).findByRoomId(roomId);

        if(bookingsOfRoom == null || bookingsOfRoom.size() == 0) {
            return true;
        }
        for (Booking booking:bookingsOfRoom) {
            if((booking.getDateBegin().compareTo(dateBegin) >= 0) && (booking.getDateBegin().compareTo(dateEnd) <= 0)){
                return false;
            }
            if((booking.getDateEnd().compareTo(dateBegin) >= 0) && (booking.getDateEnd().compareTo(dateEnd) <= 0)){
                return false;
            }
            if(dateBegin.compareTo(dateEnd) > 0){
                return false;
            }
        }
        return true;
    }
}
