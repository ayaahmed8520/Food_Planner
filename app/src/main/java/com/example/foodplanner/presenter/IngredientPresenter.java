package com.example.foodplanner.presenter;



import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackAllIngredient;

import java.util.List;

import com.example.foodplanner.model.allIngredient.AllIngredientsRepo;
import com.example.foodplanner.model.allIngredient.Ingredient;
import com.example.foodplanner.view.allIngredient.AllIngredientsInterface;

public class IngredientPresenter implements NetworkCallBackAllIngredient {
    AllIngredientsInterface allIngredientsInterface;
    AllIngredientsRepo allIngredientsRepo;

    public IngredientPresenter(AllIngredientsInterface allIngredientsInterface){
        this.allIngredientsInterface = allIngredientsInterface;
        allIngredientsRepo = new AllIngredientsRepo(MealRemoteDataSourceImpl.getInstance());
    }
    public void getAllIngredient(){
        allIngredientsRepo.getAllMealIngredient(this);
    }

    @Override
    public void onSuccessResult(List<Ingredient> ingredients) {
        allIngredientsInterface.resultSuccess(ingredients);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        allIngredientsInterface.resultFailure(errorMsg);

    }
}
