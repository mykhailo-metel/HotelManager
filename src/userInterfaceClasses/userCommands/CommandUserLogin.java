package userInterfaceClasses.userCommands;

import Services.UserServices;
import userInterfaceClasses.Command;

public class CommandUserLogin implements Command {
    UserServices userServices;

    public CommandUserLogin(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public void execute() {
        userServices.login();
    }
}
