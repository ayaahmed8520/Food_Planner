package weakPlan.WeeklyPlanMealDetails.model;

import java.util.ArrayList;

import dp.MealDetails;
import weakPlan.dp.WeeklyPlanMealDetails;

public class WeeklyPlanMealDetailsResponse {
    private ArrayList<WeeklyPlanMealDetails> meals;
    public WeeklyPlanMealDetailsResponse(ArrayList<WeeklyPlanMealDetails> meals) {
        this.meals = meals;
    }
    public ArrayList<WeeklyPlanMealDetails> getMeals() {
        return meals;
    }
    public void setMeals(ArrayList<WeeklyPlanMealDetails> meals) {
        this.meals = meals;
    }
}
