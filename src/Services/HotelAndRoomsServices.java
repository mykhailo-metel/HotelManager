package Services;


import DAO.DAO;
import DAO.DAOBooking;
import DAO.DAORoom;
import models.Booking;
import models.Hotel;
import models.Room;
import userInterfaceClasses.Requester;

import java.math.BigDecimal;
import java.util.List;

public class HotelAndRoomsServices {
    private DAO<Booking> bookingDAO;
    private DAO<Hotel> daoHotel;
    private DAO<Room> daoRoom;

    public HotelAndRoomsServices(DAO<Hotel> daoHotel, DAO<Room> daoRoom, DAO<Booking> bookingDAO) {
        this.daoHotel = daoHotel;
        this.daoRoom = daoRoom;
        this.bookingDAO = bookingDAO;
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

            System.out.println("Добавить комнаты в отель \n1. Да\n2. Нет \n");
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

    public void editHotelName(){
        try {
            System.out.println("Изменение имени отеля");
            Hotel hotel = Requester.select(daoHotel);
            System.out.println("Введите новое название отеля");
            hotel.setName(Requester.requestName());
            daoHotel.update(hotel);
            System.out.println("Имя отеля изменено");
            System.out.println(hotel.toString());
        } catch (RuntimeException e){
            System.out.println("Ошибка при изменении имени отеля.");
        }
    }

    public void deleteHotel(){
        try {
            System.out.println("Удаление отеля, всех его комнат и их бронирований");
            Hotel hotel = Requester.select(daoHotel);
            if(Requester.requestConfirm(" удаление отеля ")){
                List<Integer> roomIdList = ((DAORoom)daoRoom).findRoomsIdByHotelId(hotel.getId());

                if (!(roomIdList == null)) {
                    for (Integer aRoomIdList : roomIdList) {
                        Room room = (Room) ((DAORoom) daoRoom).findByID(aRoomIdList);
                        ((DAOBooking) bookingDAO).findByRoomId(room.getId()).forEach(((DAOBooking) bookingDAO)::delete);
                        daoRoom.delete(room);
                    }
                    }
                }
            daoHotel.delete(hotel);
            System.out.println("Отель удален");
        } catch (RuntimeException e){
            System.out.println("Ошибка при удалении отеля");
        }
    }

    void deleteRoom() {
        try{
            Hotel hotel = Requester.select(daoHotel);
            Room room = selectRoom(hotel);
            System.out.println("Удалить комнату. Все бронирования будут отменены.\n" + room + "\n1. Да\n2. Нет");
            if (Requester.requestInt() == 1) {
                ((DAOBooking)bookingDAO).findByRoomId(room.getId()).forEach(((DAOBooking) bookingDAO)::delete);
                daoRoom.delete(room);}
        } catch (RuntimeException e ) {
            System.out.println("Ошибка при удалении комнаты");
        }

    }

    private Room selectRoom(Hotel hotel) {
        Room room = null;

        ((DAORoom)daoRoom).findRoomsIdByHotelId(hotel.getId()).forEach(System.out::println);
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
