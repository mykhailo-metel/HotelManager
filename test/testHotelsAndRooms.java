import DAO.DAOHotel;
import DAO.DAORoom;
import DAO.DAOUser;
import Services.Session;
import org.junit.Test;

public class testHotelsAndRooms {
    @Test
    public void testRunningAll(){
        DAOHotel daoHotel = new DAOHotel("hotels.txt");
        DAORoom daoRoom = new DAORoom("rooms.txt");
        DAOUser daoUser = new DAOUser("users.txt");

        Session session = new Session();
    }
}
