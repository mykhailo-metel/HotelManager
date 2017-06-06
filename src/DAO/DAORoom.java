package DAO;

import models.Room;

import java.math.BigDecimal;
import java.util.List;


public class DAORoom extends DaoAbstract{

    public DAORoom(String path){
        super(path);
    }

    /**
     * Parses String line and creates a Hotel.
     * @param line line to parse (tab separated id\tcity\t\name)
     * @return Hotel object
     */
    @Override
    protected Room parseLine(String line){
        List<String> result;
        Room tempRoom = null;

        int id;
        int hotelID;
        byte person;
        BigDecimal price;

        try{
            result = getListFromLins(line);
            id = Integer.parseInt(result.get(0));
            hotelID = Integer.parseInt(result.get(1));
            person = Byte.parseByte(result.get(2));
            price = new BigDecimal(result.get(3));
            tempRoom = new Room(id, hotelID, person,price);
        }catch (Exception ex){
            System.out.println("Unable to parse room.");
        }
        return tempRoom;
    }
}
