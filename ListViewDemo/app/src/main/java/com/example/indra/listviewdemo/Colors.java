package com.example.indra.listviewdemo;

/**
 * Created by indra on 6/14/16.
 */
public class Colors {

    public String name,Hexname;

    public Colors(String name, String hexname) {
        this.name = name;
        Hexname = hexname;
    }

    @Override
    public String toString() {
        return "Colors{" +
                "name='" + name + '\'' +
                ", Hexname='" + Hexname + '\'' +
                '}';
    }
}
