package search.particularCategory;


import java.util.ArrayList;

import meal.model.SingleMeal;

public interface ParticularCategoryMealsActivityInterface {
    public void getParticularCategoryMeals(String categoryName);
    public void onSuccessResult(ArrayList<SingleMeal> meals);
    public void onFailureResult(String error);
    public void navigateToViewDetails(String position);
}