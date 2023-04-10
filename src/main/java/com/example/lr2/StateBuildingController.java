package com.example.lr2;

import com.example.lr2.classes.StateBuilding;
import com.example.lr2.classes.TypeStateBuilding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

public class StateBuildingController {

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<TypeStateBuilding> cmbType;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputLevels;

    final int maxLength = 3;

    private StateBuilding stateBuilding;

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !inputLevels.getText().equals("")) {
            StateBuilding newStateBuilding = new StateBuilding(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    cmbType.getValue()
            );
            if (MainController.buildings.contains(this.stateBuilding)) {
                MainController.buildings.set(MainController.buildings.indexOf(this.stateBuilding), newStateBuilding);
                if (MainController.street.contains(this.stateBuilding)) {
                    MainController.street.set(MainController.street.indexOf(this.stateBuilding), newStateBuilding);
                }

            } else {
                MainController.buildings.add(newStateBuilding);
            }
            Scene scene = btnSubmit.getScene();
            Stage currStage = (Stage) scene.getWindow();
            currStage.close();
        }
    }

    @FXML
    void initialize() {
        cmbType.getItems().addAll(
                TypeStateBuilding.park,
                TypeStateBuilding.school,
                TypeStateBuilding.hospital,
                TypeStateBuilding.university
        );
        cmbType.getSelectionModel().selectFirst();

        UnaryOperator<TextFormatter.Change> filter = Validation.getFilter();
        TextFormatter<String> textFormatterLevels = new TextFormatter<>(filter);

        inputLevels.setTextFormatter(textFormatterLevels);
        inputLevels.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputLevels.setText(oldValue);
            }
        });
    }

    public void setData(StateBuilding stateBuilding) {
        this.stateBuilding = stateBuilding;
        inputAddress.setText(stateBuilding.getAddress());
        inputLevels.setText(String.valueOf(stateBuilding.getNumOfLevels()));
        cmbType.getSelectionModel().select(stateBuilding.getType());
    }

}
