package search.particularArea.view;


import java.util.ArrayList;

import meal.model.SingleMeal;

public interface ParticularAreaMealActivityInterface {
    public void getParticularAreaMeals(String areaName);
    public void onSuccessResult(ArrayList<SingleMeal> meals);
    public void onFailureResult(String error);
    public void navigateToViewDetails(String position);
}
