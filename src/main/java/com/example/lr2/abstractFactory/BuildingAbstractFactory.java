package com.example.lr2.abstractFactory;

import com.example.lr2.classes.Building;

public interface BuildingAbstractFactory {
    
    String resourcePath = "/com/example/lr2/";

    void add(String title, String formPath);

    void edit(Building building, String title, String formPath);

    Building create();

}
