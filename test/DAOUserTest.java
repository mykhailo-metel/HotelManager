import DAO.DAO;
import DAO.DAOUser;
import models.User;
import org.junit.Test;

public class DAOUserTest {

    @Test
    public void checkHowUserIsAddedToListAndCanBeAccessed() throws Exception {
        DAO<User> daoUser = new DAOUser("users.txt");
        User user0 = new User(1,"N1","S1", "L1", "admin");
        User user1 = new User(2,"N2","S2", "L2", "user");
        User user2 = new User(3,"N3","S3", "L3", "user");
        User user3 = new User(4,"N4","S4", "L4", "user");
        daoUser.add(user0);
        daoUser.add(user1);
        daoUser.add(user2);
        daoUser.add(user3);
       assert daoUser.findByID(1).getLogin().equals("L1");
       assert daoUser.findByID(1).getName().equals("N1");
       assert daoUser.findByID(1).getSurname().equals("S1");
       assert daoUser.findByID(1).getUserRights().equals("admin");

        assert daoUser.findByID(2).getLogin().equals("L2");
        assert daoUser.findByID(2).getName().equals("N2");
        assert daoUser.findByID(2).getSurname().equals("S2");
        assert daoUser.findByID(2).getUserRights().equals("user");
    }

    @Test
    public void checkIfUserIsPresentAlready() throws Exception {
        DAO<User> daoUser = new DAOUser("users.txt");
        User user0 = new User(1,"N1","S1", "L1", "admin");
        User user1 = new User(2,"N2","S2", "L2", "user");
        User user2 = new User(3,"N3","S3", "L3", "user");
        User user3 = new User(4,"N4","S4", "L4", "user");
        daoUser.add(user0);
        daoUser.add(user1);
        daoUser.add(user2);
        daoUser.add(user3);

        assert daoUser.getAll().size() == 4; // As we try to add repeating users their number shouldnot be encreased

    }


    @Test
    public void loadUsersFromFile() throws Exception {
        DAO<User> userDAO = new DAOUser("users.txt");
        assert userDAO.getAll().size() == 4;

        assert userDAO.findByID(1).getLogin().equals("L1");
        assert userDAO.findByID(1).getName().equals("N1");
        assert userDAO.findByID(1).getSurname().equals("S1");
        assert userDAO.findByID(1).getUserRights().equals("admin");

        assert userDAO.findByID(2).getLogin().equals("L2");
        assert userDAO.findByID(2).getName().equals("N2");
        assert userDAO.findByID(2).getSurname().equals("S2");
        assert userDAO.findByID(2).getUserRights().equals("user");

        if(userDAO.getAll().size() == 4) {
            System.out.println("User was created from line successully");
        };
    }

    @Test
    public void editUserData() throws Exception {

    }

    @Test
    public void deleteUserData() throws Exception {
        DAO<User> userDAO = new DAOUser("users.txt");
        assert userDAO.getAll().size() == 4;
        User user0 = new User(1,"N1","S1", "L1", "admin");

    }

}