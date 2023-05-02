package com.example.lr2.serializer.impl;

import com.example.lr2.abstractFactory.*;
import com.example.lr2.classes.*;
import com.example.lr2.serializer.Serializer;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TextSerializer implements Serializer {

    private final HashMap<String, BuildingAbstractFactory> buildingClassesMap = new HashMap<>();

    @Override
    public String getExtension() {
        return "*.txt";
    }

    @Override
    public String getDescription() {
        return "Text file";
    }

    @Override
    public void serialize(ArrayList<Building> items, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Building item : items) {
                ArrayList<Field> fs = getAllFields(item.getClass());
                bw.write(item.getClass().getSimpleName() + "\n");
                for (Field field : fs) {
                    field.setAccessible(true);
                    bw.write(field.getName()+ "\n");
                    bw.write(field.get(item) + "\n");
                }
            }
        } catch (IllegalAccessException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Building> deserialize(String filePath) {
        ArrayList<Building> list = new ArrayList<>();
        initBuildingClassesMap();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while (br.ready()){
                String className = br.readLine();
                Building building = buildingClassesMap.get(className).create();
                ArrayList<Field> fields = getAllFields(building.getClass());
                for (Field field: fields){
                    Field objField = getField(building.getClass(), br.readLine());
                    objField.setAccessible(true);
                    objField.set(building, getValueField(field, br.readLine()));
                }
                list.add(building);
            }
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public ArrayList<Building> deserialize(byte[] byteArray) {
        File tempFile;
        ArrayList<Building> list;
        try {
            tempFile = File.createTempFile("tempfile", ".txt");
            Path tempFilePath = tempFile.toPath();
            Files.write(tempFilePath, byteArray);
            list = deserialize(tempFile.getPath());
            Files.delete(tempFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static ArrayList<Field> getAllFields(Class<?> clazz) {

        // получаем все поля текущего класса
        Field[] declaredFields = clazz.getDeclaredFields();
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));

        // получаем все поля родительского класса
        Class<?> parentClass = clazz.getSuperclass();
        if (parentClass != null) {
            fields.addAll(getAllFields(parentClass));
        }

        return fields;
    }

    private Field getField(Class<?> clazz, String fieldName){
        Field field;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e){
            field = getField(clazz.getSuperclass(), fieldName);
        }
       return field;
    }

    private void initBuildingClassesMap(){
        buildingClassesMap.put(Building.class.getSimpleName(), new BuildingFactory());
        buildingClassesMap.put(ApartmentBuilding.class.getSimpleName(), new ApartmentBuildingFactory());
        buildingClassesMap.put(CommercialBuilding.class.getSimpleName(), new CommercialBuildingFactory());
        buildingClassesMap.put(IndividualHouse.class.getSimpleName(), new IndividualHouseFactory());
        buildingClassesMap.put(ResidentialBuilding.class.getSimpleName(), new ResidentialBuildingFactory());
        buildingClassesMap.put(StateBuilding.class.getSimpleName(), new StateBuildingFactory());
    }

    private Object getValueField(Field field, String value){
        if (field.getType().equals(int.class)){
            return Integer.parseInt(value);
        } else if (field.getType().equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (field.getType().equals(String.class)){
            return value;
        } else if (field.getType().equals(TypeStateBuilding.class)){
            return Enum.valueOf(TypeStateBuilding.class, value);
        }
        return value;
    }
}
