import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Vlad on 17.05.2017.
 */
public class HotelController {

    private List<Hotel> hotelList;
    private DAOHotel daoHotel = new DAOHotel();
    private AtomicInteger maxId = new AtomicInteger(0);

    public HotelController() {
        this.hotelList = daoHotel.readHotels();
    }


    private boolean checkHotelIfExists(Hotel tempHotel){
        for (Hotel hotel : hotelList) {
            if(hotel.equals(tempHotel)){
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a hotel and adds it to a list of hotels.
     * @param city City of the hotel's location
     * @param name Name of the hotel.
     */
    public void createHotel(String city, String name){
        Hotel tempHotel = new Hotel(maxId.incrementAndGet(),city,name);
        if(checkHotelIfExists(tempHotel)){
            System.out.println("The hotel already exists.");
            return;
        }
        hotelList.add(tempHotel);
    }

    /**
     * Adds an existing hotel. Must be used only for reading from DB.
     * @param hotelToAdd hotel to be add.
     */
    public void addHotel(Hotel hotelToAdd){
        if(checkHotelIfExists(hotelToAdd)){
            System.out.println("The hotel already exists.");
            return;
        }
        if(hotelToAdd.getId()>maxId.get()){
            maxId.set(hotelToAdd.getId());
        }
        hotelList.add(hotelToAdd);
    }

    /**
     * Adds an existing hotels. Must be used only for reading from DB.
     * @param hotelsToAdd list of hotels to be add.
     */
    public void addHotels(List<Hotel> hotelsToAdd){
        for (Hotel hotel : hotelsToAdd) {
            addHotel(hotel);
        }
    }

    /**
     * Removes a hotel from the hotels list
     * @param hotelId Id to be removed.
     */
    public void deleteHotel(int hotelId){
        hotelList.removeIf(hotel -> hotel.getId() == hotelId);
    }

    /**
     * Updates a hotel
     * @param idToChange id of the hotel to be updated
     * @param newHotel new object.
     */
    public void updateHotel(int idToChange, Hotel newHotel){
        for (int i = 0; i < hotelList.size(); i++) {
            if(hotelList.get(i).getId()==idToChange){
                hotelList.get(i).setCity(newHotel.getCity());
            }
            if(hotelList.get(i).getId()==idToChange){
                hotelList.get(i).setName(newHotel.getName());
            }
            if(!hotelList.get(i).getRooms().equals(newHotel.getRooms())){
                hotelList.get(i).setRooms(newHotel.getRooms());
            }
        }
    }

    /**
     * Finds a hotel by id.
     * @param hotelId id to be found
     * @return hotel or null if nothing found.
     */
    public Hotel getById(int hotelId){
        try{
            return hotelList.stream().filter(x -> x.getId()==hotelId).findFirst().get();
        } catch (Exception ex){
            System.out.println("No such hotel.");
        }
        return null;
    }

    /**
     * Finds all hotels in the city.
     * @param city city to look for.
     * @return list of hotels.
     */
    public List<Hotel> getByCity(String city){
        return hotelList.stream().filter(x -> x.getCity().equals(city)).collect(Collectors.toList());
    }

    /**
     * Finds a hotel by name in all cities.
     * @param name Name to be found.
     * @return list of hotels.
     */
    public List<Hotel> getByName(String name){
        return hotelList.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
    }

    /**
     * Returns all hotels.
     * @return all hotels.
     */
    public List<Hotel> gethotelList(){
        return hotelList;
    }


}
