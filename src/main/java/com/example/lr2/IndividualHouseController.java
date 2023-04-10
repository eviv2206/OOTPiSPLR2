package com.example.lr2;

import com.example.lr2.classes.CommercialBuilding;
import com.example.lr2.classes.IndividualHouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class IndividualHouseController {

    @FXML
    private TextField InputOwners;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputLevels;

    @FXML
    private TextField inputRooms;

    final int maxLength = 3;

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !inputRooms.getText().equals("")
                && !inputLevels.getText().equals("")) {
            MainController.buildings.add(new IndividualHouse(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    Integer.parseInt(inputRooms.getText()), InputOwners.getText()
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
        TextFormatter<String> textFormatterRooms = new TextFormatter<>(filter);

        inputLevels.setTextFormatter(textFormatterLevels);
        inputLevels.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputLevels.setText(oldValue);
            }
        });
        inputRooms.setTextFormatter(textFormatterRooms);
        inputRooms.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputRooms.setText(oldValue);
            }
        });

    }

    public void setData(IndividualHouse individualHouse){
        inputAddress.setText(individualHouse.getAddress());
        inputRooms.setText(String.valueOf(individualHouse.getNumOfLivingRooms()));
        inputLevels.setText(String.valueOf(individualHouse.getNumOfLevels()));
        InputOwners.setText(String.valueOf(individualHouse.getHumanOwner()));
    }

}
