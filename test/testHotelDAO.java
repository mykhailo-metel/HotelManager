import DAO.*;
import models.Hotel;
import org.junit.Test;

import java.util.Arrays;

public class testHotelDAO {

    @Test
    public void testAddHotel(){
        DAO<Hotel> hotelDAO = new DAOHotel("hotels.txt");
        hotelDAO.add(new Hotel("HN1","City1"));
        hotelDAO.add(new Hotel("HN2","City2"));
        hotelDAO.add(new Hotel("HN3","City3"));
        hotelDAO.add(new Hotel("HN4","City4"));

        Arrays.toString(hotelDAO.getAll().toArray());
    }

    @Test
    public void testReadingHotels() {
        DAO<Hotel> hotelDAO = new DAOHotel("hotels.txt");
        assert hotelDAO.getAll().size() == 4;
        Arrays.toString(hotelDAO.getAll().toArray());
    }
}
