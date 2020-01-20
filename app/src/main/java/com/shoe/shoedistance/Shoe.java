package com.shoe.shoedistance;

import java.io.Serializable;
import java.util.ArrayList;

public class Shoe implements Serializable {


    private int distance;
    private String model;
    private String brand;

    public Shoe (String model, String brand, int distance) {
        this.model = model;
        this.brand = brand;
        this.distance = distance;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



}
