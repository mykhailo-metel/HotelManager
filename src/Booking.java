import java.util.Date;

/**
 * Created by Vlad on 17.05.2017.
 */
public class Booking {


    private long id;
    private int roomId;
    private User user;
    private Date dateBegin;
    private Date dateEnd;
    private int hotelId;

    public Booking(long id, User user, Date dateBegin, Date dateEnd, int hotelId, int roomId) throws IllegalArgumentException{

        if(user==null||dateBegin==null||dateEnd==null){
            throw new IllegalArgumentException("No null field allowed");
        }

        this.id=id;
        this.user = user;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.hotelId = hotelId;
        this.roomId = roomId;
    }

    public long getId() {
        return id;
    }

    public int getRoom() {
        return roomId;
    }

    public User getUser() {
        return user;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public int getHotel() {
        return hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (roomId != booking.roomId) return false;
        if (hotelId != booking.hotelId) return false;
        if (!getUser().equals(booking.getUser())) return false;
        if (!getDateBegin().equals(booking.getDateBegin())) return false;
        return getDateEnd().equals(booking.getDateEnd());
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getDateBegin().hashCode();
        result = 31 * result + getDateEnd().hashCode();
        result = 31 * result + hotelId;
        return result;
    }

    @Override
    public String toString() {
        return  "Бронирование Id - " + getId() + " пользователь " + getUser().getLogin() +
                "\nОтель: " + Integer.toString(getHotel()) +
                " Комната: " + Integer.toString(getRoom()) +
                "\nДата начала: " + getDateBegin() +
                "\nДата конца: " + getDateEnd() + "\n";
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
