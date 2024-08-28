package com.example.foodplanner.model.dp.weekPlanDB;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.foodplanner.model.dp.AppDataBase;

public class WeekPlanLocalDataSource {
    private WeeklyPlanMealDao weeklyPlanMealDao;
    private static WeekPlanLocalDataSource weekPlanLocalDataSource = null;
    private LiveData<List<WeeklyPlanMeal>> plannedMeals;


    private WeekPlanLocalDataSource(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        weeklyPlanMealDao = db.getWeeklyPlanMealDao();
        plannedMeals = weeklyPlanMealDao.getAllMeals();
    }

    public static WeekPlanLocalDataSource getInstance(Context context){
        if(weekPlanLocalDataSource == null){
            weekPlanLocalDataSource = new WeekPlanLocalDataSource(context);
        }
        return weekPlanLocalDataSource;
    }

    public  void insertMeal(WeeklyPlanMeal weeklyPlanMeal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                weeklyPlanMealDao.insertAll(weeklyPlanMeal);
            }
        }).start();

    }


    public void deleteMeal(WeeklyPlanMeal weeklyPlanMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                weeklyPlanMealDao.delete(weeklyPlanMeal);
            }
        }).start();
    }


    public LiveData<List<WeeklyPlanMeal>> getAllStoredMeals() {
        return plannedMeals;
    }
}


