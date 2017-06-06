package DAO;

import models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class DAOUser extends DaoAbstract {

    public DAOUser(String filename){
        super(filename);
    }

    /**
     * Parses user from a string.
     * @param   line tab-delimited string.
     * @return  user
     */
    protected User parseLine(String line){
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
}
