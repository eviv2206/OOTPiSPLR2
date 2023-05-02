package com.example.lr2.classes;

import java.io.Serializable;

public class CommercialBuilding extends Building implements Serializable {
    int numOfAvailableRooms;
    int numOfTenants;

    public CommercialBuilding(){
        super();
    }

    public CommercialBuilding(int numOfLevels, String address, int numOfTenants, int numOfAvailableRooms){
        super(numOfLevels, address);
        this.numOfTenants = numOfTenants;
        this.numOfAvailableRooms = numOfAvailableRooms;
    }

    public int getNumOfAvailableRooms() {
        return numOfAvailableRooms;
    }

    public int getNumOfTenants() {
        return numOfTenants;
    }

    public void setNumOfAvailableRooms(int numOfAvailableRooms) {
        this.numOfAvailableRooms = numOfAvailableRooms;
    }

    public void setNumOfTenants(int numOfTenants) {
        this.numOfTenants = numOfTenants;
    }
}
