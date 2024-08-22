package search.SpecificArea.presenter;


import java.util.ArrayList;

import meal.model.SingleMeal;
import search.SpecificArea.view.SpecificAreaMealInterface;
import search.SpecificArea.model.SpecificCountryMealRepo;

public class SpecificCountryMealPresenter {
    private  static SpecificAreaMealInterface particularAreaMealInterface;

    public static void getSpecificAreaMeals(String areaName, SpecificAreaMealInterface specificAreaMealInterface){
        particularAreaMealInterface= specificAreaMealInterface;
        SpecificCountryMealRepo.getParticularCountryMeals(areaName);
    }
    public static void resultSuccess(ArrayList<SingleMeal> meals){
        particularAreaMealInterface.resultSuccess(meals);
    }
    public static void resultFailure(String error){
        particularAreaMealInterface.resultFailure(error);
    }

}

