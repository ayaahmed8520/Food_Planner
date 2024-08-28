package com.example.foodplanner.model.dp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;


@Dao
public interface MealDAO {
    @Query("SELECT * FROM MealDetails")
    LiveData<List<MealDetails>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(MealDetails meal);

    @Delete
    void deleteMeal(MealDetails meal);


    @Query("SELECT * FROM MealDetails")
    List<MealDetails> getAllMealsForBackup();

    @Query("DELETE FROM MealDetails")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMany(MealDetails... meals);
}