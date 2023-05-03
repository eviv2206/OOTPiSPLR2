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

import java.io.*;
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
        if (rbtnBuilding.isSelected()) {
            listToSave = MainController.buildings;
        } else {
            listToSave = MainController.street;
        }
        OutputStream outputStream = new ByteArrayOutputStream();
        UtilFileChooser ufc = new UtilFileChooser(MainController.serializerMap, MainController.pluginMap);
        if (!cmbEncoding.getValue().equals("None")) {
            String encodeExt = MainController.pluginMap.get(cmbEncoding.getValue()).getExtension().substring(1);
            currFile = ufc.getSavingFile(encodeExt);
            serialize(listToSave, outputStream);
            encode(outputStream);
        } else {
            currFile = ufc.getSavingFile("");
            serialize(listToSave, outputStream);
        }
        if (currFile != null) {
            saveOutputStreamToFile(outputStream, currFile.getAbsolutePath());
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

    private void serialize(ObservableList<Building> listToSave, OutputStream os) {
        ArrayList<String> listExtensions = UtilFileChooser.getExtensions(currFile.getAbsolutePath());
        MainController.serializerMap.get(listExtensions.get(0)).serialize(new ArrayList<>(listToSave), os);
    }

    private void encode(OutputStream os) {
        try {
            byte[] fileBytes = MainController.pluginMap.get(cmbEncoding.getValue()).encode(((ByteArrayOutputStream) os).toByteArray());
            ((ByteArrayOutputStream) os).reset();
            os.write(fileBytes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void saveOutputStreamToFile(OutputStream outputStream, String filePath) {
        byte[] outputBytes = ((ByteArrayOutputStream) outputStream).toByteArray();
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(outputBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
