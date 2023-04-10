package com.example.lr2;

import com.example.lr2.classes.IndividualHouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

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

    private IndividualHouse individualHouse;

    @FXML
    void submit(ActionEvent event) {
        if (!inputAddress.getText().equals("") && !inputRooms.getText().equals("")
                && !inputLevels.getText().equals("")) {
            IndividualHouse newIndividualHouse = new IndividualHouse(
                    Integer.parseInt(inputLevels.getText()), inputAddress.getText(),
                    Integer.parseInt(inputRooms.getText()), InputOwners.getText()
            );
            if (MainController.buildings.contains(this.individualHouse)){
                MainController.buildings.set(MainController.buildings.indexOf(this.individualHouse), newIndividualHouse);
                if (MainController.street.contains(this.individualHouse)) {
                    MainController.street.set(MainController.street.indexOf(this.individualHouse), newIndividualHouse);
                }
            } else {
                MainController.buildings.add(newIndividualHouse);
            }
            Scene scene = btnSubmit.getScene();
            Stage currStage = (Stage) scene.getWindow();
            currStage.close();
        }
    }

    @FXML
    void initialize() {
        UnaryOperator<TextFormatter.Change> filter = Validation.getFilter();
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
        this.individualHouse = individualHouse;
        inputAddress.setText(individualHouse.getAddress());
        inputRooms.setText(String.valueOf(individualHouse.getNumOfLivingRooms()));
        inputLevels.setText(String.valueOf(individualHouse.getNumOfLevels()));
        InputOwners.setText(String.valueOf(individualHouse.getHumanOwner()));
    }

}
