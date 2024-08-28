package com.example.foodplanner.view.weeklyPlanMealDetails;

import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;

import java.util.List;

public interface WeeklyPlanMealMealDetailsIn {

    public void resultSuccess(List<WeeklyPlanMealDetails> meals);
    public void resultFailure(String error);
}
