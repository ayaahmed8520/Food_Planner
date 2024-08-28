package com.example.foodplanner.model;


import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.foodplanner.model.dp.MealLocalDataSourceImpl;
import com.example.foodplanner.model.mealDetails.MealDetails;

public class FavRepo {
    private MealLocalDataSourceImpl mealLocalDataSource;

    public FavRepo(MealLocalDataSourceImpl mealLocalData) {
        mealLocalDataSource = mealLocalData;
    }


    public LiveData<List<MealDetails>> getLocalMeal() {
        return mealLocalDataSource.getLocalMeals();
    }

    public void insertMeal(MealDetails meal) {
        mealLocalDataSource.insertMeal(meal);
    }

    public void deleteMeal(MealDetails meal) {
        mealLocalDataSource.removeMeal(meal);
    }
}

