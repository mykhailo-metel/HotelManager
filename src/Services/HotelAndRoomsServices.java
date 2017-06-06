package Services;


import DAO.DAOHotel;
import DAO.DAORoom;
import models.BaseModel;
import models.Hotel;
import models.Room;
import userInterfaceClasses.Requester;

public class HotelAndRoomsServices {
    private DAOHotel daoHotel;
    private DAORoom daoRoom;

    public HotelAndRoomsServices(DAOHotel daoHotel, DAORoom daoRoom) {
        this.daoHotel = daoHotel;
        this.daoRoom = daoRoom;
    }

    public void addNewHotelWithRooms(){

    }

    public void editHotelData(Hotel hotel){

    }

    public void editHotel(Hotel hotel){

    }

    public void deleteRoom() {
        Hotel hotel = selectHotel();
        Room room = selectRoom(hotel);
        System.out.println("Удалить комнату " + room + " ? 1. Да, 2. Нет");
        if (Requester.requestChoise() == 1) {daoRoom.delete(room);}
    }

    public Hotel selectHotel(){
        BaseModel hotel = null;

        daoHotel.getAll().forEach(System.out::println);
        System.out.println("Введите айди отеля:");
        try{
            hotel = daoHotel.findByID(Requester.requestNumber());
            if (hotel == null) {
                throw new RuntimeException("не найден отель!");
            }
        } catch (RuntimeException e) {
            System.out.println(e);
            System.out.println("Невозможно найти отель с таким айди.");
        }
        return (Hotel) hotel;
    }

    public Room selectRoom(Hotel hotel) {
        Room room = null;

        daoRoom.findRoomsByHotelId(hotel.getId()).forEach(System.out::println);
        System.out.println("Введите айди комнаты");

        try{
            room = (Room)daoRoom.findByID(Requester.requestNumber());
        } catch (RuntimeException e){
            System.out.println(e);
            System.out.println("Некорректно введена комната");
        }
        return room;
    }
}
