package weakPlan.view;

import java.util.List;

import weakPlan.dp.WeeklyPlanMeal;

public interface WeekPlanInterface {
    public void showPlannedMeals(List<WeeklyPlanMeal> meals);
    public void failedToDisplayPlanMeals(String error);
    public void removePlannedMeal(WeeklyPlanMeal meal);
}
