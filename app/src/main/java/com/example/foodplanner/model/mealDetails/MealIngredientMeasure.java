package com.example.foodplanner.model.mealDetails;

public class MealIngredientMeasure {
    private final String ingredientName;
    private final String ingredientMeasure;

    public MealIngredientMeasure(String ingredientName, String ingredientMeasure) {
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }
}

