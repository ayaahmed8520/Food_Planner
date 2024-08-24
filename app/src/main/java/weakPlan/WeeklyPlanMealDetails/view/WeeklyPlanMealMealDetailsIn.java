package weakPlan.WeeklyPlanMealDetails.view;

import dp.MealDetails;
import weakPlan.dp.WeeklyPlanMealDetails;

public interface WeeklyPlanMealMealDetailsIn {

    public void resultSuccess(WeeklyPlanMealDetails meals);
    public void resultFailure(String error);
}
