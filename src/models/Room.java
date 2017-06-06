package models;

import java.math.BigDecimal;


public class Room implements BaseModel {
    private int id;
    private int hotelID;
    private byte person;
    private BigDecimal price;

    public Room(int id,int hotelId, byte person, BigDecimal price) {
        this.id=id;
        this.hotelID = hotelId;
        this.person = person;
        this.price = price;
    }

    @Override
    public String StringForWritingToDB() {
        return Integer.toString(this.getId()) + "\t" +
                Integer.toString(this.gethotelId()) + "\t" +
                Byte.toString(this.getPerson()) + "\t" +
                this.getPrice().toString()+"\n";
    }

    @Override
    public String toString() {
        return "Room #" + id +
                ", capacity:" + person + " preson(s)"+
                ", price=" + price + " USD";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (getId() != room.getId()) return false;
        if (getPerson() != room.getPerson()) return false;
        return getPrice() != null ? getPrice().equals(room.getPrice()) : room.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (int) getPerson();
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    public byte getPerson() {
        return person;
    }

    public void setPerson(byte person) {
        this.person = person;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public int gethotelId() {
        return hotelID;
    }
}
