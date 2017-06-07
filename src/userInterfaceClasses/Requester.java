package userInterfaceClasses;

import DAO.*;
import models.BaseModel;
import models.Hotel;
import models.Room;

import java.util.*;

/**
 * Класс, предназначенный для запроса данных у пользователей при помощи статических методов запросов.
 */
public class Requester {

    /**
     * Ввод выбранного номера пункта меню.
     * @return возвращает числовое значение выбранного пункта меню.
     * @throws RuntimeException - при вводе строки или некорректного числа будет выброшен эксепшн.
     */
    public static byte requestChoise() throws RuntimeException{
        Scanner scanner = new Scanner(System.in);
        byte choise;
        try{
            choise = scanner.nextByte();
        }
        catch (RuntimeException e){
            throw new RuntimeException("Ошибка ввода номера пункта меню");
        }
        return choise;
    }

    /**
     * Ввод имени пользователя или отеля.
     * @return возвращает запрошенное у пользователя имя.
     * @throws RuntimeException в случае ошибок ввода бросается эксепшн.
     */
    public static String requestName()throws RuntimeException  {
        Scanner scanner = new Scanner(System.in);
        String name;
        try{name = scanner.next();
            if(name == null || name.length() == 0) {
                throw new RuntimeException("Некорректное имя");
            }
        } catch (RuntimeException e){
            throw new RuntimeException("Ошибка ввода логина");
        }
        return name;
    }

    public static int requestNumber() {
        Scanner scanner = new Scanner(System.in);
        String string;
        try{string = scanner.next();
            if(string == null || string.length() == 0) {
                throw new RuntimeException("Некорректное значение.");
            }
            return Integer.parseInt(string);
        } catch (RuntimeException e){
            throw new RuntimeException("Ошибка ввода числа.");
        }
    }

    public static String selectCity(DAO<Hotel> hotelDAO) {
        Map<Integer,String> idCityMap = new HashMap<>(0);
        int i = 0;
        String selectedCity = null;

        for (Hotel h: hotelDAO.getAll()){
            i++;
            idCityMap.put(i, h.getCity());
        }
        System.out.println("Выберите номер города в предложенном списке");
        try(Scanner scanner = new Scanner(System.in)){
            int n = Requester.requestNumber();
            selectedCity = idCityMap.get(n);
        } catch (RuntimeException e){
            System.out.println("Ошибка выбора города");
        }
        return selectedCity;
    }

    public static Date requestDate() {
        Date date = null;

        try{
            date = new Date( getYear(),getMonth(),getDay());
        } catch (RuntimeException e){
            System.out.println(e.toString() + "\nОшибка ввода даты. Возможно вы ввели непраивльное число из дней в месяце");
        }
        return date;
    }

    /**
     * Запрос года у пользователя.
     * @return возвращает год (числовое значение)
     * @throws RuntimeException в случае ошибки ввода или ввода строки бросается эксепшн.
     */
    private static int getYear() throws RuntimeException {
        int maxYear = 2020;
        int minyear = 2017;

        System.out.println("Год:");
        try(Scanner scanner = new Scanner(System.in)){
            int y = scanner.nextInt();
            if (y > maxYear ) {throw new RuntimeException("Слишком далеко в будущем!");}
            if (y < minyear) {throw new RuntimeException("Невозможно сделать бронирование в прошлом!");}
            return y;}
        catch (RuntimeException e){
            System.out.println(e);
            throw new RuntimeException("Ошибка ввода года");
        }
    }

    /**
     * Запрос месяца у пользователя.
     * @return возвращает месяц (числовое значение)
     * @throws RuntimeException в случае ошибки ввода или ввода строки бросается эксепшн.
     */
    private static int getMonth() throws RuntimeException  {
        System.out.println("Месяц:");
        try(Scanner scanner = new Scanner(System.in)){
            int m = scanner.nextInt() - 1; //TODO Нужно проверить правильность ввода месяца в таком формате
            if (m < 1 || m > 12 ) {throw new RuntimeException("Неправильный месяц");}
            return m;
        }
        catch (RuntimeException e){
            System.out.println(e);
            throw new RuntimeException("Ошибка ввода месяца");
        }
    }

    /**
     * Запрос дня месяца у пользователя.
     * @return возвращает день (числовое значение)
     * @throws RuntimeException в случае ошибки ввода или ввода строки бросается эксепшн.
     */
    private static int getDay() throws RuntimeException  {
        System.out.println("День: ");
        try(Scanner scanner = new Scanner(System.in)){
            int d = scanner.nextInt();
            if (d<1 || d > 31) {throw new RuntimeException("Неправильно введенный день");}
            return d;
        }
        catch (RuntimeException e){
            System.out.println(e);
            throw new RuntimeException("Ошибка ввода дня");
        }
    }

    public  static <E extends BaseModel> E select (DAO<E> DAO) {
        Map<Integer,E> idEntityMap = new HashMap<>(0);
        int i = 0;
        E selectedEntity = null;

        for (E h: DAO.getAll()){
            i++;
            System.out.println(h.toString());
            idEntityMap.put(i, h);
        }
        System.out.println("Выберите номер в списке");
        try(Scanner scanner = new Scanner(System.in)){
            int n = Requester.requestNumber();
            selectedEntity = idEntityMap.get(n);
            System.out.println("Вы выбрали " + selectedEntity.toString());
        } catch (RuntimeException e){
            System.out.println("Ошибка выбора номера");
        }
        return selectedEntity;
    }

    /**
     * Method for selecting room in a hotel
     * @param hotelId - id of hotel
     * @param hotelDAO - DAO of hotels
     * @param roomDAO - dao of rooms
     * @return Room which user has selected
     */
    public static Room selectRoom(int hotelId, DAO<Hotel> hotelDAO, DAO<Room> roomDAO) {
        Room selectedRoom = null;
        List<Integer> roomList = ((DAORoom)roomDAO).findRoomsByHotelId(hotelId);
        roomList.forEach(System.out::println);
        System.out.println("Выберите номер в списке");

        try(Scanner scanner = new Scanner(System.in)){
            int n = Requester.requestNumber();
            selectedRoom = (Room) roomDAO.findByID(n);
            System.out.println("Вы выбрали " + selectedRoom.toString());
        } catch (RuntimeException e){
            System.out.println("Ошибка выбора номера");
        }
        return selectedRoom;
    }


    /**
     * Method for requesting confirmation
     * user must enter - 1 means yes, otherwise - no;
     * @return - userchoise;
     */
    public static boolean requestConfirm(String actionName) {
        System.out.println("вы подтверждаете действие " + actionName + " ?");
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("1. - Да\n2. Нет\n");
            String answ = scanner.next();
            return answ.equals("1");
        } catch (RuntimeException e){
            System.out.println("неправильный символ подверждения");
        }
        return false;
    }
}
