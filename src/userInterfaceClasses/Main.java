package userInterfaceClasses;

import models.Hotel;
import models.Room;
import models.User;

public class Main {

    private DAO.DaoAbstract<User> userDAO = new DAO.DAOUser("users.tsv");
    private DAO.DaoAbstract<Hotel> hotelDAO = new DAO.DAOHotel("hotel.tsv");
    private DAO.DaoAbstract<Room> roomDAO = new DAO.DAORoom("hotel.tsv");

    public void run(){
        int choise;
        System.out.println(
                "Главное меню" +
                "\n1. Логин" +
                "\n2. Регистрация" +
                "\n0. Выход");
        do {
            choise = Requester.requestInt();
            switch (choise) {
                case 1:
                    //mainLogin.run();
                    break;
                case 2:
                    //mainRegister.run();
                    break;
            }
        } while (choise != 0);
    }
}
