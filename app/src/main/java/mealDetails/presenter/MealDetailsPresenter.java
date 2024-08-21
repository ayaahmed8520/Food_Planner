package mealDetails.presenter;


import mealDetails.model.MealDetailsRepository;
import mealDetails.model.SingleMealDetails;
import mealDetails.view.MealDetailsInterface;

public class MealDetailsPresenter {
    private  static MealDetailsInterface mealDetailsInterface;

    public static void getMealDetailsPresenter(String meal, MealDetailsInterface detailsInterface){
        mealDetailsInterface = detailsInterface;
        MealDetailsRepository.getDetailedMealRepository(meal);
    }
    public static void onSuccessResult(SingleMealDetails meals){
        mealDetailsInterface.onSuccessResult(meals);
    }
    public static void onFailureResult(String error){
        mealDetailsInterface.onFailureResult(error);
    }
}
