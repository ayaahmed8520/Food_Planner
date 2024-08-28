package com.example.foodplanner.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.foodplanner.model.WeekPlanRepo;
import com.example.foodplanner.view.weekPlan.WeekPlanInterface;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;
import com.example.foodplanner.model.dp.weekPlanDB.WeekPlanLocalDataSource;

public class WeekPlanMealPresenter {

    private WeekPlanInterface weekPlanInterface;
    private static WeekPlanRepo weekPlanRepo;

    public WeekPlanMealPresenter(WeekPlanInterface weekPlanView, Context context) {
        this.weekPlanInterface = weekPlanView;
        this.weekPlanRepo = new WeekPlanRepo(WeekPlanLocalDataSource.getInstance(context.getApplicationContext()));
    }

    public LiveData<List<WeeklyPlanMeal>> getPlannedMeals() {
        return weekPlanRepo.getLocalPlannedMeals();
    }

    public void addMealToPlan(WeeklyPlanMeal meal) {
        weekPlanRepo.insertMeal(meal);
    }

    public void removeFromPlan(WeeklyPlanMeal meal) {
        weekPlanRepo.deleteMeal(meal);
    }
}
