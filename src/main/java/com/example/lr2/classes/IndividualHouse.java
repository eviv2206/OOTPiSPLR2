package com.example.lr2.classes;

public class IndividualHouse extends ResidentialBuilding {
    String humanOwner;

    public IndividualHouse(int numOfLevels, String address, int numOfLivingRooms, String humanOwner){
        super(numOfLevels, address, numOfLivingRooms);
        this.humanOwner = humanOwner;
    }

    public String getHumanOwner() {
        return humanOwner;
    }

    public void setHumanOwner(String humanOwner) {
        this.humanOwner = humanOwner;
    }
}
