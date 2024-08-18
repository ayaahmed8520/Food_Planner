package meal.model;

import java.util.ArrayList;

public class MealList {
    private ArrayList<SingleMeal> meals;

    public MealList(ArrayList<SingleMeal> meals) {
        this.meals = meals;
    }

    public ArrayList<SingleMeal> getMeals() {
        return meals;
    }


    public void setMeals(ArrayList<SingleMeal> meals) {
        this.meals = meals;
    }
}