package com.example.foodplanner.model;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;


public class HomeRepo {

    MealRemoteDataSourceImpl mealRemoteDataSource;

    public HomeRepo (MealRemoteDataSourceImpl remoteDataSource) {
        this.mealRemoteDataSource = remoteDataSource;
    }

    public void getRandomMeal (NetworkCallback networkCallback){
        mealRemoteDataSource.makeRandomMealNetworkCall(networkCallback);
    }

    public void getRandomCategory (NetworkCallback networkCallback , String category){
        mealRemoteDataSource.makeRandomCategoryNetworkCall(networkCallback , category );
    }

    public void getRandomIngredient (NetworkCallback networkCallback , String ingredient){
        mealRemoteDataSource.makeRandomIngredientNetworkCall(networkCallback , ingredient);
    }





}