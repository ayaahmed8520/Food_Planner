package com.example.foodplanner.view.weekPlan;

import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;

public interface AddAndRemoveFromPlan {
    public void addMealToPlan(WeeklyPlanMealDetails weeklyPlanMeal );
    public void removeMealFromPlan(WeeklyPlanMealDetails weeklyPlanMeal );
}
