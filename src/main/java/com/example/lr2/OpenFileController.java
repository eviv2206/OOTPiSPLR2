package com.example.lr2;

import com.example.lr2.classes.Building;
import com.example.lr2.utils.UtilFileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class OpenFileController {

    @FXML
    private Button btnSubmit;

    @FXML
    private RadioButton rbtnBuilding;

    @FXML
    private RadioButton rbtnStreet;

    @FXML
    private ToggleGroup savingObj;

    @FXML
    void execute(ActionEvent event) {
        ObservableList<Building> listDeserialized;
        if (rbtnBuilding.isSelected()){
            listDeserialized = deserialize();
            MainController.buildings.clear();
            MainController.buildings.addAll(listDeserialized);
        } else {
            listDeserialized = deserialize();
            MainController.street.clear();
            for (Building building: listDeserialized){
                if (!MainController.buildings.contains(building)){
                    MainController.buildings.add(building);
                }
                MainController.street.add(building);
            }
        }
        Scene scene = btnSubmit.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    private ObservableList<Building> deserialize()  {
        UtilFileChooser ufc = new UtilFileChooser(MainController.serializerMap, MainController.pluginMap);
        File file = ufc.getOpenFile();
        if (file != null) {
            ArrayList<String> listExtensions = UtilFileChooser.getExtensions(file.getAbsolutePath());
            if (listExtensions.size() > 1 && isEncoded(listExtensions.get(1))) {
                try {
                    byte[] fileBytes = Files.readAllBytes(Path.of(file.getPath()));
                    fileBytes = MainController.pluginMap.get(listExtensions.get(1)).decode(fileBytes);
                    return FXCollections.observableArrayList(MainController.serializerMap.get(listExtensions.get(0)).deserialize(fileBytes));
                } catch (IOException e) {

                    System.err.println(e.getMessage());
                }
            } else {
                return FXCollections.observableArrayList(MainController.serializerMap.get(listExtensions.get(0)).deserialize(file.getPath()));
            }
        }
        return null;
    }

    private boolean isEncoded(String ext) {
        for (String key : MainController.pluginMap.keySet()) {
            if (MainController.pluginMap.get(key).toString().equals(ext)) {
                return true;
            }
        }
        return false;
    }


}
