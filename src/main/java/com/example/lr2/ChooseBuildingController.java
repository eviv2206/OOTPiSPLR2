package com.example.lr2;

import com.example.lr2.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChooseBuildingController {

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<Building> cmbBox;

    @FXML
    void submit(ActionEvent event) {
        if (cmbBox.getSelectionModel().getSelectedItem() != null) {
            final Building building = MainController.buildings.get(MainController.buildings.indexOf(cmbBox.getSelectionModel().getSelectedItem()));
            MainController.street.add(building);
        }
        Scene scene = btnSubmit.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    @FXML
    void initialize() {
        ObservableList<Building> list = FXCollections.observableArrayList(new ArrayList<>(MainController.buildings));
        list.removeIf(MainController.street::contains);
        cmbBox.setItems(list);
        cmbBox.getSelectionModel().selectFirst();
    }

}
