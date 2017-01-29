package com.example.indra.finalexam;

import com.firebase.client.ServerValue;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by indra on 6/28/16.
 */
public class PersonDetails implements Serializable {
    String name,key;
    ArrayList<Gifts> gifts  =  new ArrayList<>();
    int budgetGiven,budgetSpent,items =0;
    boolean i = true;

    @Override
    public String toString() {
        return "PersonDetails{" +
                "budgetGiven=" + budgetGiven +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", Gifts=" + gifts +
                ", budgetSpent=" + budgetSpent +
                ", items=" + items +
                '}';
    }

    public boolean isI() {
        return i;
    }

    public void setI(boolean i) {
        this.i = i;
    }

    public ArrayList<com.example.indra.finalexam.Gifts> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<com.example.indra.finalexam.Gifts> gifts) {
        this.gifts = gifts;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getBudgetGiven() {
        return budgetGiven;
    }

    public void setBudgetGiven(int budgetGiven) {
        this.budgetGiven = budgetGiven;
    }

    public int getBudgetSpent() {
        return budgetSpent;
    }

    public void setBudgetSpent(int budgetSpent) {
        this.budgetSpent = budgetSpent;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
