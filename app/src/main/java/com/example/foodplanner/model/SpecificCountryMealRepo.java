package com.example.foodplanner.model;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

public class SpecificCountryMealRepo {

    MealRemoteDataSourceImpl mealRemoteDataSource;

    public SpecificCountryMealRepo (MealRemoteDataSourceImpl remoteDataSource) {
        this.mealRemoteDataSource = remoteDataSource;
    }

    public void getSpecificAreaMeal(NetworkCallback networkCallback , String areaName){
        mealRemoteDataSource.makeSpecificAreaNetworkCall(networkCallback , areaName);
    }
}

