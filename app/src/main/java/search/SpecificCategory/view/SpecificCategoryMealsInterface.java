package search.SpecificCategory.view;


import java.util.ArrayList;

import meal.model.SingleMeal;

public interface SpecificCategoryMealsInterface {
    public void getSpecificCategoryMeals(String categoryName);
    public void resultSuccess(ArrayList<SingleMeal> meals);
    public void resultFailure(String error);
    public void navigateToViewDetails(String position);
}