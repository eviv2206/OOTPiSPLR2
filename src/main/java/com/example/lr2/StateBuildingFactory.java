package com.example.lr2;

import com.example.lr2.classes.Building;
import com.example.lr2.classes.StateBuilding;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class StateBuildingFactory implements BuildingAbstractFactory{

    @Override
    public void add(String title, String formPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
        Stage stage = new Stage();
        FormController.setFormParameters(title, loader, stage);
        stage.show();
    }

    @Override
    public void edit(Building building, String title, String formPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
        Stage stage = new Stage();
        Object controller = FormController.setFormParameters(title, loader, stage);
        assert controller != null;
        ((StateBuildingController) controller).setData((StateBuilding) building);
        stage.showAndWait();
    }
}
