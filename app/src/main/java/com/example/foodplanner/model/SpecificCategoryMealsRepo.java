package com.example.foodplanner.model;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

public class SpecificCategoryMealsRepo {
    MealRemoteDataSourceImpl mealRemoteDataSource;

    public SpecificCategoryMealsRepo (MealRemoteDataSourceImpl remoteDataSource) {
        this.mealRemoteDataSource = remoteDataSource;
    }

    public void getSpecificCategoryMeal(NetworkCallback networkCallback , String categoryName){
        mealRemoteDataSource.makeSpecificCategoryNetworkCall(networkCallback , categoryName);
    }
}
