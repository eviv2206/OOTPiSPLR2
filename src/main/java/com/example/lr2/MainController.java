package com.example.lr2;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    final Map<Class<?>, String> formsMap = new HashMap<>();

    @FXML
    void add(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectType.fxml"));
        Stage stage = new Stage();
        Object controller = FormController.setFormParameters("Choose type", loader, stage);
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
            GetFactory getFactory = new GetFactory();
            Building building = table.getSelectionModel().getSelectedItem();
            BuildingAbstractFactory factory = getFactory.getFactory(building.getClass().getSimpleName());
            factory.edit(table.getSelectionModel().getSelectedItem(), building.getClass().getSimpleName(), formsMap.get(building.getClass()));
            table.refresh();
        }
    }

    @FXML
    void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("address"));
        colData.setCellValueFactory(new PropertyValueFactory<>("numOfLevels"));
        table.setItems(buildings);
        formsMap.put(Building.class, "BuildingForm.fxml");
        formsMap.put(ApartmentBuilding.class, "ApartmentBuildingForm.fxml");
        formsMap.put(CommercialBuilding.class, "CommercialBuildingForm.fxml");
        formsMap.put(IndividualHouse.class, "IndividualHouseForm.fxml");
        formsMap.put(ResidentialBuilding.class, "ResidentialBuildingForm.fxml");
        formsMap.put(StateBuilding.class, "StateBuildingForm.fxml");

    }

    @FXML
    void checkStreet(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StreetForm.fxml"));
        Stage stage = new Stage();
        Object controller = FormController.setFormParameters("Street", loader, stage);
        stage.showAndWait();
    }

}
