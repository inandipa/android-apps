package com.example.indra.intents;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by indra on 5/31/2016.
 */
public class Person implements Parcelable {

    String name;
    String address;
    double age;

    public Person(String name,String address,double age) {
    this.address = address;
        this.name=name;
        this.age = age;

    }




    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeDouble(age);
        dest.writeString(name);
        dest.writeString(address);
    }

    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private Person(Parcel in) {
        this.age = in.readDouble();
        this.name = in.readString();
        this.address = in.readString();

    }

}
