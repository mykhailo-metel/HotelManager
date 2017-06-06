package models;


public class User implements BaseModel {
    private static int maxID = 0;
    private int id;
    private String name;
    private String surname;
    private String login;
    private String userRights;

    /**
     * Constructor for generating users during runtime
     * @param name
     * @param surname
     * @param login
     * @param userRights
     */
    public User(String name, String surname, String login, String userRights) {
        User.maxID++;
        this.id = maxID;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.userRights = userRights;
    }

    /**
     * Constructor for reading users from file or string
     * @param id
     * @param name
     * @param surname
     * @param login
     * @param userRights
     */
    public User(int id, String name, String surname, String login, String userRights) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.userRights = userRights;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Generates the string further used for writing to file
     * @return string
     */
    @Override
    public String StringForWritingToDB() {
        return Integer.toString(getId()) + "\t"
                + getName() + "\t"
                + getSurname() + "\t"
                + getLogin() + "\t"
                + getUserRights() + "\n";
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getUserRights() {
        return userRights;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserRights(String userRights) {
        this.userRights = userRights;
    }

    @Override
    public String toString() {
        return "id:\t" + id +
                "\nName:\t'" + name + '\'' +
                "\nSurname:\t'" + surname + '\'' +
                "\nLogin:\t'" + login + '\'' +
                "\nuserRights:\t'" + userRights + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        return getUserRights().equals(user.getUserRights());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getUserRights() != null ? getUserRights().hashCode() : 0);
        return result;
    }
}
