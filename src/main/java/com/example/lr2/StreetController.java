package com.example.lr2;

import com.example.lr2.classes.Building;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StreetController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<Building, String> colName;

    @FXML
    private TableView<Building> table;

    @FXML
    void add(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseBuildingForm.fxml"));
        Stage stage = new Stage();
        FormController.setFormParameters("Choose building", loader, stage);
        stage.show();
    }

    @FXML
    void delete(ActionEvent event) {
        MainController.street.remove(table.getSelectionModel().getSelectedItem());
        table.refresh();
    }

    @FXML
    void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("address"));
        table.setItems(MainController.street);
    }

}
