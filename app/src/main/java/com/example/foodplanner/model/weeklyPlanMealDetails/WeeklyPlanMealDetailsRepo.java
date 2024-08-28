package com.example.foodplanner.model.weeklyPlanMealDetails;

import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackPlanMeals;

public class WeeklyPlanMealDetailsRepo {

    MealRemoteDataSourceImpl mealRemoteDataSource;

    public WeeklyPlanMealDetailsRepo (MealRemoteDataSourceImpl remoteDataSource ) {
        this.mealRemoteDataSource = remoteDataSource;

    }

    public void getMealPlanDetails (NetworkCallBackPlanMeals networkCallback , String meal){
        mealRemoteDataSource.makePlanMealDetailsNetworkCall(networkCallback , meal);
    }


}