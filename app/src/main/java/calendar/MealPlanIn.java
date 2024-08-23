package calendar;

import dp.MealDetails;

public interface MealPlanIn {
    public void resultPlanSuccess(MealDetails meals);
    public void resultPlanFailure(String error);
    public void addToPlan(String meal);
}
