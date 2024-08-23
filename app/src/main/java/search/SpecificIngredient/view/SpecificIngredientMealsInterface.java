package search.SpecificIngredient.view;


import java.util.ArrayList;

import meal.model.Meal;

public interface SpecificIngredientMealsInterface {
    public void getSpecificIngredientMeals(String ingredientName);
    public void resultSuccess(ArrayList<Meal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}
