package com.example.foodplanner.model.allIngredient;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackAllIngredient;

public class AllIngredientsRepo {
    MealRemoteDataSourceImpl mealRemoteDataSource;

    public AllIngredientsRepo (MealRemoteDataSourceImpl remoteDataSource) {
        this.mealRemoteDataSource = remoteDataSource;
    }

    public void getAllMealIngredient (NetworkCallBackAllIngredient networkCallback){
        mealRemoteDataSource.makeAllIngredientNetworkCall(networkCallback);
    }

}
