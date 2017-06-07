package Services;

import DAO.DAOBooking;
import models.Booking;
import models.Room;

import java.util.Date;
import java.util.List;

public class BookingService {
    private DAOBooking daoBooking;

    public BookingService(DAOBooking daoBooking){
        this.daoBooking = daoBooking;
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
        List<Booking> bookingsOfRoom = daoBooking.findByRoomId(roomId);

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
