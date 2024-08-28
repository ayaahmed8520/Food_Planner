package com.example.foodplanner.model;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

public class SpecificIngredientMealsRepo {
    MealRemoteDataSourceImpl mealRemoteDataSource;

    public SpecificIngredientMealsRepo (MealRemoteDataSourceImpl remoteDataSource) {
        this.mealRemoteDataSource = remoteDataSource;
    }

    public void getSpecificIngredientMeal(NetworkCallback networkCallback , String ingredientName){
        mealRemoteDataSource.makeSpecificIngredientNetworkCall(networkCallback , ingredientName);
    }
}
