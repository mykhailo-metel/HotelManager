package userInterfaceClasses;

public class CurrentStatus {
//    private static UserController userController;
//    private static HotelController hotelController;
//    private static BookingController bookingController;

    private static int loggedInUserId = -1;


    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int loggedInUserId) {
        CurrentStatus.loggedInUserId = loggedInUserId;
    }

}
