package com.example.lr2.utils;

import com.example.lr2.plugins.Plugin;
import com.example.lr2.serializer.Serializer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class UtilFileChooser {

    private final HashMap<String, Serializer> serializerMap;

    private final HashMap<String, Plugin> pluginMap;

    public UtilFileChooser(HashMap<String, Serializer> serializerMap, HashMap<String, Plugin> pluginMap){
        this.pluginMap = pluginMap;
        this.serializerMap = serializerMap;
    }

    public static ArrayList<String> getExtensions(String absolutePath){
        ArrayList<String> list = new ArrayList<>();
        if (absolutePath.indexOf('.') != absolutePath.lastIndexOf('.')){
            list.add(absolutePath.substring(absolutePath.indexOf('.') + 1, absolutePath.lastIndexOf('.')));
        }
        list.add(absolutePath.substring(absolutePath.lastIndexOf(".") + 1));
        return list;
    }

    public File getSavingFile(String encodingExt){
        FileChooser fc = new FileChooser();
        getExtensionFiltersToSave(fc, encodingExt);
        return fc.showSaveDialog(new Stage());
    }

    public File getOpenFile(){
        FileChooser fc = new FileChooser();
        getExtensionFiltersToOpen(fc);
        return fc.showOpenDialog(new Stage());
    }

    private void getExtensionFiltersToSave(FileChooser fc, String ext){
        for (String key : serializerMap.keySet()){
            Serializer s = serializerMap.get(key);
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(s.getDescription() + " encoded", s.getFilterExtension()+ext));
        }
    }

    private void getExtensionFiltersToOpen(FileChooser fc){
        for (String key : pluginMap.keySet()){
            Plugin plugin = pluginMap.get(key);
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(plugin.getDescription(), plugin.getExtension()));
        }
        for (String key : serializerMap.keySet()){
            Serializer s = serializerMap.get(key);
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(s.getDescription(), s.getFilterExtension()));
        }
    }
}
