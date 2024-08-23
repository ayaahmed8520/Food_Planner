package search.SpecificArea.view;


import java.util.ArrayList;

import meal.model.Meal;

public interface SpecificAreaMealInterface {
    public void getSpecificAreaMeals(String areaName);
    public void resultSuccess(ArrayList<Meal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}
