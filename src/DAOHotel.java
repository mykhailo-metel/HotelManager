import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
/**
 * Created by Vlad on 17.05.2017.
 */
public class DAOHotel {
    private String CURRENT_DIR = System.getProperty("user.dir")+"/db/";
    private String HOTEL_FILE_PATH = CURRENT_DIR+"Hotel.tsv";
    private String ROOM_FILE_PATH = CURRENT_DIR+"Room.tsv";
    private String ROOM_HOTEL_MAP = CURRENT_DIR+"RoomHotelMap.tsv";

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
                System.out.println(hotelString);
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

    public void truncateDB(){
        File hotelsFile = new File(HOTEL_FILE_PATH);
        File roomsFile = new File(ROOM_FILE_PATH);
        File roomHotelMapFile = new File(ROOM_HOTEL_MAP);
        if(hotelsFile.exists()) hotelsFile.delete();
        if(roomsFile.exists()) roomsFile.delete();
        if(roomHotelMapFile.exists()) roomHotelMapFile.delete();
    }

    public List<Hotel> readHotels(){
        File hotelsFile = new File(HOTEL_FILE_PATH);
        File roomsFile = new File(ROOM_FILE_PATH);
        File roomHotelMapFile = new File(ROOM_HOTEL_MAP);

        //CREATE HOTELS

        //READ ROOMS TO SINGLE LIST

        //PLACE ROOMS

        return null;
    }


}
