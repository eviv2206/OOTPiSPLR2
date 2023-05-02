package com.example.lr2.serializer;

import com.example.lr2.classes.Building;

import java.util.ArrayList;

public interface Serializer {

    String getExtension();

    String getDescription();

    void serialize(ArrayList<Building> items, String filePath);

    ArrayList<Building> deserialize(String filePath);

    ArrayList<Building> deserialize(byte[] byteArray);
}
