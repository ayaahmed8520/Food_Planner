package search.SpecificIngredient.view;


import java.util.ArrayList;

import meal.model.SingleMeal;

public interface SpecificIngredientMealsInterface {
    public void getSpecificIngredientMeals(String ingredientName);
    public void resultSuccess(ArrayList<SingleMeal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}
