package weakPlan.view;

import dp.MealDetails;
import weakPlan.dp.WeeklyPlanMeal;
import weakPlan.dp.WeeklyPlanMealDetails;

public interface AddAndRemoveFromPlan {
    public void addMealToPlan(WeeklyPlanMealDetails weeklyPlanMeal );
    public void removeMealFromPlan(WeeklyPlanMealDetails weeklyPlanMeal );
}
