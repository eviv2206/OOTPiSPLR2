package com.example.lr2.classes;

import java.io.Serializable;

public class ResidentialBuilding extends Building implements Serializable {
    int numOfLivingRooms;

    public ResidentialBuilding(){
        super();
    }

    public ResidentialBuilding(int numOfLevels, String address, int numOfLivingRooms){
        super(numOfLevels, address);
        this.numOfLivingRooms = numOfLivingRooms;
    }

    public int getNumOfLivingRooms() {
        return numOfLivingRooms;
    }

    public void setNumOfLivingRooms(int numOfLivingRooms) {
        this.numOfLivingRooms = numOfLivingRooms;
    }
}
