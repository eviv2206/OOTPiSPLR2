package com.example.lr2.serializer.impl;

import com.example.lr2.classes.*;
import com.example.lr2.serializer.Serializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;


public class JsonSerializer implements Serializer {

    private final RuntimeTypeAdapterFactory<Building> typeAdapterFactory = RuntimeTypeAdapterFactory.of(Building.class, "type")
            .registerSubtype(ApartmentBuilding.class, "apartment building").registerSubtype(CommercialBuilding.class, "commercial building")
            .registerSubtype(IndividualHouse.class, "individual house").registerSubtype(ResidentialBuilding.class, "residential building")
            .registerSubtype(StateBuilding.class, "state building");
    @Override
    public String getFilterExtension() {
        return "*.json";
    }

    @Override
    public String getDescription() {
        return "JSON file";
    }

    @Override
    public void serialize(ArrayList<Building> items, OutputStream os){
        Gson gson = new Gson().newBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Type type = new TypeToken<ArrayList<Building>>(){}.getType();
        try{
            os.write(gson.toJson(items, type).getBytes());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Building> deserialize(String filePath){
        Gson gson = new Gson().newBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Type type = new TypeToken<ArrayList<Building>>(){}.getType();
        try(FileReader fr = new FileReader(filePath)){
            return gson.fromJson(fr, type);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Building> deserialize(byte[] byteArray) {
        Gson gson = new Gson().newBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Type type = new TypeToken<ArrayList<Building>>(){}.getType();
        String json = new String(byteArray, StandardCharsets.UTF_8);
        return gson.fromJson(json, type);
    }
}
