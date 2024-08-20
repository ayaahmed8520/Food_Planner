package search.particularArea.presenter;


import java.util.ArrayList;

import meal.model.SingleMeal;
import search.particularArea.view.ParticularAreaMealActivityInterface;
import search.particularArea.model.ParticularAreaMealRepository;

public class ParticularAreaMealPresenter {
    private  static ParticularAreaMealActivityInterface particularAreaMealInterface;

    public static void getParticularAreaMeals(String areaName, ParticularAreaMealActivityInterface particularAreaMealActivityInterface){
        particularAreaMealInterface=particularAreaMealActivityInterface;
        ParticularAreaMealRepository.getParticularAreaMeals(areaName);
    }
    public static void onSuccessResult(ArrayList<SingleMeal> meals){
        particularAreaMealInterface.onSuccessResult(meals);
    }
    public static void onFailureResult(String error){
        particularAreaMealInterface.onFailureResult(error);
    }

}
