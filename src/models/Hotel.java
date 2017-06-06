package models;

public class Hotel implements BaseModel {
    private int id;
    private String city;
    private String name;

    public Hotel(int id, String city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public String StringForWritingToDB() {
        return Integer.toString(this.getId())+"\t"
                + this.getCity()+"\t"
                + this.getName()+"\n";
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("Отель #");
        sb.append(id);
        sb.append(", Город ");
        sb.append(city);
        sb.append(", Название ");
        sb.append(name);
        sb.append("\t\t");
        return  sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (getId() != hotel.getId()) return false;
        if (getCity() != null ? !getCity().equals(hotel.getCity()) : hotel.getCity() != null) return false;
        return getName() != null ? getName().equals(hotel.getName()) : hotel.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
