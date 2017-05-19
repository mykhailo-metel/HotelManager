import java.util.List;

/**
 * Created by Vlad on 17.05.2017.
 */
public class UserController {

    private List<User> userlList;
    private DAOUser daoUser = new DAOUser();

    public UserController() {
        this.userlList = daoUser.readUsers();
    }

    public void createUser(int id, String name, String surname, String login, String userRights){}
    public void deleteUser(int userId){}
    public void updateUser(int idToChange, Hotel newUser){}
    public Hotel getById(int userId){return null;}
    public Hotel getUserList(int userId){return null;}

}
