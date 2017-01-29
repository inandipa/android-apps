package com.example.indra.intents;

import java.io.Serializable;

/**
 * Created by indra on 5/30/2016.
 */
public class User implements Serializable{
    private String name;
    private double age;

    public User(String name,double age) {
        this.age = age;
        this.name=name;
    }

    @Override
    public String toString() {
        return "com.example.indra.intents.User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
