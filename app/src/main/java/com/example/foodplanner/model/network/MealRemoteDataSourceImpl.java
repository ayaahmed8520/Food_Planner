package com.example.foodplanner.model.network;


import android.util.Log;


import com.example.foodplanner.model.meal.MealResponse;
import com.example.foodplanner.model.mealDetails.MealDetailsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.foodplanner.model.weeklyPlanMealDetails.WeeklyPlanMealDetailsResponse;

import com.example.foodplanner.model.allIngredient.IngredientResponse;

import com.example.foodplanner.model.allCategory.CategoryResponse;


public class MealRemoteDataSourceImpl {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "API_Client";
    private ApiService apiService;
    private static MealRemoteDataSourceImpl mealsRemoteDataSource = null;
    private static Retrofit retrofit;

    private MealRemoteDataSourceImpl(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    public static MealRemoteDataSourceImpl getInstance(){
        if(mealsRemoteDataSource == null){
            mealsRemoteDataSource = new MealRemoteDataSourceImpl();
        }
        return mealsRemoteDataSource;
    }

    public void makeRandomMealNetworkCall (NetworkCallback networkCallback){
        Call<MealResponse> call = apiService.getASingleRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals() , 1);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeRandomCategoryNetworkCall (NetworkCallback networkCallback, String category){
        Call<MealResponse> call = apiService.getFilterByCategory(category);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals() , 2);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeRandomIngredientNetworkCall (NetworkCallback networkCallback, String ingredient){
        Call<MealResponse> call = apiService.getFilterByMealIngredient(ingredient);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals() , 3);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeMealDetailsNetworkCall (NetworkCallBackDetails networkCallback, String meal){
        Call<MealDetailsResponse> call = apiService.getMealDetailsByID(meal);
        call.enqueue(new Callback<MealDetailsResponse>() {
            @Override
            public void onResponse(Call<MealDetailsResponse> call, Response<MealDetailsResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealDetailsResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeAllCategoryNetworkCall (NetworkCallBackAllCategory networkCallback){
        Call<CategoryResponse> call = apiService.getListAllCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getCategories().size() );
                    networkCallback.onSuccessResult(response.body().getCategories());
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeAllIngredientNetworkCall (NetworkCallBackAllIngredient networkCallback){
        Call<IngredientResponse> call = apiService.getListAllIngredient();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeSpecificAreaNetworkCall (NetworkCallback networkCallback , String areaName){
        Call<MealResponse> call = apiService.getFilterByArea(areaName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals() , 1);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeSpecificCategoryNetworkCall (NetworkCallback networkCallback , String categoryName){
        Call<MealResponse> call = apiService.getFilterByCategory(categoryName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals() , 1);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeSpecificIngredientNetworkCall (NetworkCallback networkCallback , String IngredientName){
        Call<MealResponse> call = apiService.getFilterByMealIngredient(IngredientName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals() , 1);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makePlanMealDetailsNetworkCall (NetworkCallBackPlanMeals networkCallback, String meal){
        Call<WeeklyPlanMealDetailsResponse> call = apiService.getMealDetailsByID2(meal);
        call.enqueue(new Callback<WeeklyPlanMealDetailsResponse>() {
            @Override
            public void onResponse(Call<WeeklyPlanMealDetailsResponse> call, Response<WeeklyPlanMealDetailsResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+ response.body().getMeals().size() );
                    networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<WeeklyPlanMealDetailsResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

}




