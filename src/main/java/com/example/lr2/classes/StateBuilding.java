package com.example.lr2.classes;

public class StateBuilding extends Building{
    TypeStateBuilding type;
    public StateBuilding(int numOfLevels, String address, TypeStateBuilding type) {
        super(numOfLevels, address);
        this.type = type;
    }

    public TypeStateBuilding getType() {
        return type;
    }
}
