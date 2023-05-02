package com.example.lr2.classes;

import java.io.Serializable;

public class StateBuilding extends Building implements Serializable {
    private final TypeStateBuilding typeBuilding;

    public StateBuilding(){
        super();
        this.typeBuilding = null;
    }

    public StateBuilding(int numOfLevels, String address, TypeStateBuilding type) {
        super(numOfLevels, address);
        this.typeBuilding = type;
    }

    public TypeStateBuilding getTypeBuilding() {
        return typeBuilding;
    }
}
