package com.example.lr2;

import com.example.lr2.classes.Building;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class BuildingController {
    @FXML
    private Button btnSubmit;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputLevels;

    private Building building;

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !inputLevels.getText().equals("")) {
            final Building newBuilding = new Building(Integer.parseInt(inputLevels.getText()), inputAddress.getText());
            if (MainController.buildings.contains(this.building)) {
                MainController.buildings.set(MainController.buildings.indexOf(this.building), newBuilding);
                MainController.street.set(MainController.street.indexOf(this.building), newBuilding);

            } else {
                MainController.buildings.add(newBuilding);
            }
            Scene scene = btnSubmit.getScene();
            Stage currStage = (Stage) scene.getWindow();
            currStage.close();
        }
    }

    final int maxLength = 3;

    @FXML
    void initialize() {
        Pattern pattern = Pattern.compile("[0-9]*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (pattern.matcher(text).matches()) {
                return change;
            } else {
                return null;
            }
        };
        TextFormatter<String> textFormatterLevels = new TextFormatter<>(filter);

        inputLevels.setTextFormatter(textFormatterLevels);
        inputLevels.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputLevels.setText(oldValue);
            }
        });
    }

    public void setData(Building building) {
        this.building = building;
        inputAddress.setText(building.getAddress());
        inputLevels.setText(String.valueOf(building.getNumOfLevels()));
    }
}
