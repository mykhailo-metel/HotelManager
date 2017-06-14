package userInterfaceClasses;

import DAO.DAO;
import DAO.DAORoom;
import models.BaseModel;
import models.Hotel;
import models.Room;

import java.math.BigDecimal;
import java.util.*;

/**
 * Класс, предназначенный для запроса данных у пользователей при помощи статических методов запросов.
 */
public class Requester {
    static private Scanner scanner = new Scanner(System.in);
    /**
     * Ввод выбранного номера пункта меню.
     * @return возвращает числовое значение выбранного пункта меню.
     * @throws RuntimeException - при вводе строки или некорректного числа будет выброшен эксепшн.
     */
    public static int requestInt() throws RuntimeException{
        int value;
        String str;
        try{
            str = scanner.nextLine();
            if (!checkString(str)){throw new RuntimeException();}
            value = Integer.parseInt(str);
        }
        catch (RuntimeException e){
            throw new RuntimeException("Ошибка ввода номера пункта");
        }
        return value;
    }

    /**
     * Ввод имени пользователя или отеля.
     * @return возвращает запрошенное у пользователя имя.
     */
    public static String requestName()throws RuntimeException  {
        String name;

        try{
            name = scanner.nextLine();
            if(!checkString(name)){throw new RuntimeException();};
        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка считки имени с консоли");
        }
        return name;
    }

    public static String selectCity(DAO<Hotel> hotelDAO) {
        int i = 0;
        String selectedCity = null;
        Map<Integer,String> idCityMap = new HashMap<>(0);

        for (Hotel h: hotelDAO.getAll()){
            i++;
            idCityMap.put(i, h.getCity());
        }
        System.out.println("Выберите номер города в предложенном списке");
        try{
            int n = Requester.requestInt();
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
        try{
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
        try{
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
        try{
            int d = scanner.nextInt();
            if (d<1 || d > 31) {throw new RuntimeException("Неправильно введенный день");}
            return d;
        }
        catch (RuntimeException e){
            System.out.println(e);
            throw new RuntimeException("Ошибка ввода дня");
        }
    }

    public  static <E extends BaseModel> E select (DAO<E> dao) {
        int i = 0;
        E selectedEntity = null;

        System.out.println("Список для выбора:");
        dao.getAll().forEach(System.out::println);
        System.out.println("Выберите ID в списке");
        try{
            int n = Requester.requestInt();
            selectedEntity = dao.findByID(n);
            if(selectedEntity == null){throw new RuntimeException();}
            System.out.println("Вы выбрали " + selectedEntity.toString() + "\n");
        } catch (RuntimeException e){
            System.out.println("Ошибка выбора номера\n");
        }
        return selectedEntity;
    }

    /**
     * Method for selecting room in a hotel
     * @param hotelId - id of hotel
     * @param roomDAO - dao of rooms
     * @return Room which user has selected
     */
    public static Room selectRoom(int hotelId, DAO<Room> roomDAO) {
        Room selectedRoom = null;
        List<Integer> roomList = ((DAORoom)roomDAO).findRoomsIdByHotelId(hotelId);
        roomList.forEach(System.out::println);
        System.out.println("Выберите номер в списке");

        try{
            int n = Requester.requestInt();
            selectedRoom = roomDAO.findByID(n);
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
        try{
            System.out.println("1. Да\n2. Нет\n");
            String answ = scanner.nextLine();
            return answ.equals("1");
        } catch (RuntimeException e){
            System.out.println("неправильный символ подверждения");
        }
        return false;
    }

    private static boolean checkString(String str) {
        boolean result = true;
        try{
            if(str == null || str.length() == 0) {result = false;}
            String[] spliited = str.split(" ");
            if(spliited.length > 1){result = false;}
        } catch (RuntimeException e) {
            result = false;
        }
        return result;
    }

    public static BigDecimal requestPrice() throws RuntimeException {
        BigDecimal value;
        String str;
        try{
            str = scanner.nextLine();
            if (!checkString(str)){throw new RuntimeException();}
            value = BigDecimal.valueOf(Double.parseDouble(str));
        }
        catch (RuntimeException e){
            throw new RuntimeException("Ошибка ввода числа");
        }
        return value;
    }
}
