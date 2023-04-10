package com.example.lr2;

import com.example.lr2.abstractFactory.*;
import com.example.lr2.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SelectTypeController {
    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<BuildingClass> cmbBox;

    @FXML
    void submit(ActionEvent event) {
        BuildingClass buildingClass = cmbBox.getSelectionModel().getSelectedItem();
        BuildingAbstractFactory abstractFactory = buildingClass.getFactory();
        abstractFactory.add(buildingClass.getTitle(), buildingClass.getFxmlName());
        Scene scene = cmbBox.getScene();
        Stage currStage = (Stage) scene.getWindow();
        currStage.close();
    }

    @FXML
    void initialize() {
        ObservableList<BuildingClass> classes = FXCollections.observableArrayList(
                new BuildingClass(Building.class, "Building", "Create building", "BuildingForm.fxml" , new BuildingFactory()),
                new BuildingClass(ApartmentBuilding.class, "Apartment building", "Create apartment building", "ApartmentBuildingForm.fxml", new ApartmentBuildingFactory()),
                new BuildingClass(CommercialBuilding.class, "Commercial building", "Create commercial building", "CommercialBuildingForm.fxml", new CommercialBuildingFactory()),
                new BuildingClass(IndividualHouse.class, "Individual house", "Create individual house", "IndividualHouseForm.fxml", new IndividualHouseFactory()),
                new BuildingClass(ResidentialBuilding.class, "Residential building", "Create residential building", "ResidentialBuildingForm.fxml", new ResidentialBuildingFactory()),
                new BuildingClass(StateBuilding.class, "State building", "Create state building", "StateBuildingForm.fxml", new StateBuildingFactory())
        );
        cmbBox.setItems(classes);
        cmbBox.getSelectionModel().selectFirst();
    }
}
