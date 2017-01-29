package com.example.indra.saxparsing;

import java.util.ArrayList;

/**
 * Created by indra on 6/12/16.
 */
public class Weather {

    private double temp;
    private String name;
    private int humidity;
    private  int pressure;
    private ArrayList<String> weatherlist;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public ArrayList<String> getWeatherlist() {
        return weatherlist;
    }

    public void setWeatherlist(ArrayList<String> weatherlist) {
        this.weatherlist = weatherlist;
    }
}
