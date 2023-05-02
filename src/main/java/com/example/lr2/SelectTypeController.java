package com.example.lr2;

import com.example.lr2.abstractFactory.*;
import com.example.lr2.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SelectTypeController {
    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<BuildingClass> cmbBox;

    private BuildingClass[] classes;

    @FXML
    void submit(ActionEvent event) {
        BuildingClass buildingClass = cmbBox.getSelectionModel().getSelectedItem();
        BuildingAbstractFactory abstractFactory = buildingClass.getFactory();
        abstractFactory.add(buildingClass.getTitle(), buildingClass.getFxmlName());
        Scene scene = cmbBox.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    @FXML
    void initialize() {
        ObservableList<BuildingClass> classes = FXCollections.observableArrayList(MainController.classes);
        cmbBox.setItems(classes);
        cmbBox.getSelectionModel().selectFirst();
    }

    public void setClasses(BuildingClass[] classes){
        this.classes = classes;
    }
}
