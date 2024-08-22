package network;


import io.reactivex.rxjava3.core.Observable;
import meal.model.MealList;
import mealDetails.model.MealListDetails;
import retrofit2.http.GET;
import retrofit2.http.Query;
import search.allCategory.model.CategoryResponse;
import search.allIngrediant.model.IngredientResponse;

public interface ApiService {
    @GET("random.php")
    Observable<MealList> getASingleRandomMeal();

    @GET("filter.php")
    Observable<MealList> getFilterByArea(@Query("a") String area);

    @GET("filter.php")
    Observable<MealList> getFilterByCategory(@Query("c") String category);
    @GET("filter.php")
    Observable<MealList> getFilterByMealIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Observable<MealListDetails> getMealDetailsByID(@Query("i") String mealDetails);

    @GET("list.php?i=list")
    Observable<IngredientResponse> getListAllIngredient();

    @GET("categories.php")
    Observable<CategoryResponse> getListAllCategories();

}
