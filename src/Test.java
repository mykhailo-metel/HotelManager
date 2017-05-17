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
    }
}
