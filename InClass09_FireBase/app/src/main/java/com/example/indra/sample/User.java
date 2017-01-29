package com.example.indra.sample;

import java.util.ArrayList;

/**
 * Created by indra on 6/23/16.
 */
public class User {
    private String Email,Name,Password,check;
    private ArrayList<AppDetails> favorites;

    @Override
    public String toString() {
        return "User{" +
                "Email='" + Email + '\'' +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", favorites=" + favorites +
                '}';
    }

    public User(){

    }

    public User(String email, String name, String password) {
        Email = email;

        Name = name;
        Password = password;
    }

    public String getCheck() {
        return check;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public ArrayList<AppDetails> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<AppDetails> favorites) {
        this.favorites = favorites;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
