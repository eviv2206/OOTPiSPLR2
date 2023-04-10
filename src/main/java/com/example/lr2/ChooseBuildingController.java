package com.example.lr2;

import com.example.lr2.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ChooseBuildingController {

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<Building> cmbBox;

    @FXML
    void submit(ActionEvent event) {
        if (cmbBox.getSelectionModel().getSelectedItem() != null) {
            MainController.street.add(cmbBox.getSelectionModel().getSelectedItem());
        }
        Scene scene = btnSubmit.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    @FXML
    void initialize() {
        cmbBox.setItems(MainController.buildings);
        cmbBox.getSelectionModel().selectFirst();
    }

}
