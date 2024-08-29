package com.example.foodplanner.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.FavRepo;
import com.example.foodplanner.view.fav.FavoriteFragmentIn;

import com.example.foodplanner.model.dp.MealLocalDataSourceImpl;
import com.example.foodplanner.model.mealDetails.MealDetails;

import java.util.List;

public class FavPresenter {
    private FavoriteFragmentIn favoriteFragmentView;
    private FavRepo favRepo;

    public FavPresenter(FavRepo favRepository , FavoriteFragmentIn favView) {
        this.favoriteFragmentView = favView;
        this.favRepo = favRepository;
    }

    public LiveData<List<MealDetails>> getFavoriteMeals() {
        return favRepo.getLocalMeal();
    }

    public void addMeal(MealDetails meal) {
        favRepo.insertMeal(meal);
    }

    public void removeFromFav(MealDetails meal) {
        favRepo.deleteMeal(meal);
    }
}


