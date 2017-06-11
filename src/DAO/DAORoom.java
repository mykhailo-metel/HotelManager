package DAO;

import models.Room;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DAORoom extends DaoAbstract{
    private Map<Integer, List<Integer>> hotelRoomMap = new HashMap<>(0);

    public DAORoom(String path){
        super(path);
    }

    public void add(Room room){
        super.add(room);
        if (hotelRoomMap.get(room.getId()) == null){
            List<Integer> temp = new ArrayList<>(0);
            temp.add(room.getId());
            hotelRoomMap.put(room.getId(), temp);
        }
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
        int person;
        BigDecimal price;

        try{
            result = getListFromLins(line);
            id = Integer.parseInt(result.get(0));
            hotelID = Integer.parseInt(result.get(1));
            person = Integer.parseInt(result.get(2));
            price = new BigDecimal(result.get(3));
            tempRoom = new Room(id, hotelID, person,price);
        }catch (Exception ex){
            System.out.println("Unable to parse room.");
        }
        return tempRoom;
    }

    public List<Integer> findRoomsByHotelId(int hotelID) {
        return hotelRoomMap.get(hotelID);
    }
}
