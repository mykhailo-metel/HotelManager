import java.math.BigDecimal;
import java.util.*;
import java.io.*;
import java.util.function.Function;

/**
 * Created by Vlad on 17.05.2017.
 */
public class DAOHotel {
    private String CURRENT_DIR = System.getProperty("user.dir")+"/db/";
    private String HOTEL_FILE_PATH = CURRENT_DIR+"Hotel.tsv";
    private String ROOM_FILE_PATH = CURRENT_DIR+"Room.tsv";
    private String ROOM_HOTEL_MAP = CURRENT_DIR+"RoomHotelMap.tsv";

    /**
     * Writes data (current state of the system) to Hotel, Room and RoomHotel tables.
     * @param hotels a list of hotel objects.
     */
    public void writeHotels(List<Hotel> hotels){
        File hotelsFile = new File(HOTEL_FILE_PATH);
        File roomsFile = new File(ROOM_FILE_PATH);
        File roomHotelMapFile = new File(ROOM_HOTEL_MAP);

        List<String> hotelsStringList = new ArrayList<>();
        List<String> roomsStringList = new ArrayList<>();
        List<String> hotelRoomMapStringList = new ArrayList<>();

        for (Hotel hotel : hotels) {
            hotelsStringList.add(Integer.toString(hotel.getId())+"\t"
                                                +hotel.getCity()+"\t"
                                                +hotel.getName()+"\n");
            for (Room room : hotel.getRooms()) {
                roomsStringList.add(Integer.toString(room.getId())+"\t"
                                                +Byte.toString(room.getPerson())+"\t"
                                                +room.getPrice().toString()+"\n");

                hotelRoomMapStringList.add(Integer.toString(hotel.getId())+"\t"
                                            +Integer.toString(room.getId())+"\n");
            }
        }

        BufferedWriter writer = null;
        try{
            hotelsFile.createNewFile();
            roomsFile.createNewFile();
            roomHotelMapFile.createNewFile();
            writer=new BufferedWriter(new FileWriter(hotelsFile));
            for (String hotelString : hotelsStringList) {
                //System.out.println(hotelString);
                writer.write(hotelString);
            }
            writer.close();
            writer=new BufferedWriter(new FileWriter(roomsFile));
            for (String roomString : roomsStringList) {
                writer.write(roomString);
            }
            writer.close();
            writer=new BufferedWriter(new FileWriter(roomHotelMapFile));
            for (String hotelRoomString : hotelRoomMapStringList) {
                writer.write(hotelRoomString);
            }
        } catch (IOException ex) {
            throw new Error("Can't write to DB.");
        } finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException ex) {
                System.out.println("Can't close writer!");
            }
        }
    }

    /**
     * Truncate all tables (Hotel, Room, RoomHotelMap).
     */
    public void truncateDB(){
        File hotelsFile = new File(HOTEL_FILE_PATH);
        File roomsFile = new File(ROOM_FILE_PATH);
        File roomHotelMapFile = new File(ROOM_HOTEL_MAP);
        if(hotelsFile.exists()) hotelsFile.delete();
        if(roomsFile.exists()) roomsFile.delete();
        if(roomHotelMapFile.exists()) roomHotelMapFile.delete();
        createDBStructure();
    }

    public void createDBStructure(){
        File hotelsFile = new File(HOTEL_FILE_PATH);
        File roomsFile = new File(ROOM_FILE_PATH);
        File roomHotelMapFile = new File(ROOM_HOTEL_MAP);
        try{
            hotelsFile.createNewFile();
            roomsFile.createNewFile();
            roomHotelMapFile.createNewFile();
        } catch(IOException ex){
            System.out.println("Can't initialize DB.");
        }

    }

    /**
     * Reads data from Hotel, Room and RoomHotelMap tables and collects a list of Hotel objects.
     * @return List of hotels.
     */
    public List<Hotel> readHotels(){
        File hotelsFile = new File(HOTEL_FILE_PATH);
        File roomsFile = new File(ROOM_FILE_PATH);
        File roomHotelMapFile = new File(ROOM_HOTEL_MAP);
        BufferedReader reader = null;
        Hotel tempHotel;
        Room tempRoom;
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        String line = "";
        int[] hotel_room;
        List<int[]> hotelRoomPairs = new ArrayList<>();

        try{
            //CREATE HOTELS
            reader = new BufferedReader(new FileReader(hotelsFile));
            while((line = reader.readLine()) != null){
                tempHotel=parseHotel(line);
                if(tempHotel==null){
                    break;
                }
                hotels.add(tempHotel);
            }
            reader.close();
            //READ ROOMS TO SINGLE LIST
            reader = new BufferedReader(new FileReader(roomsFile));
            while((line = reader.readLine()) != null){
                tempRoom=parseRoom(line);
                if(tempRoom==null){
                    break;
                }
                rooms.add(tempRoom);
            }
            reader.close();
            //READ idHotel-idRoom PAIRS
            reader = new BufferedReader(new FileReader(roomHotelMapFile));
            while((line = reader.readLine()) != null){
                hotel_room=parseHotelRoomPair(line);
                if(hotel_room==null){
                    break;
                }
                hotelRoomPairs.add(hotel_room);
            }
            reader.close();
            //ADD ROOMS TO HOTELS
            for (int[] hotelRoomPair : hotelRoomPairs) {
                Hotel hotel;
                Room room;
                try {
                    hotel = hotels.stream()
                            .filter(x -> x.getId() == hotelRoomPair[0])
                            .findFirst()
                            .get();
                    room = rooms.stream()
                            .filter(x -> x.getId() == hotelRoomPair[1])
                            .findFirst()
                            .get();
                    hotel.addRoom(room);
                } catch (Exception ex){
                    System.out.println("WARNING!! Inconsistent rooms and hotels tables!!! Check the DB!");
                }

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
        return hotels;
    }

    //LINES PARSING

    /**
     * Parses String line and creates a Hotel.
     * @param hotelLine line to parse (tab separated id\tcity\t\name)
     * @return Hotel object
     */
    private Hotel parseHotel(String hotelLine){
        List<String> result;
        try{
            result = getListFromLins(hotelLine);
        }catch (Exception ex){
            System.out.println("Unable to parse hotel.");
            return null;
        }

        Hotel tempHotel=null;
        int id;
        String name;
        String city;

        try{
            id = Integer.parseInt(result.get(0));
            city = result.get(1);
            name = result.get(2);
            tempHotel = new Hotel(id,city,name);
        }catch (Exception ex){
            System.out.println("Unable to parse hotel.");
        }
        return tempHotel;
    }

    /**
     * Parses String line and creates a Room.
     * @param roomLine line to parse (tab separated id\tperson\t\price)
     * @return Room object
     */
    private Room parseRoom(String roomLine){
        List<String> result;
        try{
            result = getListFromLins(roomLine);
        }catch (Exception ex){
            System.out.println("Unable to parse hotel.");
            return null;
        }

        int id;
        byte person;
        BigDecimal price;
        Room tempRoom = null;
        try{
            id = Integer.parseInt(result.get(0));
            person = Byte.parseByte(result.get(1));
            price = new BigDecimal(result.get(2));
            tempRoom = new Room(id,person,price);
        }catch (Exception ex){
            System.out.println("Unable to parse room.");
        }
        return tempRoom;
    }

    /**
     * Parses a line to create idHotel-idRoom pair.
     * @param pairLine line for parsing (tab-separated)
     * @return array of ints.
     */
    private int[] parseHotelRoomPair(String pairLine){
        List<String> result;
        try{
            result = getListFromLins(pairLine);
        }catch (Exception ex){
            System.out.println("Unable to parse hotel-room pair.");
            return null;
        }

        int idHotel;
        int idRoom;

        try{
            idHotel = Integer.parseInt(result.get(0));
            idRoom = Integer.parseInt(result.get(1));
            return new int[] {idHotel,idRoom};
        }catch (Exception ex){
            System.out.println("Unable to parse hotel-room pair.");
        }
        return null;
    }

    /**
     * Parses String line and creates a List for creating an object.
     * @param line line to parse (tab separated)
     * @return Line<String>
     */
    private List<String> getListFromLins(String line){
        Scanner sc = new Scanner(line);
        sc.useDelimiter("\t");
        List<String> result = new ArrayList<>();

        while(sc.hasNext()){
            result.add(sc.next());
        }
        return result;
    }



}
