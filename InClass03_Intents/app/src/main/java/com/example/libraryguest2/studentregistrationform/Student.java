package com.example.libraryguest2.studentregistrationform;

import java.io.Serializable;

/**
 * Created by LibraryGuest2 on 5/31/2016.
 */
public class Student implements Serializable{

    String name;
    String email;
    String language;

    public Student(String name, String email, String language) {
        this.name = name;
        this.email = email;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
