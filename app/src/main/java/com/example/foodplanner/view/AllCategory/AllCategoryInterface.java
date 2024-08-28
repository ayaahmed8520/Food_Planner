package com.example.foodplanner.view.AllCategory;



import java.util.List;

import com.example.foodplanner.model.allCategory.Category;

public interface AllCategoryInterface {
    public void getCategories();
    public void resultSuccess(List<Category> categories);
    public void resultFailure(String error);
    public void navToSpecificCategoryMeals(String categoryName);
}
