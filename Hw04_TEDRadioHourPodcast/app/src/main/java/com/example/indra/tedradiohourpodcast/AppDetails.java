package com.example.indra.tedradiohourpodcast;


import android.text.format.DateFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by indra on 6/14/16.
 */public class AppDetails implements Serializable{

    String Title, Description, Publication_date, Image_URL, Duration,MP3_URL;

    @Override
    public String toString() {
        return "AppDetails{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Publication_date='" + Publication_date + '\'' +
                ", Image_URL='" + Image_URL + '\'' +
                ", Duration='" + Duration + '\'' +
                ", MP3_URL='" + MP3_URL + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPublication_date() {
        return Publication_date;
    }

    public void setPublication_date(String publication_date) {
        Publication_date = publication_date;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getMP3_URL() {
        return MP3_URL;
    }

    public void setMP3_URL(String MP3_URL) {
        this.MP3_URL = MP3_URL;
    }

}
