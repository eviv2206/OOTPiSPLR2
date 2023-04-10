package com.example.lr2.classes;

public class Street {
    CommercialBuilding[] commercialBuildings;
    IndividualHouse[] individualHouses;
    ApartmentBuilding[] apartmentBuildings;
    StateBuilding[] stateBuildings;


    public Street(CommercialBuilding[] commercialBuildings, IndividualHouse[] individualHouses,
                  ApartmentBuilding[] apartmentBuildings, StateBuilding[] stateBuildings){
        this.commercialBuildings = commercialBuildings;
        this.individualHouses = individualHouses;
        this.apartmentBuildings = apartmentBuildings;
        this.stateBuildings = stateBuildings;
    }
}
