package com.example.lr2.abstractFactory;

import com.example.lr2.CommercialBuildingController;
import com.example.lr2.FormController;
import com.example.lr2.classes.Building;
import com.example.lr2.classes.CommercialBuilding;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class CommercialBuildingFactory implements BuildingAbstractFactory {

    @Override
    public void add(String title, String formPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath + formPath));
        Stage stage = new Stage();
        FormController.setFormParameters(title, loader, stage);
        stage.show();
    }

    @Override
    public void edit(Building building, String title, String formPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath + formPath));
        Stage stage = new Stage();
        Object controller = FormController.setFormParameters(title, loader, stage);
        assert controller != null;
        ((CommercialBuildingController) controller).setData((CommercialBuilding) building);
        stage.show();
    }
}
