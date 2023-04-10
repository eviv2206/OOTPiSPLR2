package com.example.lr2;

import com.example.lr2.abstractFactory.*;
import com.example.lr2.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnStreet;

    @FXML
    private TableView<Building> table;

    @FXML
    private TableColumn<Building, Integer> colData;

    @FXML
    private TableColumn<Building, String> colName;

    public static final ObservableList<Building> buildings = FXCollections.observableArrayList();

    public static final ObservableList<Building> street = FXCollections.observableArrayList();

    public static final ArrayList<BuildingClass> classes = new ArrayList<>();
    @FXML
    void add(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectType.fxml"));
        Stage stage = new Stage();
        FormController.setFormParameters("Choose type", loader, stage);
        stage.showAndWait();
    }

    @FXML
    void delete(ActionEvent event) {
        Building building = table.getSelectionModel().getSelectedItem();
        buildings.remove(building);
        street.remove(building);
        table.refresh();
    }

    @FXML
    void edit(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Building building = table.getSelectionModel().getSelectedItem();
            BuildingClass buildingClass = null;
            for(BuildingClass buildClass: classes){
                if (buildClass.getBuildingClass() == building.getClass()){
                    buildingClass = buildClass;
                }
            }
            BuildingAbstractFactory factory = buildingClass.getFactory();
            factory.edit(building, buildingClass.getTitle(), buildingClass.getFxmlName());
            table.refresh();
        }
    }

    @FXML
    void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("address"));
        colData.setCellValueFactory(new PropertyValueFactory<>("numOfLevels"));

        classes.add(new BuildingClass(Building.class, "Building", "Create building", "BuildingForm.fxml" , new BuildingFactory()));
        classes.add(new BuildingClass(ApartmentBuilding.class, "Apartment building", "Create apartment building", "ApartmentBuildingForm.fxml", new ApartmentBuildingFactory()));
        classes.add(new BuildingClass(CommercialBuilding.class, "Commercial building", "Create commercial building", "CommercialBuildingForm.fxml", new CommercialBuildingFactory()));
        classes.add(new BuildingClass(IndividualHouse.class, "Individual house", "Create individual house", "IndividualHouseForm.fxml", new IndividualHouseFactory()));
        classes.add(new BuildingClass(ResidentialBuilding.class, "Residential building", "Create residential building", "ResidentialBuildingForm.fxml", new ResidentialBuildingFactory()));
        classes.add(new BuildingClass(StateBuilding.class, "State building", "Create state building", "StateBuildingForm.fxml", new StateBuildingFactory()));
        table.setItems(buildings);
    }

    @FXML
    void checkStreet(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StreetForm.fxml"));
        Stage stage = new Stage();
        FormController.setFormParameters("Street", loader, stage);
        stage.showAndWait();
    }

}
