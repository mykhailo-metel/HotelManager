package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements BaseModel {
    private int id;
    private int roomId;
    private int userId;
    private Date dateBegin;
    private Date dateEnd;

    public Booking(int id, int roomId, int userId, Date dateBegin, Date dateEnd) throws IllegalArgumentException{
        if(dateBegin == null || dateEnd == null){
            throw new IllegalArgumentException("No null field (in date) allowed");
        }
        this.id=id;
        this.roomId = roomId;
        this.userId = userId;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    @Override
    public String StringForWritingToDB() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateBegin = dateFormat.format(getDateBegin());
        String strDateEnd = dateFormat.format(getDateEnd());

        return getId()+"\t"
                + getRoomId() + "\t"
                + getUserId() + "\t"
                + strDateBegin + "\t"
                + strDateEnd + "\n";
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

}
