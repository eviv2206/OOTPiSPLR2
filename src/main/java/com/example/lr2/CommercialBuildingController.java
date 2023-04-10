package com.example.lr2;

import com.example.lr2.classes.CommercialBuilding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class CommercialBuildingController {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputLevels;

    @FXML
    private TextField inputRooms;

    @FXML
    private TextField inputTentants;

    final int maxLength = 3;

    @FXML
    void submit(ActionEvent event) {
        if (!inputTentants.getText().equals("") && !inputAddress.getText().equals("")
                && !inputRooms.getText().equals("") && !inputLevels.getText().equals("")){
            MainController.buildings.add(new CommercialBuilding(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    Integer.parseInt(inputTentants.getText()), Integer.parseInt(inputRooms.getText())
            ));
            Scene scene = btnSubmit.getScene();
            Stage currStage = (Stage) scene.getWindow();
            currStage.close();
        }
    }

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
        TextFormatter<String> textFormatterTentants = new TextFormatter<>(filter);
        TextFormatter<String> textFormatterRooms = new TextFormatter<>(filter);

        inputLevels.setTextFormatter(textFormatterLevels);
        inputLevels.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputLevels.setText(oldValue);
            }
        });
        inputTentants.setTextFormatter(textFormatterTentants);
        inputTentants.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputTentants.setText(oldValue);
            }
        });
        inputRooms.setTextFormatter(textFormatterRooms);
        inputRooms.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputRooms.setText(oldValue);
            }
        });

    }

    public void setData(CommercialBuilding commercialBuilding){
        inputAddress.setText(commercialBuilding.getAddress());
        inputLevels.setText(String.valueOf(commercialBuilding.getNumOfLevels()));
        inputRooms.setText(String.valueOf(commercialBuilding.getNumOfAvailableRooms()));
        inputTentants.setText(String.valueOf(commercialBuilding.getNumOfTenants()));
    }

}
