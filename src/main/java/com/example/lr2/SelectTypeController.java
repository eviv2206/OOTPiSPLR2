package com.example.lr2;

import com.example.lr2.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SelectTypeController {
    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<String> cmbBox;

    final Map<String, String> formsMap = new HashMap<>();

    @FXML
    void submit(ActionEvent event) {
        String className = cmbBox.getSelectionModel().getSelectedItem();
        GetFactory getFactory = new GetFactory();
        BuildingAbstractFactory abstractFactory = getFactory.getFactory(className);
        abstractFactory.add(className, formsMap.get(className));
        Scene scene = cmbBox.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    @FXML
    void initialize() {
        cmbBox.getItems().addAll(
                Building.class.getSimpleName(),
                ApartmentBuilding.class.getSimpleName(),
                CommercialBuilding.class.getSimpleName(),
                IndividualHouse.class.getSimpleName(),
                ResidentialBuilding.class.getSimpleName(),
                StateBuilding.class.getSimpleName()
        );
        cmbBox.getSelectionModel().selectFirst();

        formsMap.put(Building.class.getSimpleName(), "BuildingForm.fxml");
        formsMap.put(ApartmentBuilding.class.getSimpleName(), "ApartmentBuildingForm.fxml");
        formsMap.put(CommercialBuilding.class.getSimpleName(), "CommercialBuildingForm.fxml");
        formsMap.put(IndividualHouse.class.getSimpleName(), "IndividualHouseForm.fxml");
        formsMap.put(ResidentialBuilding.class.getSimpleName(), "ResidentialBuildingForm.fxml");
        formsMap.put(StateBuilding.class.getSimpleName(), "StateBuildingForm.fxml");
    }
}
