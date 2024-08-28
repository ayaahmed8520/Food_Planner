package com.example.foodplanner.model.network;


import com.example.foodplanner.model.meal.MealResponse;
import com.example.foodplanner.model.mealDetails.MealDetailsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.foodplanner.model.allCategory.CategoryResponse;
import com.example.foodplanner.model.allIngredient.IngredientResponse;
import com.example.foodplanner.model.weeklyPlanMealDetails.WeeklyPlanMealDetailsResponse;

public interface ApiService {
    @GET("random.php")
    Call<MealResponse> getASingleRandomMeal();

    @GET("filter.php")
    Call<MealResponse> getFilterByArea(@Query("a") String area);

    @GET("filter.php")
    Call<MealResponse> getFilterByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<MealResponse> getFilterByMealIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Call<MealDetailsResponse> getMealDetailsByID(@Query("i") String mealDetails);
    @GET("lookup.php")
    Call<WeeklyPlanMealDetailsResponse> getMealDetailsByID2(@Query("i") String mealDetails);

    @GET("list.php?i=list")
    Call<IngredientResponse> getListAllIngredient();

    @GET("categories.php")
    Call<CategoryResponse> getListAllCategories();

}


