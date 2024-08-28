package com.example.foodplanner.model.dp.weekPlanDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeeklyPlanMealDetailsDao {

    @Query("SELECT * From Weekly_plan_details")
    LiveData<List<WeeklyPlanMealDetails>> getAllPlaneMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(WeeklyPlanMealDetails meal);

    @Delete
    void deleteMeal(WeeklyPlanMealDetails meal);

    @Query("SELECT * From Weekly_plan_details WHERE idMeal = :id LIMIT 1")
    WeeklyPlanMealDetails getPlanMeal(String id);

    @Query("DELETE From Weekly_plan_details WHERE idMeal = :id")
    void deleteMealById(String id);

    @Query("SELECT * FROM weekly_plan_details")
    List<WeeklyPlanMealDetails> getAllPlanMealsforBackup();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMany(WeeklyPlanMealDetails... meals);

    @Query("DELETE FROM weekly_plan_details") // Replace with your table name
    void deleteAll();


}
