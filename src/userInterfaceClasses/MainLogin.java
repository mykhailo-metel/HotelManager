package userInterfaceClasses;

public class MainLogin {

    /**
     * Метод для входа пользователя в систему. Пользователю предлагается ввести уникальный логин. В случае если человек
     * с таким логином имеет права администратора, вызывается меню администратора, в противном случае - меню клиента.
     */
    public void run() {
        System.out.println(
                "Введите логин" +
                        "\n1. Админ" +
                        "\n2. Юзер" +
                        "\n0. Выход");
        byte choise = Requester.requestChoise();
        switch (choise) {
            case 1:

                break;
            case 2:
                break;
        }
        /*try{
            String login = Requester.requestName();

            if (userController.hasLogin(login)) {
                userController.setTempUser(userController.findByLogin(login));
                System.out.println("Добро пожаловать " + userController.getTempUser());
            }
            else {
                System.out.println("Несущствующий логин.");
            }

            if (userController.getTempUser()!= null) {
                if (userController.getTempUser().getIsAdmin()) {
                    runTUI(interFace.getAdminMenu());
                }
                else {
                    runTUI(interFace.getUserMenu());
                }
            }
        }catch (RuntimeException e) {
            System.out.println(e + "Ошибка при входе в систему");
        }*/
    }
}
