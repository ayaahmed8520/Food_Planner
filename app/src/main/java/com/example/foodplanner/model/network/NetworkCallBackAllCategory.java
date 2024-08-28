package com.example.foodplanner.model.network;

import java.util.List;

import com.example.foodplanner.model.allCategory.Category;

public interface NetworkCallBackAllCategory {
    public void onSuccessResult(List<Category> categories);
    public void onFailureResult(String errorMsg);
}
