package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;


import com.example.foodplanner.model.dp.weekPlanDB.WeekPlanLocalDataSource;

import java.util.List;

import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;

public class WeekPlanRepo {
    private WeekPlanLocalDataSource weekPlanLocalDataSource;

    public WeekPlanRepo(WeekPlanLocalDataSource planMealLocalData) {
        weekPlanLocalDataSource = planMealLocalData;
    }


    public LiveData<List<WeeklyPlanMeal>> getLocalPlannedMeals() {
        return weekPlanLocalDataSource.getAllStoredMeals();
    }

    public void insertMeal(WeeklyPlanMeal meal) {
        weekPlanLocalDataSource.insertMeal(meal);
    }

    public void deleteMeal(WeeklyPlanMeal meal) {
        weekPlanLocalDataSource.deleteMeal(meal);
    }
}
