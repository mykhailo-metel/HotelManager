import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlad on 17.05.2017.
 */
public class HotelController {

    private List<Hotel> hotelList;
    private DAOHotel daoHotel = new DAOHotel();

    public HotelController() {
        this.hotelList = daoHotel.readHotels();
    }

    public void createHotel(String city, String name){}
    public void deleteHotel(int hotelId){}
    public void updateHotel(int idToChange, Hotel newHotel){}
    public Hotel getById(int hotelId){return null;}
    public List<Hotel> getByCity(String city){return null;}
    public List<Hotel> getByName(String name){return null;}
    public Hotel gethotelList(int hotelId){return null;}
}
