package search.particularIngrediant;


import java.util.ArrayList;

import meal.model.SingleMeal;

public class ParticularIngredientMealsPresenter {
    private  static ParticularIngredientMealsActivityInterface particularIngredientMealsActivityInterface;

    public static void getParticularIngredientMeals(String IngredientName,ParticularIngredientMealsActivityInterface particularIngredientMealsInterface){
        particularIngredientMealsActivityInterface=particularIngredientMealsInterface;
        ParticularIngredientMealsRepository.getParticularIngredientMeals(IngredientName);
    }
    public static void onSuccessResult(ArrayList<SingleMeal> meals){
        particularIngredientMealsActivityInterface.onSuccessResult(meals);
    }
    public static void onFailureResult(String error){
        particularIngredientMealsActivityInterface.onFailureResult(error);
    }
}

