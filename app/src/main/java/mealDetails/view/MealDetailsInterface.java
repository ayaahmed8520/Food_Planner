package mealDetails.view;


import mealDetails.model.SingleMealDetails;

public interface MealDetailsInterface {
        public void onSuccessResult(SingleMealDetails meals);
        public void onFailureResult(String error);
        public void navigateToCalendar(String meal);
}
