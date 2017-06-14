package Services;

import DAO.*;
import DAO.DAOHotel;
import DAO.DAORoom;
import models.Booking;
import models.Hotel;
import models.Room;

public class TestHotelService {
    public static void main(String[] args) {
        DAO<Hotel> hotelDAO = new DAOHotel("hotels.txt");
        DAO<Room> roomDAO = new DAORoom("rooms.txt");
        DAO<Booking> bookingDAO = new DAOBooking("bookngs.txt");

        HotelAndRoomsServices hrs = new HotelAndRoomsServices(hotelDAO, roomDAO, bookingDAO);
        /*hrs.addNewHotelWithRooms();
        hrs.editHotelName();*/
        hrs.deleteRoom();
    }
}
