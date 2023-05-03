package com.example.lr2.serializer;

import com.example.lr2.classes.Building;

import java.io.OutputStream;
import java.util.ArrayList;

public interface Serializer {

    String getFilterExtension();

    String getDescription();

    void serialize(ArrayList<Building> items, OutputStream os);

    ArrayList<Building> deserialize(String filePath);

    ArrayList<Building> deserialize(byte[] byteArray);
}
