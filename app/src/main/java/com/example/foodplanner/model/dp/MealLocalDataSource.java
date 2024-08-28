package com.example.foodplanner.model.dp;


import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;

public interface MealLocalDataSource {
    LiveData<List<MealDetails>> getLocalMeals();
    void insertMeal(MealDetails meal);
    void removeMeal(MealDetails meal);
}

