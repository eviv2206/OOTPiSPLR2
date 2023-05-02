package com.example.lr2.serializer.impl;

import com.example.lr2.classes.Building;
import com.example.lr2.serializer.Serializer;

import java.io.*;
import java.util.ArrayList;

public class BinarySerializer implements Serializer {
    @Override
    public String getExtension() {
        return "*.bin";
    }

    @Override
    public String getDescription() {
        return "Binary file";
    }

    @Override
    public void serialize(ArrayList<Building> buildings, String directoryPath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(directoryPath))){
            oos.writeObject(buildings);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public ArrayList<Building> deserialize(String filePath){
        ArrayList<Building> buildingList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            buildingList = (ArrayList<Building>) ois.readObject();
        } catch (ClassNotFoundException | IOException e){
            System.err.println(e.getMessage());
        }
        return buildingList;
    }

    @Override
    public ArrayList<Building> deserialize(byte[] byteArray) {
        ArrayList<Building> buildingList = new ArrayList<>();
        try (ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            buildingList = (ArrayList<Building>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return buildingList;
    }
}
