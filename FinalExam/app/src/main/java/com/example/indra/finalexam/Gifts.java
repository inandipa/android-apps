package com.example.indra.finalexam;

import java.io.Serializable;

/**
 * Created by indra on 6/28/16.
 */
public class Gifts implements Serializable {
    String gift,ImageUrl,key;
    int price;


    @Override
    public String toString() {
        return "Gifts{" +
                "gift='" + gift + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", price=" + price +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }
}
