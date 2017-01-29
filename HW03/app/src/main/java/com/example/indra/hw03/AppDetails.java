package com.example.indra.hw03;

import java.io.Serializable;

/**
 * Created by indra on 6/12/16.
 */
public class AppDetails implements Serializable{
    String id,App_Title,Developer_name,uri,small_photo_url,large_photo_url,app_price,release_date;

    @Override
    public String toString() {
        return "AppDetails{" +
                "id='" + id + '\'' +
                ", App_Title='" + App_Title + '\'' +
                ", Developer_name='" + Developer_name + '\'' +
                ", uri='" + uri + '\'' +
                ", small_photo_url='" + small_photo_url + '\'' +
                ", large_photo_url='" + large_photo_url + '\'' +
                ", app_price='" + app_price + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_Title() {
        return App_Title;
    }

    public void setApp_Title(String app_Title) {
        App_Title = app_Title;
    }

    public String getDeveloper_name() {
        return Developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        Developer_name = developer_name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSmall_photo_url() {
        return small_photo_url;
    }

    public void setSmall_photo_url(String small_photo_url) {
        this.small_photo_url = small_photo_url;
    }

    public String getLarge_photo_url() {
        return large_photo_url;
    }

    public void setLarge_photo_url(String large_photo_url) {
        this.large_photo_url = large_photo_url;
    }

    public String getApp_price() {
        return app_price;
    }

    public void setApp_price(String app_price) {
        this.app_price = app_price;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
