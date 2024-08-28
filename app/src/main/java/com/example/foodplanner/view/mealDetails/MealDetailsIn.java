package com.example.foodplanner.view.mealDetails;


import java.util.List;

import com.example.foodplanner.model.mealDetails.MealDetails;

public interface MealDetailsIn {
        public void resultSuccess(List<MealDetails> meals);
        public void resultFailure(String error);
        public void goToCalendar(String meal);
}
