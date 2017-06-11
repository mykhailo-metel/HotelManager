package Services;


import DAO.*;
import DAO.DAOUser;
import models.Booking;
import models.User;
import userInterfaceClasses.Requester;


public class UserServices {

    private DAO<User> daoUser;
    private Session session;
    private DAO<Booking> daoBooking;

    public UserServices(DAO<User> daoUser, DAO<Booking> daoBooking, Session session){
        this.daoUser = daoUser;
        this.daoBooking = daoBooking;
        this.session = session;
    }

    public void login() {
        String login;
        User user;

        System.out.println("Введите логин (и нажмите Enter)");

        try {
            login = Requester.requestName();
            user = ((DAOUser)daoUser).findByLogin(login);
            if (user == null) {
                System.out.println("Такого пользователя нет в системе");
                return;
            }
            session.setLoggedUser(user);
        }catch (RuntimeException e) {
            System.out.println("Ошибка при входе в систему");
        }
        System.out.println("Добро пожаловать " + session.getLoggedUser().toString() + "\n");
    }

    public void registerUser(){
        String login;

        System.out.println("Введите логин");
        try{
            login = Requester.requestName();
            if (((DAOUser)daoUser).containsLogin(login)){
                System.out.println("Такой логин уже зарегистрирован");
                return;
            }
            System.out.println("Введите Имя");
            String firstName = Requester.requestName();

            System.out.println("Введите Фамилию");
            String lastName = Requester.requestName();

            User user = new User(firstName,lastName,login,"user");
            daoUser.add(user);
            System.out.println("Пользователь " + ((DAOUser) daoUser).findByLogin(login).toString() + "  успешно зарегистрирован\n");
        } catch (RuntimeException e){
            System.out.println(e);
            System.out.println("Ошибка регистрации пользователя\n");
        }
    }

    public void editUserData(User user) {
        try {
            System.out.println("Изменение данных пользователя\n" + user.toString());
            System.out.println("Введите новое Имя");
            user.setName(Requester.requestName());
            System.out.println("Введите новую Фамилию");
            user.setSurname(Requester.requestName());
            daoUser.update(user);
            System.out.println("Изменение данных прошло успешно. Новые данные:");
            System.out.println(user.toString() + "\n");
        }catch (RuntimeException e){
            System.out.println("Ошибка при изменении данных пользователя\n");
        }
    }

    public void editSelectedUser() {
        System.out.println("Изменение данных пользователя");
        User user = Requester.select(daoUser);
        if (user == null){return;}
        editUserData(user);
    }

    public void deleteUser(User user) {
        System.out.println("Удаление пользователя приведет к удалению всех его бронирований. Вы уверены?");
        if (Requester.requestConfirm("удаление")){
            ((DAOBooking)daoBooking).findByUserID(user.getId()).forEach(b->daoBooking.delete(b));
            System.out.println("Бронирования пользователя удалены.");
            daoUser.delete(user);
            System.out.println("Пользователь " + user + " удален\n");
        };

    }

    public void deleteSelectedUser() {
        System.out.println("Удвление данных пользователя");
        User user = Requester.select(daoUser);
        if (user == null){return;}
        deleteUser(user);
    }
}
