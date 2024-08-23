package mealDetails.presenter;


import dp.MealDetails;
import mealDetails.model.MealDetailsRepo;
import mealDetails.view.MealDetailsIn;

public class MealDetailsPresenter {
    private  static MealDetailsIn mealDetailsIn;

    public static void getMealDetailsPresenter(String meal, MealDetailsIn detailsInterface){
        mealDetailsIn = detailsInterface;
        MealDetailsRepo.getDetailedMealRepo(meal);
    }
    public static void resultSuccess(MealDetails meals){
        mealDetailsIn.resultSuccess(meals);
    }
    public static void resultFailure(String error){
        mealDetailsIn.resultFailure(error);
    }
}
