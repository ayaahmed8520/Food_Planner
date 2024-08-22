package search.allIngrediant.view;



import java.util.ArrayList;

import search.allIngrediant.model.Ingredient;

public interface AllIngredientsInterface {

    public void getIngredients();
    public void resultSuccess(ArrayList<Ingredient> ingredients);
    public void resultFailure(String error);
    public void navToSpecificIngredientMeals(String ingredientName);
}
