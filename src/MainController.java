import models.Booking;
import models.Hotel;

import java.util.List;

/**
 * Created by Vlad on 19.05.2017.
 */
public class MainController {

    //Класс нужен, чтобы объединить данные пользователя, брони и отеля
    //например, поиск броней по отелю и пользователю, удаление брони при уделении пользователя или комнаты
    //и т.д.

    UserController userController = new UserController();
    HotelController hotelController = new HotelController();
    BookingController bookingController = new BookingController(null);

    public void createHotel(){}
    public void deleteHotel(){}
    public void updateHotel(){}
    public void createRoom(){}
    public void deleteRoom(){}
    public void updateRoom(){}
    public void createUser(){}
    public void deleteUser(){}
    public void updateUser(){}
    public void createBooking(){}
    public void deleteBooking(){}
    public void updateBooking(){}
    public List<Hotel> getHotelsByCity(){return null;}
    public List<Hotel> getHotelsByName(){return null;}
    public List<Booking> getBookingsByHotel(){return null;}
    public List<Booking> getBookingsByRoom(){return null;}
    public List<Booking> getBookingsByUser(){return null;}
}
