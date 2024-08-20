package search.allCategory.view;



import java.util.ArrayList;

import search.allCategory.model.Category;

public interface AllCategoriesActivityInterface {
    public void getCategories();
    public void onSuccessResult(ArrayList<Category> categories);
    public void onFailureResult(String error);
    public void navigateToParticularCategoryMeals(String categoryName);
}
