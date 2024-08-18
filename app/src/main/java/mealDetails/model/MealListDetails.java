package mealDetails.model;


import java.util.ArrayList;

public class MealListDetails {
    private ArrayList<SingleMealDetails> meals;
    public MealListDetails(ArrayList<SingleMealDetails> meals) {
        this.meals = meals;
    }
    public ArrayList<SingleMealDetails> getMeals() {
        return meals;
    }
    public void setMeals(ArrayList<SingleMealDetails> meals) {
        this.meals = meals;
    }
}
