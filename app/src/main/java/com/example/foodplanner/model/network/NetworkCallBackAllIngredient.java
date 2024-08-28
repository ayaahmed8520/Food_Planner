package com.example.foodplanner.model.network;

import java.util.List;

import com.example.foodplanner.model.allIngredient.Ingredient;

public interface NetworkCallBackAllIngredient {
    public void onSuccessResult(List<Ingredient> categories);
    public void onFailureResult(String errorMsg);
}
