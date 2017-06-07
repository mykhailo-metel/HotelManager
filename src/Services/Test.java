package Services;

import DAO.*;
import models.User;
import userInterfaceClasses.Requester;

public class Test {
    public static void main(String[] args) {
        DAO<User> userDAO = new DAOUser("users.txt");
        Test test = new Test();
        test.selectUser(userDAO);
    }

    public void selectUser(DAO<User> dao){
        Requester.select(dao);

    }
}
