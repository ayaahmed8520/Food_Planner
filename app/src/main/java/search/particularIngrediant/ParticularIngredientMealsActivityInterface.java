package search.particularIngrediant;


import java.util.ArrayList;

import meal.model.SingleMeal;

public interface ParticularIngredientMealsActivityInterface {
    public void getParticularIngredientMeals(String ingredientName);
    public void onSuccessResult(ArrayList<SingleMeal> meals);
    public void onFailureResult(String error);
    public void navigateToViewDetails(String position);
}
