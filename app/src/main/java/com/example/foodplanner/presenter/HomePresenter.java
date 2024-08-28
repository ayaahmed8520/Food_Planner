package com.example.foodplanner.presenter;

import android.util.Log;

import com.example.foodplanner.model.HomeRepo;
import com.example.foodplanner.view.home.InHomeView;
import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallback;

import java.util.List;

import com.example.foodplanner.model.meal.Meal;

public class HomePresenter implements NetworkCallback {

    InHomeView homeView;
    HomeRepo homeRepo;

    public HomePresenter(InHomeView homeView) {
        this.homeView = homeView;
        this.homeRepo = new HomeRepo(MealRemoteDataSourceImpl.getInstance());
    }

    public void getRandomMeal (){
        homeRepo.getRandomMeal(this);
    }

    public void getRandomCategory (String category){
        homeRepo.getRandomCategory(this,category);
    }

    public void getRandomIngredient (String ingredient){
        homeRepo.getRandomIngredient(this,ingredient);
    }


    @Override
    public void onSuccessResult(List<Meal> meals, int type) {
        switch (type) {
            case 1:
                homeView.showRandomMealData(meals);
                break;
            case 2:
                homeView.showCategoryData(meals);
                break;
            case 3:
                homeView.showIngredientData(meals);
                break;
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(errorMsg, "onFailureResult: ");

    }
}
