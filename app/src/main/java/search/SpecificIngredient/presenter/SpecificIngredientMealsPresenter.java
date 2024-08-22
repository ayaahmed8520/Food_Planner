package search.SpecificIngredient.presenter;


import java.util.ArrayList;

import meal.model.SingleMeal;
import search.SpecificIngredient.view.SpecificIngredientMealsInterface;
import search.SpecificIngredient.model.SpecificIngredientMealsRepo;

public class SpecificIngredientMealsPresenter {
    private  static SpecificIngredientMealsInterface specificIngredientMealsInterface;

    public static void getSpecificIngredientMeals(String IngredientName, SpecificIngredientMealsInterface particularIngredientMealsInterface){
        specificIngredientMealsInterface =particularIngredientMealsInterface;
        SpecificIngredientMealsRepo.getSpecificIngredientMeals(IngredientName);
    }
    public static void resultSuccess(ArrayList<SingleMeal> meals){
        specificIngredientMealsInterface.resultSuccess(meals);
    }
    public static void resultFailure(String error){
        specificIngredientMealsInterface.resultFailure(error);
    }
}

