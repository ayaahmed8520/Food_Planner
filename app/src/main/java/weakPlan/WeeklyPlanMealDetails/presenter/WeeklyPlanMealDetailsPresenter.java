package weakPlan.WeeklyPlanMealDetails.presenter;

import dp.MealDetails;
import mealDetails.model.MealDetailsRepo;
import mealDetails.view.MealDetailsIn;
import weakPlan.WeeklyPlanMealDetails.model.WeeklyPlanMealDetailsRepo;
import weakPlan.WeeklyPlanMealDetails.view.WeeklyPlanMealMealDetailsIn;
import weakPlan.dp.WeeklyPlanMealDetails;

public class WeeklyPlanMealDetailsPresenter {
    private  static WeeklyPlanMealMealDetailsIn weeklyPlanMealMealDetailsIn;

    public static void getWeeklyPlanMealDetailsPresenter(String meal, WeeklyPlanMealMealDetailsIn weeklyPlanMealDetailsIn){
        weeklyPlanMealMealDetailsIn = weeklyPlanMealDetailsIn;
        WeeklyPlanMealDetailsRepo.getWeeklyPlanMealDetailsRepo(meal);
    }
    public static void resultSuccess(WeeklyPlanMealDetails meals){
        weeklyPlanMealMealDetailsIn.resultSuccess(meals);
    }
    public static void resultFailure(String error){
        weeklyPlanMealMealDetailsIn.resultFailure(error);
    }
}
