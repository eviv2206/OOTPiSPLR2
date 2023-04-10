package com.example.lr2;

import com.example.lr2.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class ApartmentBuildingController {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputLevels;

    @FXML
    private TextField inputNumApart;

    @FXML
    private TextField inputRooms;

    final int maxLength = 3;

    private ApartmentBuilding apartmentBuilding;

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !inputLevels.getText().equals("")
                && !inputNumApart.getText().equals("") && !inputRooms.getText().equals("")){
            ApartmentBuilding newApartmentBuilding = new ApartmentBuilding(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    Integer.parseInt(inputRooms.getText()), Integer.parseInt(inputNumApart.getText()));
            if (MainController.buildings.contains(this.apartmentBuilding)){
                MainController.buildings.set(MainController.buildings.indexOf(this.apartmentBuilding), newApartmentBuilding);
                MainController.street.set(MainController.street.indexOf(this.apartmentBuilding), newApartmentBuilding);

            } else {
                MainController.buildings.add(newApartmentBuilding);
            }
        }
        Scene scene = btnSubmit.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
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
        TextFormatter<String> textFormatterAparts = new TextFormatter<>(filter);
        TextFormatter<String> textFormatterRooms = new TextFormatter<>(filter);

        inputLevels.setTextFormatter(textFormatterLevels);
        inputLevels.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputLevels.setText(oldValue);
            }
        });
        inputNumApart.setTextFormatter(textFormatterAparts);
        inputNumApart.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputNumApart.setText(oldValue);
            }
        });
        inputRooms.setTextFormatter(textFormatterRooms);
        inputRooms.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                inputRooms.setText(oldValue);
            }
        });

    }

    public void setData(ApartmentBuilding apartmentBuilding){
        this.apartmentBuilding = apartmentBuilding;
        inputAddress.setText(apartmentBuilding.getAddress());
        inputRooms.setText(String.valueOf(apartmentBuilding.getNumOfLivingRooms()));
        inputLevels.setText(String.valueOf(apartmentBuilding.getNumOfLevels()));
        inputNumApart.setText(String.valueOf(apartmentBuilding.getNumOfApartments()));
    }

}
