package com.example.foodplanner.presenter;


import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.NetworkCallBackAllCategory;

import java.util.List;

import com.example.foodplanner.model.allCategory.AllCategoriesRepo;
import com.example.foodplanner.model.allCategory.Category;
import com.example.foodplanner.view.AllCategory.AllCategoryInterface;

public class CategoryPresenter implements NetworkCallBackAllCategory {
    AllCategoryInterface allCategoryInterface;
    AllCategoriesRepo allCategoriesRepo;

    public CategoryPresenter(AllCategoryInterface allCategoriesInterface){
        this.allCategoryInterface = allCategoriesInterface;
        allCategoriesRepo = new AllCategoriesRepo(MealRemoteDataSourceImpl.getInstance());
    }
    public void getAllCategory(){
        allCategoriesRepo.getAllMealCategory(this);
    }

    @Override
    public void onSuccessResult(List<Category> categories) {
        allCategoryInterface.resultSuccess(categories);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        allCategoryInterface.resultFailure(errorMsg);

    }
}
