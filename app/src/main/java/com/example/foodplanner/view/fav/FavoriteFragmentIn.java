package com.example.foodplanner.view.fav;

import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;

public interface FavoriteFragmentIn {
    public void showMeals(List<MealDetails> meals);
    public void failedToDisplay(String error);
    public void removeMeal(MealDetails meal);

}
