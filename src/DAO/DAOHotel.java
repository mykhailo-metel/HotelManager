package DAO;

import models.Hotel;
import java.util.List;


public class DAOHotel extends DaoAbstract {

    public DAOHotel(String path){
        super(path);
    }

    /**
     * Parses String line and creates a Hotel.
     * @param hotelLine line to parse (tab separated id\tcity\t\name)
     * @return Hotel object
     */
    @Override
    protected Hotel parseLine(String hotelLine){
        List<String> result;
        Hotel tempHotel=null;
        int id;
        String name;
        String city;

        try{
            result = getListFromLins(hotelLine);
            id = Integer.parseInt(result.get(0));
            city = result.get(1);
            name = result.get(2);
            tempHotel = new Hotel(name, city);
        }catch (Exception ex){
            System.out.println("Unable to parse hotel.");
        }
        return tempHotel;
    }
}
