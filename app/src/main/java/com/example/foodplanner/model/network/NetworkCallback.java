package com.example.foodplanner.model.network;


import java.util.List;

import com.example.foodplanner.model.meal.Meal;

public interface NetworkCallback {
    public void onSuccessResult(List<Meal> meals , int type );
    public void onFailureResult(String errorMsg);

}

