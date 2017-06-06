import DAO.DAOHotel;
import models.Hotel;
import models.Room;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Vlad on 17.05.2017.
 */
public class HotelController {

    private List<Hotel> hotelList;
    private DAOHotel daoHotel = new DAOHotel();
    private AtomicInteger maxHotelId = new AtomicInteger(0);
    private AtomicInteger maxRoomID = new AtomicInteger(0);
    public HotelController() {
        this.hotelList = daoHotel.readHotels();
        if(!this.hotelList.isEmpty()){
            for (Hotel hotel : this.hotelList) {
                int hotlId=hotel.getId();
                int roomMaxId=hotel.getMaxRoomId();
                if(hotlId>maxHotelId.get()) maxHotelId.set(hotlId);
                if(roomMaxId>maxRoomID.get()) maxRoomID.set(roomMaxId);
            }
        }
    }

    /**
     * Checks if there such hotel in the list.
     * @param tempHotel hotel to be checked.
     * @return true or false.
     */
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
        Hotel tempHotel = new Hotel(maxHotelId.incrementAndGet(),city,name);
        if(checkHotelIfExists(tempHotel)){
            System.out.println("The hotel already exists.");
            return;
        }
        hotelList.add(tempHotel);
        daoHotel.truncateDB();
        daoHotel.writeHotels(this.hotelList);
    }

    /**
     * Adds a new room to the hotel.
     * @param hotelId id of a hotel to add a room.
     * @param person room capacity.
     * @param price price of the room for a day.
     */
    public void createRoom(int hotelId,byte person, BigDecimal price){
        for (int i = 0; i < hotelList.size(); i++) {
            if(hotelList.get(i).getId()==hotelId){
                hotelList.get(i).addRoom(new Room(maxRoomID.incrementAndGet(),person,price));
                break;
            }
        }
        daoHotel.truncateDB();
        daoHotel.writeHotels(this.hotelList);
    }

    /**
     *
     * @return An id of the last hotel in the hotels list.
     * @throws IllegalAccessException
     */
    public int getLastHotelId() throws IllegalAccessException {
        if(hotelList.isEmpty()) throw new IllegalAccessException("A list of hotels is empty.");
        return hotelList.get(hotelList.size()-1).getId();
    }

    /**
     * Adds an existing hotel. Must be used only for reading from DB.
     * @param hotelToAdd hotel to be add.
     */
    private void addHotel(Hotel hotelToAdd){
        if(checkHotelIfExists(hotelToAdd)){
            System.out.println("The hotel already exists.");
            return;
        }
        if(hotelToAdd.getId()>maxHotelId.get()){
            maxHotelId.set(hotelToAdd.getId());
        }
        hotelList.add(hotelToAdd);
        if(hotelToAdd.getMaxRoomId()>maxRoomID.get()){
            maxRoomID.set(hotelToAdd.getMaxRoomId());
        }
    }

    /**
     * Adds an existing hotels. Must be used only for reading from DB.
     * @param hotelsToAdd list of hotels to be add.
     */
    public void addHotels(List<Hotel> hotelsToAdd){
        for (Hotel hotel : hotelsToAdd) {
            addHotel(hotel);
        }
        daoHotel.truncateDB();
        daoHotel.writeHotels(this.hotelList);
    }

    /**
     * Removes a hotel from the hotels list
     * @param hotelId Id to be removed.
     */
    public void deleteHotel(int hotelId){
        hotelList.removeIf(hotel -> hotel.getId() == hotelId);
        daoHotel.truncateDB();
        daoHotel.writeHotels(this.hotelList);
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
        daoHotel.truncateDB();
        daoHotel.writeHotels(this.hotelList);
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
