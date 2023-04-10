package com.example.lr2.classes;

public class ApartmentBuilding extends ResidentialBuilding {
    int numOfApartments;

    public ApartmentBuilding(int numOfLevels, String address, int numOfLivingRooms, int numOfApartments){
        super(numOfLevels, address, numOfLivingRooms);
        this.numOfApartments = numOfApartments;
    }

    public int getNumOfApartments(){
        return this.numOfApartments;
    }

    public void setNumOfApartments(int numOfApartments) {
        this.numOfApartments = numOfApartments;
    }
}
