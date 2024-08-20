package search.allCategory.presenter;


import java.util.ArrayList;

import search.allCategory.model.AllCategoriesRepository;
import search.allCategory.model.Category;
import search.allCategory.view.AllCategoriesActivityInterface;

public class AllCategoriesPresenter {
    private  static AllCategoriesActivityInterface allCategoriesActivityInterface;

    public static void getAllCategories(AllCategoriesActivityInterface allCategoriesInterface){
        allCategoriesActivityInterface= allCategoriesInterface;
        AllCategoriesRepository.getAllCategories();
    }
    public static void onSuccessResult(ArrayList<Category> categories){
        allCategoriesActivityInterface.onSuccessResult(categories);
    }
    public static void onFailureResult(String error){
        allCategoriesActivityInterface.onFailureResult(error);
    }
}
