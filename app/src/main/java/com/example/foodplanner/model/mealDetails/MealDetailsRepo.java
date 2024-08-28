package com.example.foodplanner.model.mealDetails;

import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackDetails;



public class MealDetailsRepo {

    MealRemoteDataSourceImpl mealRemoteDataSource;


    public MealDetailsRepo (MealRemoteDataSourceImpl remoteDataSource ) {
        this.mealRemoteDataSource = remoteDataSource;

    }

    public void getMealDetails (NetworkCallBackDetails networkCallback , String meal){
        mealRemoteDataSource.makeMealDetailsNetworkCall(networkCallback , meal);
    }

}


