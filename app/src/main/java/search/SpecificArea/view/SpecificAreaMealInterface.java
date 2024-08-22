package search.SpecificArea.view;


import java.util.ArrayList;

import meal.model.SingleMeal;

public interface SpecificAreaMealInterface {
    public void getSpecificAreaMeals(String areaName);
    public void resultSuccess(ArrayList<SingleMeal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}
