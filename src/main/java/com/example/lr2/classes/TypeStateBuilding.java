package com.example.lr2.classes;

public enum TypeStateBuilding {
    hospital("hospital"),
    school("school"),
    university("university"),
    park("park");
    final String data;
    TypeStateBuilding(String data){
        this.data = data;
    }
}
