package com.example.foodplanner.presenter;

import com.example.foodplanner.model.weeklyPlanMealDetails.WeeklyPlanMealDetailsRepo;
import com.example.foodplanner.view.weeklyPlanMealDetails.WeeklyPlanMealMealDetailsIn;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;
import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackPlanMeals;

import java.util.List;

public class WeeklyPlanMealDetailsPresenter implements NetworkCallBackPlanMeals {

    WeeklyPlanMealDetailsRepo planMealDetailsRepo;
    private  static WeeklyPlanMealMealDetailsIn planMealDetailsIn;


    public WeeklyPlanMealDetailsPresenter (WeeklyPlanMealMealDetailsIn planDetailsIn) {
        planMealDetailsIn = planDetailsIn;
        planMealDetailsRepo = new WeeklyPlanMealDetailsRepo(MealRemoteDataSourceImpl.getInstance());

    }


    public  void getPlanMealDetails(String meal){
        planMealDetailsRepo.getMealPlanDetails(this,meal);
    }


    @Override
    public void onSuccessResult(List<WeeklyPlanMealDetails> meals) {
        planMealDetailsIn.resultSuccess(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        planMealDetailsIn.resultFailure(errorMsg);
    }
}
