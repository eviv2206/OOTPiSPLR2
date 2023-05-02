package com.example.lr2;

import com.example.lr2.classes.Building;
import com.example.lr2.utils.UtilFileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ChooseEncodingController {

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<String> cmbEncoding;


    @FXML
    private RadioButton rbtnBuilding;

    @FXML
    private RadioButton rbtnStreet;

    @FXML
    private ToggleGroup savingObj;


    private File currFile;


    @FXML
    void execute(ActionEvent event) {
        ObservableList<Building> listToSave;
        if (rbtnBuilding.isSelected()){
            listToSave = MainController.buildings;
        } else {
            listToSave = MainController.street;
        }
        if (!cmbEncoding.getValue().equals("None")){
            serialize(listToSave);
            encode();
        } else {
            serialize(listToSave);
        }
        currFile = null;
        Scene scene = btnSubmit.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    @FXML
    void initialize() {
        ObservableList<String> plugins = FXCollections.observableArrayList(MainController.pluginMap.keySet());
        plugins.add(0, "None");
        cmbEncoding.setItems(plugins);
        cmbEncoding.getSelectionModel().selectFirst();
    }

    private void serialize(ObservableList<Building> listToSave){
        UtilFileChooser ufc = new UtilFileChooser(MainController.serializerMap, MainController.pluginMap);
        currFile = ufc.getSavingFile();
        if (currFile != null){
            String ext = UtilFileChooser.getExtension(currFile.getAbsolutePath());
            MainController.serializerMap.get(ext).serialize(new ArrayList<>(listToSave), currFile.getPath());
        }
    }

    private void encode(){
        try {
            byte[] fileBytes = Files.readAllBytes(Path.of(currFile.getPath()));
            String newPath = currFile.getPath() + "." + MainController.pluginMap.get(cmbEncoding.getValue()).toString();
            currFile.renameTo(new File(newPath));
            fileBytes = MainController.pluginMap.get(cmbEncoding.getValue()).encode(fileBytes);
            FileOutputStream fw = new FileOutputStream(newPath);
            fw.write(fileBytes);
            fw.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



}
