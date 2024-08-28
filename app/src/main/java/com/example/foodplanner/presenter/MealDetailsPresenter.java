package com.example.foodplanner.presenter;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackDetails;

import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;
import com.example.foodplanner.model.mealDetails.MealDetailsRepo;
import com.example.foodplanner.view.mealDetails.MealDetailsIn;

public class MealDetailsPresenter implements NetworkCallBackDetails {
    MealDetailsRepo mealDetailsRepo;
    private  static MealDetailsIn mealDetailsIn;


    public MealDetailsPresenter (MealDetailsIn detailsIn) {
        mealDetailsIn = detailsIn;
        mealDetailsRepo = new MealDetailsRepo(MealRemoteDataSourceImpl.getInstance());

    }


    public  void getMealDetails(String meal){
        mealDetailsRepo.getMealDetails(this,meal);
    }


    @Override
    public void onSuccessResult(List<MealDetails> meals) {
        mealDetailsIn.resultSuccess(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        mealDetailsIn.resultFailure(errorMsg);
    }
}
