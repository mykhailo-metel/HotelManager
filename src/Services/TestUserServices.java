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

    }

}
