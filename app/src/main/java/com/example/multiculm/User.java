package com.example.multiculm;

/**
 * Created by Mitch on 2016-05-13.
 */
public class User {
    private String Name;
    private String Phone;
    private String Email ;
    private String Street ;
    private String Place ;

    public User(String getName, String getPhone, String getEmail, String getStreet, String getPlace ){
        Name = getName;
        Phone = getPhone;
        Email = getEmail;
        Street = getStreet;
        Place = getPlace;
    }

    public String getName() {
        return Name;
    }

    public void setName(String getName) {
        Name = getName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String getPhone) {
        Phone = getPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String getEmail) { Email = getEmail; }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String getStreet) { Street = getStreet; }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String getPlace) { Place = getPlace; }

}
