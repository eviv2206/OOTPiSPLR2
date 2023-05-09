package com.example.lr2.serializer.impl;

import com.example.lr2.abstractFactory.*;
import com.example.lr2.classes.*;
import com.example.lr2.serializer.Serializer;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TextSerializer implements Serializer {

    private final HashMap<String, BuildingAbstractFactory> buildingClassesMap = new HashMap<>();

    @Override
    public String getFilterExtension() {
        return "*.txt";
    }

    public String getExtension(){ return ".txt";}

    @Override
    public String getDescription() {
        return "Text file";
    }

    @Override
    public void serialize(ArrayList<Building> items, OutputStream os) {
        try{
            for (Building item : items) {
                ArrayList<Field> fs = getAllFields(item.getClass());
                os.write((item.getClass().getSimpleName() + "\n").getBytes());
                for (Field field : fs) {
                    field.setAccessible(true);
                    os.write((field.getName()+ "\n").getBytes());
                    os.write((field.get(item) + "\n").getBytes());
                }
            }
        } catch (IllegalAccessException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Building> deserialize(String filePath) {
        ArrayList<Building> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            this.setList(br, list);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public ArrayList<Building> deserialize(byte[] byteArray) {
        ArrayList<Building> list = new ArrayList<>();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            this.setList(bufferedReader, list);
        } catch (IOException | IllegalAccessException e) {
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

    private void setList(BufferedReader br, ArrayList<Building> list) throws IOException, IllegalAccessException {
        String line;
        initBuildingClassesMap();
        while ((line = br.readLine()) != null){
            Building building = buildingClassesMap.get(line).create();
            ArrayList<Field> fields = getAllFields(building.getClass());
            for (Field field: fields){
                Field objField = getField(building.getClass(), br.readLine());
                objField.setAccessible(true);
                objField.set(building, getValueField(field, br.readLine()));
            }
            list.add(building);
        }

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
