package userInterfaceClasses;

import java.util.Scanner;

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

}
