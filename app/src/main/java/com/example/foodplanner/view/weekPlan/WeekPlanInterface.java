package com.example.foodplanner.view.weekPlan;

import java.util.List;

import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;

public interface WeekPlanInterface {
    public void showPlannedMeals(List<WeeklyPlanMeal> meals);
    public void failedToDisplayPlanMeals(String error);
    public void removePlannedMeal(WeeklyPlanMeal meal);
}
