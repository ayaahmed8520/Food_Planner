package com.example.foodplanner.model.allCategory;

import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackAllCategory;


public class AllCategoriesRepo {
    MealRemoteDataSourceImpl mealRemoteDataSource;

    public AllCategoriesRepo (MealRemoteDataSourceImpl remoteDataSource) {
        this.mealRemoteDataSource = remoteDataSource;
    }

    public void getAllMealCategory (NetworkCallBackAllCategory networkCallback){
        mealRemoteDataSource.makeAllCategoryNetworkCall(networkCallback);
    }

}
