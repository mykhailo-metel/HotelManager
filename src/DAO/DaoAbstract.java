package DAO;

import models.BaseModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract  class DaoAbstract<E extends BaseModel> implements DAO<E> {
    protected String CURRENT_DIR = System.getProperty("user.dir") + "\\db\\";
    protected String FILE_PATH;
    protected List<E> list = new ArrayList<>(0);

    DaoAbstract(String filename){
        FILE_PATH = CURRENT_DIR + filename;
        list = loadFromDB();
    }

    /**
     * Truncates table
     */
    public void truncateDB(){
        File f = new File(FILE_PATH);
        if(f.exists()) f.delete();
        if(list != null) {
            list = null;
        }
    }

    public E findByID(int id){
        if(list==null || list.size()==0) return null;
        return list.stream().filter(e->e.getId()==id).findFirst().orElse(null);
    }

    public List<E> getAll(){
        return list;
    }

    /**
     * Writes all items into a table
     */
    protected void saveToDB() {
        StringBuilder stringBuilder = new StringBuilder();

        try{
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i).StringForWritingToDB());
            }
            writer.write(stringBuilder.toString());
            writer.flush();
            writer.close();
        } catch (IOException ex)
        {
            throw new Error("Can't initialize the DB");
        }
    }

    /**
     * Reads items list from the DB.
     * @return list of users from Users table
     */
    public List<E> loadFromDB() {
        File f = new File(FILE_PATH);
        String line;
        List<E> items = new ArrayList<>();
        BufferedReader reader = null;
        E tempItem;

        try{
            if (!f.exists()){
                if(f.createNewFile()) {
                    System.out.println("File successfully created.");
                };
            }
            reader = new BufferedReader(new FileReader(f));
            while((line = reader.readLine()) != null){
                tempItem = parseLine(line);
                if(tempItem == null){
                    break;
                }
                items.add(tempItem);
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
        return items;
    }

    @Override
    public void add(E e){
        if (!list.stream().anyMatch((E o) -> e.getId() == o.getId())) {
            list.add(e);
            saveToDB();
        };
    }

    @Override
    public E update(E e) {
            E temp = findByID(e.getId());
            list.removeIf(o -> o.getId() == e.getId());
            list.add(e);
            saveToDB();
            return temp;
    }

    @Override
    public void delete(E e) {
        list.removeIf(o -> o.getId() == e.getId());
        saveToDB();
    }

    protected abstract E parseLine(String line);
    /**
     * Parses String line and creates a List for creating an object.
     * @param line line to parse (tab separated)
     * @return Line<String>
     */
    protected List<String> getListFromLins(String line){
        Scanner sc = new Scanner(line);
        sc.useDelimiter("\t");
        List<String> result = new ArrayList<>();

        while(sc.hasNext()){
            result.add(sc.next());
        }
        return result;
    }
}
