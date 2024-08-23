package search.SpecificCategory.presenter;

import java.util.ArrayList;

import meal.model.Meal;
import search.SpecificCategory.model.SpecificCategoryMealsRepo;
import search.SpecificCategory.view.SpecificCategoryMealsInterface;

public class SpecificCategoryMealsPresenter {
    private  static SpecificCategoryMealsInterface specificCategoryMealsInterface;

    public static void getSpecificCategoryMeals(String categoryName, SpecificCategoryMealsInterface particularCategoryMealsInterface){
        specificCategoryMealsInterface =particularCategoryMealsInterface;
        SpecificCategoryMealsRepo.getParticularCategoryMeals(categoryName);
    }
    public static void resultSuccess(ArrayList<Meal> meals){
        specificCategoryMealsInterface.resultSuccess(meals);
    }
    public static void resultFailure(String error){
        specificCategoryMealsInterface.resultFailure(error);
    }
}
