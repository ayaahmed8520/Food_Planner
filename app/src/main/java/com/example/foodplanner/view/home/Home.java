package com.example.foodplanner.view.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import com.example.foodplanner.model.meal.Meal;
import com.example.foodplanner.view.meal.MealAdapter;
import com.example.foodplanner.view.meal.MainMealAdapter;
import com.example.foodplanner.view.meal.OnMealClick;
import com.example.foodplanner.view.mealDetails.ViewDetailsActivityMy;

public class Home extends Fragment implements OnMealClick, InHomeView {

    private RecyclerView recyclerViewFirst;
    private RecyclerView recyclerViewSecond;
    private RecyclerView recyclerViewThird;
    private MealAdapter firstAdapter;
    private MealAdapter secondAdapter;
    private MainMealAdapter mainMealAdapter;
    private ArrayList<Meal> simpleMealsFirst;
    private ArrayList<Meal> simpleMealsSecond;
    private ArrayList<Meal> simpleMealsThird;
    private String[] randomCategories;
    private String[] randomIngredient;
    private HomePresenter homePresenter;

    private static final String MEAL_CURRENT_ID_KEY = "mealcurrentid";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homePresenter = new HomePresenter(this);

        recyclerViewFirst = view.findViewById(R.id.recycler_mainItem);
        recyclerViewSecond = view.findViewById(R.id.recycler_randomCategory);
        recyclerViewThird = view.findViewById(R.id.recycler_randomIngredient);

        simpleMealsFirst = new ArrayList<>();
        simpleMealsSecond = new ArrayList<>();
        simpleMealsThird = new ArrayList<>();

        mainMealAdapter = new MainMealAdapter(simpleMealsFirst, Home.this);
        firstAdapter = new MealAdapter(simpleMealsSecond, Home.this);
        secondAdapter = new MealAdapter(simpleMealsThird, Home.this);

        // Initialize RecyclerViews and Adapters...
        recyclerViewFirst.setAdapter(mainMealAdapter);
        recyclerViewSecond.setAdapter(firstAdapter);
        recyclerViewThird.setAdapter(secondAdapter);


        randomCategories = new String[]{"Beef", "Chicken", "Dessert", "Lamb", "Miscellaneous", "Pork", "Seafood", "Side", "Vegetarian"};
        randomIngredient = new String[]{"Salmon", "Pork", "Potatoes", "Currants", "Pine Nuts", "Cheese", "Sea Salt", "Single Cream", "Cucumber"};


        homePresenter.getRandomMeal();
        homePresenter.getRandomCategory(randomCategories[0]);
        homePresenter.getRandomIngredient(randomIngredient[0]);

//        homePresenter.getRandomMeal();
//        homePresenter.getRandomCategory(randomCategories[new Random().nextInt(randomCategories.length)]);
//        homePresenter.getRandomIngredient(randomIngredient[new Random().nextInt(randomIngredient.length)]);
    }

    @Override
    public void showRandomMealData(List<Meal> meals) {
        mainMealAdapter.setMeals(new ArrayList<>(meals));
    }

    @Override
    public void showCategoryData(List<Meal> meals) {
        firstAdapter.setMeals(new ArrayList<>(meals));
    }

    @Override
    public void showIngredientData(List<Meal> meals) {
        secondAdapter.setMeals(new ArrayList<>(meals));
    }



    @Override
    public void showError(String error) {
        homePresenter.onFailureResult("error on uploading Meals");
    }

    @Override
    public void OnMealClicked(String position) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MEAL_CURRENT_ID_KEY, position);
        editor.apply();

        Intent intent = new Intent(getContext(), ViewDetailsActivityMy.class);
        startActivity(intent);
    }
}
