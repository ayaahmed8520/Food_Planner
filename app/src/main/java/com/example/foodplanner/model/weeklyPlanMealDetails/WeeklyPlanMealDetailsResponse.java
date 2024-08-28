package com.example.foodplanner.model.weeklyPlanMealDetails;

import java.util.List;

import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;

public class WeeklyPlanMealDetailsResponse {
    private List<WeeklyPlanMealDetails> meals;
    public WeeklyPlanMealDetailsResponse(List<WeeklyPlanMealDetails> meals) {
        this.meals = meals;
    }
    public List<WeeklyPlanMealDetails> getMeals() {
        return meals;
    }
    public void setMeals(List<WeeklyPlanMealDetails> meals) {
        this.meals = meals;
    }
}
