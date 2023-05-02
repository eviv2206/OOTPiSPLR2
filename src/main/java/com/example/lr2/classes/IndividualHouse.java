package com.example.lr2.classes;

import java.io.Serializable;

public class IndividualHouse extends ResidentialBuilding implements Serializable {
    String humanOwner;

    public IndividualHouse(){
        super();
    }

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
