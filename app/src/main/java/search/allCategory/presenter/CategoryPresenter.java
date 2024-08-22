package search.allCategory.presenter;


import java.util.ArrayList;

import search.allCategory.model.AllCategoriesRepo;
import search.allCategory.model.Category;
import search.allCategory.view.AllCategoryInterface;

public class CategoryPresenter {
    private  static AllCategoryInterface allCategoryInterface;

    public static void getAllCategories(AllCategoryInterface allCategoriesInterface){
        allCategoryInterface = allCategoriesInterface;
        AllCategoriesRepo.getAllCategories();
    }
    public static void resultSuccess(ArrayList<Category> categories){
        allCategoryInterface.resultSuccess(categories);
    }
    public static void resultFailure(String error){
        allCategoryInterface.resultFailure(error);
    }
}
