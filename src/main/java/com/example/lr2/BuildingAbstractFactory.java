package com.example.lr2;

import com.example.lr2.classes.Building;

public interface BuildingAbstractFactory {

    void add(String title, String formPath);

    void edit(Building building, String title, String formPath);

}
