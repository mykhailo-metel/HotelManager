import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vlad on 17.05.2017.
 */
public class UserController {

    private List<User> userlList;
    private DAOUser daoUser = new DAOUser();
    private AtomicInteger maxId;

    public UserController() {
        this.userlList = daoUser.readUsers();
    }

    public void createUser(int id, String name, String surname, String login, String userRights){
//        User tempUser = new User()
    }
    public void deleteUser(int userId){}
    public void updateUser(int idToChange, Hotel newUser){}
    public Hotel getById(int userId){return null;}
    public Hotel getUserList(int userId){return null;}

}
