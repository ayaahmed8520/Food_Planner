package search.allCategory.view;



import java.util.ArrayList;

import search.allCategory.model.Category;

public interface AllCategoryInterface {
    public void getCategories();
    public void resultSuccess(ArrayList<Category> categories);
    public void resultFailure(String error);
    public void navToSpecificCategoryMeals(String categoryName);
}
