package com.example.lr2.classes;

public class Building {
    private int numOfLevels;
    private String address;

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
    public String toString(){
        return this.address;
    }


}
