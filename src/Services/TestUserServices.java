package Services;

import DAO.DAO;
import DAO.DAOBooking;
import DAO.DAOUser;
import models.Booking;
import models.User;

public class TestUserServices {
    public static void main(String[] args) {
        DAO<User> userDAO = new DAOUser("users.txt");
        DAO<Booking> bookingDAO = new DAOBooking("bookings.txt");
        Session session = new Session();
        UserServices userServices = new UserServices(userDAO, bookingDAO, session );

        //userServices.login();
        //System.out.println(session.getLoggedUser());
        //userServices.registerUser();
        //userServices.editUserData(userDAO.findByID(1));
        //userServices.editSelectedUser();
        userServices.deleteSelectedUser();
        userServices.deleteSelectedUser();
    }

}
