package com.example.lr2.classes;

public class ResidentialBuilding extends Building {
    int numOfLivingRooms;

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
