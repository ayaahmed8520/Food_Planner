package com.example.foodplanner.view.specificIngredient;


import java.util.List;

import com.example.foodplanner.model.meal.Meal;

public interface SpecificIngredientMealsInterface {
    public void getSpecificIngredientMeals(String ingredientName);
    public void resultSuccess(List<Meal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}
