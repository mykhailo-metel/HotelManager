import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Vlad on 17.05.2017.
 */
public class BookingController {

    List<Booking> bookings;
    DAOBooking daoBooking;
    AtomicLong maxId = new AtomicLong(0);
    public BookingController(List<Booking> bookings) {
        this.bookings = bookings;
        DAOBooking daoBooking = new DAOBooking();
    }

    public void addBooking(User user, Date dateBegin, Date dateEnd, Hotel hotel, Room room){};
    public void readBookings(){};
    public List<Booking> getBookings(){return null;}
    public List<Booking> getBookingsByHotel(int hotelId){return null;}
    public List<Booking> getBookingsByUser(int userId){return null;}
    public void removeBooking(long idBooking){}
    public void removeBookingByUser(long idUser){}
    public void removeBookingByRoom(long idRoom){}
    public void removeBookingByHotel(long idHotel){}
    private boolean checkAvailability(User user, Date dateBegin, Date dateEnd, Hotel hotel, Room room){return false;}
    private boolean checkAvailability(Booking booking){return false;}
}
