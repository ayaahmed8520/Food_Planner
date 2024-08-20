package search.allIngrediant.presenter;



import java.util.ArrayList;

import search.allIngrediant.model.AllIngredientsRepository;
import search.allIngrediant.model.Ingredient;
import search.allIngrediant.view.AllIngredientsActivityInterface;

public class AllIngredientPresenter {
    private  static AllIngredientsActivityInterface allIngredientsActivityInterface;

    public static void getAllIngredients(AllIngredientsActivityInterface allIngredientsInterface){
        allIngredientsActivityInterface= allIngredientsInterface;
        AllIngredientsRepository.getAllIngredients();
    }
    public static void onSuccessResult(ArrayList<Ingredient> ingredients){
        allIngredientsActivityInterface.onSuccessResult(ingredients);
    }
    public static void onFailureResult(String error){
        allIngredientsActivityInterface.onFailureResult(error);
    }
}
