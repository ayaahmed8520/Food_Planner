package com.example.foodplanner.presenter;



import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.foodplanner.model.WeekPlanRepo;
import com.example.foodplanner.view.weekPlan.WeekPlanInterface;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;

public class WeekPlanMealPresenter {

    private WeekPlanInterface weekPlanInterface;
    private static WeekPlanRepo weekPlanRepo;

    public WeekPlanMealPresenter(WeekPlanRepo weekPlanRepository ,WeekPlanInterface weekPlanView) {
        this.weekPlanInterface = weekPlanView;
        this.weekPlanRepo = weekPlanRepository;
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


