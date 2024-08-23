package weakPlan.view;

import dp.MealDetails;
import weakPlan.dp.WeeklyPlanMeal;

public interface AddAndRemoveFromPlan {
    public void addMealToPlan(WeeklyPlanMeal weeklyPlanMeal );
    public void removeMealFromPlan(WeeklyPlanMeal weeklyPlanMeal );
}
