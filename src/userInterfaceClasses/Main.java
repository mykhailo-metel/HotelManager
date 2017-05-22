package userInterfaceClasses;

public class Main {

    private MainLogin mainLogin;
    private MainRegister mainRegister;

    public void run(){
        byte choise;
        System.out.println(
                "Главное меню" +
                "\n1. Логин" +
                "\n2. Регистрация" +
                "\n0. Выход");
        do {
            choise = Requester.requestChoise();
            switch (choise) {
                case 1:
                    mainLogin.run();
                    break;
                case 2:
                    mainRegister.run();
                    break;
            }
        } while (choise != 0);
    }
}
