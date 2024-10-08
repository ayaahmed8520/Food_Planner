package com.example.foodplanner.model.dp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.mealDetails.MealDetails;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDao;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetailsDao;


@Database(entities = {MealDetails.class , WeeklyPlanMeal.class , WeeklyPlanMealDetails.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract MealDAO mealDAO();
    public abstract WeeklyPlanMealDao getWeeklyPlanMealDao();
    public abstract WeeklyPlanMealDetailsDao getWeeklyPlanMealDetailsDao();

    //one thread at a time to access this method
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "meals")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}