package com.example.lr2.classes;

import java.io.Serializable;
import java.util.Objects;

public class Building implements Serializable {
    private int numOfLevels;
    private String address;

    public Building(){
        this.numOfLevels = 0;
        this.address = "";
    }

    public Building(int numOfLevels, String address){
        this.numOfLevels = numOfLevels;
        this.address = address;
    }

    public int getNumOfLevels() {
        return numOfLevels;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumOfLevels(int numOfLevels) {
        this.numOfLevels = numOfLevels;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Building other)) {
            return false;
        }
        return Objects.equals(address, other.address);
    }


}
