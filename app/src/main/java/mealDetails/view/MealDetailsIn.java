package mealDetails.view;


import dp.MealDetails;

public interface MealDetailsIn {
        public void resultSuccess(MealDetails meals);
        public void resultFailure(String error);
        public void goToCalendar(String meal);
}
