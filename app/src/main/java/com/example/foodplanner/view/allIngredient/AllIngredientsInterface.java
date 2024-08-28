package com.example.foodplanner.view.allIngredient;



import java.util.List;

import com.example.foodplanner.model.allIngredient.Ingredient;

public interface AllIngredientsInterface {

    public void getIngredients();
    public void resultSuccess(List<Ingredient> ingredients);
    public void resultFailure(String error);
    public void navToSpecificIngredientMeals(String ingredientName);
}
