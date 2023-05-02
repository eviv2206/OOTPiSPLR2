package com.example.lr2.classes;

import com.example.lr2.abstractFactory.BuildingAbstractFactory;

public class BuildingClass {

    private final String fxmlName;
    private final Class<?> buildingClass;
    private final String buildingName;
    private final String title;
    private final BuildingAbstractFactory factory;

    public BuildingClass(Class<?> buildingClass, String buildingName,
                         String title, String fxmlName, BuildingAbstractFactory factory) {
        this.buildingClass = buildingClass;
        this.buildingName = buildingName;
        this.title = title;
        this.fxmlName = fxmlName;
        this.factory = factory;
    }

    public BuildingAbstractFactory getFactory() {
        return factory;
    }

    public Class<?> getBuildingClass() {
        return buildingClass;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return buildingName;
    }
}
