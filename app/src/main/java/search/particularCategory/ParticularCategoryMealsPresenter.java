package search.particularCategory;

import java.util.ArrayList;

import meal.model.SingleMeal;

public class ParticularCategoryMealsPresenter {
    private  static ParticularCategoryMealsActivityInterface particularCategoryMealsActivityInterface;

    public static void getParticularCategoryMeals(String categoryName,ParticularCategoryMealsActivityInterface particularCategoryMealsInterface){
        particularCategoryMealsActivityInterface=particularCategoryMealsInterface;
        ParticularCategoryMealsRepository.getParticularCategoryMeals(categoryName);
    }
    public static void onSuccessResult(ArrayList<SingleMeal> meals){
        particularCategoryMealsActivityInterface.onSuccessResult(meals);
    }
    public static void onFailureResult(String error){
        particularCategoryMealsActivityInterface.onFailureResult(error);
    }
}
