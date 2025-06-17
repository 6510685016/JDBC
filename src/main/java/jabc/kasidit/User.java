package jabc.kasidit;

import java.sql.Date;

public class User {
    private int id;
    private String name;
    private String surname;
    private Date birthdate;
    private String address;

    public User(int id, String name, String surname, Date birthday, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthday;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
