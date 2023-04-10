package com.example.lr2;

import com.example.lr2.classes.CommercialBuilding;
import com.example.lr2.classes.ResidentialBuilding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ResidentialBuildingController {

    @FXML
    private TextField InputRooms;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField inputAddress;

    @FXML
    private TextField inputLevels;

    final int maxLength = 3;

    private ResidentialBuilding residentialBuilding;

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !InputRooms.getText().equals("")
                && !inputLevels.getText().equals("")){
            ResidentialBuilding newResidentialBuilding = new ResidentialBuilding(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    Integer.parseInt(InputRooms.getText())
            );
            if (MainController.buildings.contains(this.residentialBuilding)){
                MainController.buildings.set(MainController.buildings.indexOf(this.residentialBuilding), newResidentialBuilding);
                MainController.street.set(MainController.street.indexOf(this.residentialBuilding), newResidentialBuilding);

            } else {
                MainController.buildings.add(newResidentialBuilding);
            }
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
        InputRooms.setTextFormatter(textFormatterRooms);
        InputRooms.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                InputRooms.setText(oldValue);
            }
        });

    }

    public void setData(ResidentialBuilding residentialBuilding){
        this.residentialBuilding = residentialBuilding;
        inputAddress.setText(residentialBuilding.getAddress());
        inputLevels.setText(String.valueOf(residentialBuilding.getNumOfLevels()));
        InputRooms.setText(String.valueOf(residentialBuilding.getNumOfLivingRooms()));
    }

}
