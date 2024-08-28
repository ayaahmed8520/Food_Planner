package com.example.foodplanner.model.dp;


import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;


public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private static MealLocalDataSourceImpl mealsRepository = null;
    MealDAO mealDAO;
    Context context;
    AppDataBase db;
    private final LiveData<List<MealDetails>> mealList;

    private MealLocalDataSourceImpl(Context _context) {
        context = _context;
        db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealDAO();
        mealList = mealDAO.getAllMeals();
    }

    public LiveData<List<MealDetails>> getLocalMeals() {
        return mealList;
    }

    public static MealLocalDataSourceImpl getInstance(Context context) {
        if (mealsRepository == null) {
            mealsRepository = new MealLocalDataSourceImpl(context.getApplicationContext());
        }
        return mealsRepository;
    }

    public void removeMeal(MealDetails meal) {
        new Thread(() -> mealDAO.deleteMeal(meal)).start();
    }

    public void insertMeal(MealDetails meal) {
        new Thread(() -> mealDAO.insertMeal(meal)).start();
    }
}


