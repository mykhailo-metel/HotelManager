import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 17.05.2017.
 */
public class Test {
    public static void main(String[] args) {
        User user1 = new User(1,"Ivan","Svitek","IvSv", "Admin");
        User user2 = new User(2,"Steven","Spielberg","StSp", "User");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        DAOUser dao = new DAOUser();

        dao.truncateUserDB();
        dao.writeUsers(users);
        users=null;
        users=dao.readUsers();
        for (User user : users) {
            System.out.println(user);
        }

        Room room1 = new Room(1,(byte)1, new BigDecimal(100));
        Room room2 = new Room(2,(byte)2, new BigDecimal(200));
        Room room3 = new Room(3,(byte)2, new BigDecimal(400));
        Room room4 = new Room(4,(byte)3, new BigDecimal(500));
        Room room5 = new Room(5,(byte)4, new BigDecimal(300));

        Hotel hotel1 = new Hotel(1,"Kiev","Hayatt");
        Hotel hotel2 = new Hotel(2,"Moskow","Royal");

        hotel1.addRoom(room1);
        hotel1.addRoom(room2);
        hotel2.addRoom(room3);
        hotel2.addRoom(room4);
        hotel2.addRoom(room5);

        System.out.println(hotel1);
        System.out.println(hotel2);

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel1);
        hotels.add(hotel2);

        DAOHotel daoHotel = new DAOHotel();
        daoHotel.truncateDB();
        daoHotel.writeHotels(hotels);
        System.out.println("######################################");
        List<Hotel> newHotelsList = daoHotel.readHotels();
        for (Hotel hotel : newHotelsList) {
            System.out.println(hotel);
        }


    }
}
