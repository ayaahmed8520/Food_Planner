package com.example.foodplanner.model.mealDetails;


import java.util.ArrayList;

public class MealDetailsResponse {
    private ArrayList<MealDetails> meals;
    public MealDetailsResponse(ArrayList<MealDetails> meals) {
        this.meals = meals;
    }
    public ArrayList<MealDetails> getMeals() {
        return meals;
    }
    public void setMeals(ArrayList<MealDetails> meals) {
        this.meals = meals;
    }
}
