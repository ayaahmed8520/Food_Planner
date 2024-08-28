package com.example.foodplanner.view.SpecificArea;


import java.util.List;

import com.example.foodplanner.model.meal.Meal;

public interface SpecificAreaMealInterface {
    public void getSpecificAreaMeals(String areaName);
    public void resultSuccess(List<Meal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}
