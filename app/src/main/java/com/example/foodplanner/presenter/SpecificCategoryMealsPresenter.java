package com.example.foodplanner.presenter;

import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

import java.util.List;

import com.example.foodplanner.model.meal.Meal;
import com.example.foodplanner.model.SpecificCategoryMealsRepo;
import com.example.foodplanner.view.SpecificCategory.SpecificCategoryMealsInterface;

public class SpecificCategoryMealsPresenter implements NetworkCallback {
    SpecificCategoryMealsInterface specificCategoryMealsInterface;
    SpecificCategoryMealsRepo specificCategoryMealsRepo;

    public SpecificCategoryMealsPresenter(SpecificCategoryMealsInterface specificCategoryMealsInterface){
        this.specificCategoryMealsInterface = specificCategoryMealsInterface;
        specificCategoryMealsRepo = new SpecificCategoryMealsRepo(MealRemoteDataSourceImpl.getInstance());
    }
    public void getSpecificCategoryMeal(String categoryName){
        specificCategoryMealsRepo.getSpecificCategoryMeal(this , categoryName);
    }

    @Override
    public void onSuccessResult(List<Meal> meals , int type ) {
        specificCategoryMealsInterface.resultSuccess(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        specificCategoryMealsInterface.resultFailure(errorMsg);

    }
}
