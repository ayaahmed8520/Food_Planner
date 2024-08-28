package com.example.foodplanner.model.network;

import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;

public interface NetworkCallBackDetails {
    public void onSuccessResult(List<MealDetails> meals );
    public void onFailureResult(String errorMsg);
}
