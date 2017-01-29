package com.example.indra.madweather;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by indra on 6/9/16.
 */

public class Weather implements Serializable {
    private double temp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int humidity;
    private  int pressure;
     public String code;
    private ArrayList<String> weatherlist;
    private ArrayList<String> icon;

    public ArrayList<String> getIcon() {
        return icon;
    }

    public void setIcon(ArrayList<String> icon) {
        this.icon = icon;
    }

    public ArrayList<String> getWeatherlist() {
        return weatherlist;
    }

    public void setWeatherlist(ArrayList<String> weatherlist) {
        this.weatherlist = weatherlist;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", weatherlist=" + weatherlist +
                '}';
    }

    public int getPressure() {

        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {

        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp() {

        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
