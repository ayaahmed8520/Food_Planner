package search.allIngrediant.view;



import java.util.ArrayList;

import search.allIngrediant.model.Ingredient;

public interface AllIngredientsActivityInterface {

    public void getIngredients();
    public void onSuccessResult(ArrayList<Ingredient> ingredients);
    public void onFailureResult(String error);
    public void navigateToParticularIngredientMeals(String ingredientName);
}
