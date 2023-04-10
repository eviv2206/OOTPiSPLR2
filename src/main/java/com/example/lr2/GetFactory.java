package com.example.lr2;

import com.example.lr2.classes.*;

import java.util.HashMap;
import java.util.Map;

public class GetFactory {

    final Map<String, BuildingAbstractFactory> factoryMap = new HashMap<>();

    public GetFactory(){
        factoryMap.put(ApartmentBuilding.class.getSimpleName(), new ApartmentBuildingFactory());
        factoryMap.put(Building.class.getSimpleName(), new BuildingFactory());
        factoryMap.put(CommercialBuilding.class.getSimpleName(), new CommercialBuildingFactory());
        factoryMap.put(IndividualHouse.class.getSimpleName(), new IndividualHouseFactory());
        factoryMap.put(ResidentialBuilding.class.getSimpleName(), new ResidentialBuildingFactory());
        factoryMap.put(StateBuilding.class.getSimpleName(), new StateBuildingFactory());
    }

    public BuildingAbstractFactory getFactory(String type){
        return factoryMap.get(type);
    }
}
