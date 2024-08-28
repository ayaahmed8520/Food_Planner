package com.example.foodplanner.model.network;

import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;
import com.example.foodplanner.model.mealDetails.MealDetails;

import java.util.List;

public interface NetworkCallBackPlanMeals {
    public void onSuccessResult(List<WeeklyPlanMealDetails> meals );
    public void onFailureResult(String errorMsg);
}
