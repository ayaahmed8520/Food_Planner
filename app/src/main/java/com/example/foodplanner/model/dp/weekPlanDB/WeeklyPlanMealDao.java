package com.example.foodplanner.model.dp.weekPlanDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeeklyPlanMealDao {

    @Query("SELECT * From Weekly_plan")
    LiveData<List<WeeklyPlanMeal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(WeeklyPlanMeal meal);

    @Delete
    void delete(WeeklyPlanMeal meal);

    @Query("SELECT * FROM weekly_plan")
    List<WeeklyPlanMeal> getAllPlanMealsforBackup();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMany(WeeklyPlanMeal... meals);

    @Query("DELETE FROM weekly_plan") // Replace with your table name
    void deleteAll();

}
