package com.example.lr2;

import com.example.lr2.abstractFactory.*;
import com.example.lr2.classes.*;
import com.example.lr2.plugins.Plugin;
import com.example.lr2.serializer.Serializer;
import com.example.lr2.serializer.impl.BinarySerializer;
import com.example.lr2.serializer.impl.JsonSerializer;
import com.example.lr2.serializer.impl.TextSerializer;
import com.example.lr2.utils.UtilFileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
    public TableView<Building> table;

    @FXML
    private TableColumn<Building, Integer> colData;

    @FXML
    private TableColumn<Building, String> colName;

    @FXML
    private MenuItem menuOpenFile;

    @FXML
    private MenuItem menuSaveAs;

    public static final ObservableList<Building> buildings = FXCollections.observableArrayList();

    public static final ObservableList<Building> street = FXCollections.observableArrayList();

    public static final HashMap<String, Plugin> pluginMap = new HashMap<>();

    public static final HashMap<String, Serializer> serializerMap = new HashMap<>();

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
            for (BuildingClass buildClass : classes) {
                if (buildClass.getBuildingClass() == building.getClass()) {
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
        table.setItems(buildings);
        getBuildingClasses();
        initSerializers();
        loadPlugins();
        HashMap<String, Building> b = new HashMap<>();
        b.put("h", new Building());
        Building build1 = b.get("h");
        Building build2 = b.get("h");
        build1.setAddress("gggg");
        build2.setAddress("hhh");
        System.out.println(build1.getAddress());
        System.out.println(build2.getAddress());
    }

    @FXML
    void checkStreet(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StreetForm.fxml"));
        Stage stage = new Stage();
        FormController.setFormParameters("Street", loader, stage);
        stage.showAndWait();
    }

    @FXML
    void openFile(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OpenFileForm.fxml"));
        Stage stage = new Stage();
        FormController.setFormParameters("Choose open file", loader, stage);
        stage.showAndWait();
        table.refresh();
    }

    @FXML
    void saveAs(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseEncoding.fxml"));
        Stage stage = new Stage();
        FormController.setFormParameters("Choose encoding", loader, stage);
        stage.showAndWait();
    }

    private void loadPlugins() {
        File dir = new File("src\\main\\java\\com\\example\\lr2\\plugins\\impl");
        try {
            URL[] urls = {dir.toURI().toURL()};
            ClassLoader cl = new URLClassLoader(urls);
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                String className = file.getName().substring(0, file.getName().length() - 5);
                Class<?> clazz = cl.loadClass("com.example.lr2.plugins.impl." + className);
                Plugin plugin = (Plugin) clazz.getConstructor().newInstance();
                pluginMap.put(plugin.toString(), plugin);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private void getBuildingClasses() {
        classes.add(new BuildingClass(Building.class, "Building", "Create building", "BuildingForm.fxml", new BuildingFactory()));
        classes.add(new BuildingClass(ApartmentBuilding.class, "Apartment building", "Create apartment building", "ApartmentBuildingForm.fxml", new ApartmentBuildingFactory()));
        classes.add(new BuildingClass(CommercialBuilding.class, "Commercial building", "Create commercial building", "CommercialBuildingForm.fxml", new CommercialBuildingFactory()));
        classes.add(new BuildingClass(IndividualHouse.class, "Individual house", "Create individual house", "IndividualHouseForm.fxml", new IndividualHouseFactory()));
        classes.add(new BuildingClass(ResidentialBuilding.class, "Residential building", "Create residential building", "ResidentialBuildingForm.fxml", new ResidentialBuildingFactory()));
        classes.add(new BuildingClass(StateBuilding.class, "State building", "Create state building", "StateBuildingForm.fxml", new StateBuildingFactory()));
    }

    private void initSerializers() {
        serializerMap.put("bin", new BinarySerializer());
        serializerMap.put("json", new JsonSerializer());
        serializerMap.put("txt", new TextSerializer());
    }


}
