package Services;

import DAO.DAOUser;
import models.User;

import java.util.Scanner;

public class Session {
    DAOUser daoUser;
    private User loggedUser;
    private User selectedUser;

    public Session(DAOUser daoUser){
        this.daoUser = daoUser;
    }

    /**
     * Метод для входа пользователя в систему. Пользователю предлагается ввести уникальный логин. В случае если человек
     * с таким логином имеет права администратора, вызывается меню администратора, в противном случае - меню клиента.
     */
    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите логин");
        try{
            String login = scanner.next();

            if (daoUser.containsLogin(login)) {
                setLoggedUser(daoUser.findByLogin(login));
                System.out.println("Добро пожаловать " + getLoggedUser());
            }
            else {
                System.out.println("Несущствующий логин.");
            }
        }catch (RuntimeException e) {
            System.out.println(e + "Ошибка при входе в систему");
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}
