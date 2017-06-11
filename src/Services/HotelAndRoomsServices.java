package Services;


import DAO.*;
import DAO.DAORoom;
import models.BaseModel;
import models.Hotel;
import models.Room;
import userInterfaceClasses.Requester;

import java.math.BigDecimal;

public class HotelAndRoomsServices {
    private DAO<Hotel> daoHotel;
    private DAO<Room> daoRoom;

    public HotelAndRoomsServices(DAO<Hotel> daoHotel, DAO<Room> daoRoom) {
        this.daoHotel = daoHotel;
        this.daoRoom = daoRoom;
    }

    public void addNewHotelWithRooms(){
        try{
            System.out.println("Добавление нового отеля");
            System.out.println("Введите название отеля");
            String hotelName = Requester.requestName();
            System.out.println("Введите название города");
            String city = Requester.requestName();
            Hotel hotel = new Hotel(hotelName,city);

            daoHotel.add(hotel);
            System.out.println("Отель " + hotel + " добавлен");

            System.out.println("Добавить комнаты в отель \n1. Да\n 2. Нет \n");
            int choise = Requester.requestInt();
            if(choise != 1){return;}
            int hotelId = hotel.getId();

            do{
                System.out.println("Введите цену на комнаты");
                BigDecimal price = Requester.requestPrice();
                System.out.println("Введите количество людей  в комнате");
                int persons = Requester.requestInt();
                System.out.println("Введите количество таких комнат");
                int count = Requester.requestInt();

                for (int i = 0; i < count; i++) {
                    Room room = new Room(hotelId,persons,price);
                    daoRoom.add(room);
                }

                System.out.println("Хотите ли добавить еще комнаты в этот отель? \n1. Да\n2. Нет  ");
                choise = Requester.requestInt();
            } while (choise == 1);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Ошибка при создании отеля");
        }
    }

    public void editHotel(Hotel hotel){

    }

    public void deleteRoom() {
        Hotel hotel = selectHotel();
        Room room = selectRoom(hotel);
        System.out.println("Удалить комнату " + room + " ? 1. Да, 2. Нет");
        if (Requester.requestInt() == 1) {daoRoom.delete(room);}
    }

    public Hotel selectHotel(){
        BaseModel hotel = null;

        daoHotel.getAll().forEach(System.out::println);
        System.out.println("Введите айди отеля:");
        try{
            hotel = daoHotel.findByID(Requester.requestInt());
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

        ((DAORoom)daoRoom).findRoomsByHotelId(hotel.getId()).forEach(System.out::println);
        System.out.println("Введите айди комнаты");

        try{
            room = (Room)daoRoom.findByID(Requester.requestInt());
        } catch (RuntimeException e){
            System.out.println(e);
            System.out.println("Некорректно введена комната");
        }
        return room;
    }
}
