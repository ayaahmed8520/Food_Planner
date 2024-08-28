package com.example.foodplanner.view.home;

import java.util.List;

import com.example.foodplanner.model.meal.Meal;

public interface InHomeView {
    public void showRandomMealData(List<Meal> meals);
    public void showCategoryData(List<Meal> meals);
    public void showIngredientData(List<Meal> meals);
    public void showError(String error);
}
