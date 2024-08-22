package search.allIngrediant.presenter;



import java.util.ArrayList;

import search.allIngrediant.model.AllIngredientsRepo;
import search.allIngrediant.model.Ingredient;
import search.allIngrediant.view.AllIngredientsInterface;

public class IngredientPresenter {
    private  static AllIngredientsInterface allIngredientsInterface;

    public static void getAllIngredients(AllIngredientsInterface allIngredientsInterface){
        IngredientPresenter.allIngredientsInterface = allIngredientsInterface;
        AllIngredientsRepo.getAllIngredients();
    }
    public static void resultSuccess(ArrayList<Ingredient> ingredients){
        allIngredientsInterface.resultSuccess(ingredients);
    }
    public static void resultFailure(String error){
        allIngredientsInterface.resultFailure(error);
    }
}
