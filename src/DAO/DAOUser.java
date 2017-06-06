package DAO;

import models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class DAOUser extends DaoAbstract {

    public DAOUser(String filename){
        super(filename);
    }

    public void add(User user){
        if (containsLogin(user.getLogin())){
            super.add(user);
        }
    }


    /**
     * Check if list contains user with specified login
     * @param login - checked login
     * @return true if contains
     */
    public boolean containsLogin(String login) {
        boolean result = false;
        if (list.stream().anyMatch(e->((User)e).getLogin().equals(login))){
            result = true;
        }
        return result;
    }

    /**
     * Parses user from a string.
     * @param   line tab-delimited string.
     * @return  user
     */
    public User parseLine(String line){
        if(line.length()<3) return null;

        ArrayList<String> tempUser = new ArrayList<>();
        Scanner sc = new Scanner(line);
        sc.useDelimiter("\t");
        while(sc.hasNext()){
            tempUser.add(sc.next());
        }
        if(tempUser.size()==5){
            int id = Integer.parseInt(tempUser.get(0));
            String name = tempUser.get(1);
            String surname = tempUser.get(2);
            String login = tempUser.get(3);
            String userRights = tempUser.get(4);
            return new User(id,name,surname,login,userRights);
        }
        return null;
    }

    public User findByLogin(String login) {
        if (login == null || login.length() == 0) return null;
        return (User) list.stream().filter(e->login.equals(((User)e).getLogin())).findFirst().orElse(null);
    }
}
