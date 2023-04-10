package com.example.lr2;

import com.example.lr2.classes.CommercialBuilding;
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
import java.util.regex.Pattern;

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

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !inputLevels.getText().equals("")){
            MainController.buildings.add(new StateBuilding(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    cmbType.getValue()
            ));
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

    public void setData(StateBuilding stateBuilding){
        inputAddress.setText(stateBuilding.getAddress());
        inputLevels.setText(String.valueOf(stateBuilding.getNumOfLevels()));
        cmbType.getSelectionModel().select(stateBuilding.getType());
    }

}
