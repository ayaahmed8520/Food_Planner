package mealDetails.view;


import java.net.DatagramSocket;

import mealDetails.model.SingleMealDetails;

public interface MealDetailsInterface {
        public void onSuccessResult(SingleMealDetails meals);
        public void onFailureResult(String error);
        public void navigateToCalendar(String meal);
}
