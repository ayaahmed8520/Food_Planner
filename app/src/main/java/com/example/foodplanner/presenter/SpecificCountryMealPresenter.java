package com.example.foodplanner.presenter;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

import java.util.List;

import com.example.foodplanner.model.meal.Meal;
import com.example.foodplanner.view.SpecificArea.SpecificAreaMealInterface;
import com.example.foodplanner.model.SpecificCountryMealRepo;

public class SpecificCountryMealPresenter implements NetworkCallback {
    SpecificAreaMealInterface specificAreaMealInterface;
    SpecificCountryMealRepo specificCountryMealRepo;

    public SpecificCountryMealPresenter(SpecificAreaMealInterface specificAreaMealInterface){
        this.specificAreaMealInterface = specificAreaMealInterface;
        specificCountryMealRepo = new SpecificCountryMealRepo(MealRemoteDataSourceImpl.getInstance());
    }
    public void getSpecificAreaMeal(String areaName){
        specificCountryMealRepo.getSpecificAreaMeal(this , areaName);
    }

    @Override
    public void onSuccessResult(List<Meal> meals , int type ) {
        specificAreaMealInterface.resultSuccess(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        specificAreaMealInterface.resultFailure(errorMsg);

    }

}

