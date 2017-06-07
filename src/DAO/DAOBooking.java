package DAO;

import models.Booking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOBooking extends DaoAbstract {

    DAOBooking(String path){
        super(path);
    }

    /**
     * Method for parsing bookings.
     * Sequence of parsed values
     * 1. Id 2. RoomId 3. UserId 4. DateBegin 5. DateEnd
     * @param line - input string
     * @return Booking object
     */
    @Override
    protected Booking parseLine(String line) {
        int id;
        int roomId;
        int userId;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateBegin;
        Date dateEnd;
        List<String> result;
        Booking booking = null;

        try{
            result = getListFromLins(line);
            id = Integer.parseInt(result.get(0));
            roomId = Integer.parseInt(result.get(1));
            userId = Integer.parseInt(result.get(2));
            dateBegin = dateFormat.parse(result.get(3));
            dateEnd = dateFormat.parse(result.get(4));
            booking = new Booking(id, roomId, userId, dateBegin, dateEnd);
        }catch (Exception ex){
            System.out.println("Unable to parse booking.");
        }
        return booking;
    }


    /**
     * Looks for all bookings of a room
     * @param id - roomId
     * @return - list of bookings satisfying the criteria
     */
    public List<Booking> findByRoomId(int id) {
        List<Booking> tempList = new ArrayList<>(list);
        return tempList.stream().filter( booking -> ((Booking) booking).getRoomId() == id).collect(Collectors.toList());
    }

//    public List<Booking> findByCondition(Predicate<Booking> p){
//        List<Booking> tempList = new ArrayList<>(list);
//        return tempList.stream().filter(p).findFirst().orElse(null);
//    }
}
