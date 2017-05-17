import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Vlad on 17.05.2017.
 */
public class DAOUser {

    private String CURRENT_DIR = System.getProperty("user.dir")+"/db/";
    private String USER_FILE_PATH = CURRENT_DIR+"Users.tsv";

    /**
     * Truncates Users table
     */
    void truncateUserDB(){
        File f = new File(USER_FILE_PATH);
        if(f.exists()) f.delete();
    }

    /**
     * Writes all users into a table
     * @param users list of users to be written into DB
     */
    void writeUsers(List<User> users){
        Function<User,String> flattenUser = user -> Integer.toString(user.getId())+"\t"
                +user.getName()+"\t"
                +user.getSurname()+"\t"
                +user.getLogin()+"\t"
                +user.getUserRights()+"\n";

        try{
            File f = new File(USER_FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            for(String user : users.stream().map(flattenUser).collect(Collectors.toList())){
                writer.write(user);
            }
            writer.close();
        } catch (IOException ex)
        {
            throw new Error("Can't initialize the DB");
        }
    }

    /**
     * Reads users list from the DB.
     * @return list of users from Users table
     */
    List<User> readUsers(){
        File f = new File(USER_FILE_PATH);
        String line;
        List<User> users = new ArrayList<>();
        BufferedReader reader = null;
        User tempUser;
        try{
            reader = new BufferedReader(new FileReader(f));
            while((line = reader.readLine()) != null){
                tempUser=parseLine(line);
                if(tempUser==null){
                    break;
                }
                users.add(tempUser);
            }
        } catch (IOException ex){
            throw new Error("Can't read DB!");
        } finally {
            try{
                if (reader != null)
                    reader.close();
            }catch (IOException ex){
                System.out.println("Can't close connection to Users DB.");;
            }
        }
        return users;
    }

    /**
     * Parses user from a string.
     * @param   line tab-delimited string.
     * @return  user
     */
    private User parseLine(String line){
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
