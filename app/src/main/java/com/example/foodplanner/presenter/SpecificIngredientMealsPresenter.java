package com.example.foodplanner.presenter;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

import java.util.List;

import com.example.foodplanner.model.meal.Meal;
import com.example.foodplanner.view.specificIngredient.SpecificIngredientMealsInterface;
import com.example.foodplanner.model.SpecificIngredientMealsRepo;

public class SpecificIngredientMealsPresenter implements NetworkCallback {
    SpecificIngredientMealsInterface ingredientMealsInterface;
    SpecificIngredientMealsRepo ingredientMealsRepo;

    public SpecificIngredientMealsPresenter(SpecificIngredientMealsInterface ingredientMealsInterface){
        this.ingredientMealsInterface = ingredientMealsInterface;
        ingredientMealsRepo = new SpecificIngredientMealsRepo(MealRemoteDataSourceImpl.getInstance());
    }
    public void getSpecificIngredientMeal(String ingredientName){
        ingredientMealsRepo.getSpecificIngredientMeal(this , ingredientName);
    }

    @Override
    public void onSuccessResult(List<Meal> meals , int type ) {
        ingredientMealsInterface.resultSuccess(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        ingredientMealsInterface.resultFailure(errorMsg);

    }
}