package com.example.foodplanner.view.SpecificCategory;


import java.util.List;

import com.example.foodplanner.model.meal.Meal;

public interface SpecificCategoryMealsInterface {
    public void getSpecificCategoryMeals(String categoryName);
    public void resultSuccess(List<Meal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}