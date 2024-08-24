package network;


import io.reactivex.rxjava3.core.Observable;
import meal.model.MealResponse;
import mealDetails.model.MealDetailsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import search.allCategory.model.CategoryResponse;
import search.allIngrediant.model.IngredientResponse;
import weakPlan.WeeklyPlanMealDetails.model.WeeklyPlanMealDetailsResponse;

public interface ApiService {
    @GET("random.php")
    Observable<MealResponse> getASingleRandomMeal();

    @GET("filter.php")
    Observable<MealResponse> getFilterByArea(@Query("a") String area);

    @GET("filter.php")
    Observable<MealResponse> getFilterByCategory(@Query("c") String category);
    @GET("filter.php")
    Observable<MealResponse> getFilterByMealIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Observable<MealDetailsResponse> getMealDetailsByID(@Query("i") String mealDetails);
    @GET("lookup.php")
    Observable<WeeklyPlanMealDetailsResponse> getMealDetailsByID2(@Query("i") String mealDetails);

    @GET("list.php?i=list")
    Observable<IngredientResponse> getListAllIngredient();

    @GET("categories.php")
    Observable<CategoryResponse> getListAllCategories();

}
