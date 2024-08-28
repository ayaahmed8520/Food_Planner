package com.example.foodplanner.view.specificIngredient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
import com.example.foodplanner.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.foodplanner.model.meal.Meal;
import com.example.foodplanner.view.mealDetails.ViewDetailsActivityMy;
import com.example.foodplanner.presenter.SpecificIngredientMealsPresenter;

public class SpecificIngredientMeals extends AppCompatActivity implements SpecificIngredientMealsInterface {
    String ingredientName;
    TextView tvIngredient;
    RecyclerView recyclerView;
    GridLayoutManager gridlayoutManager;
    SpecificIngredientAdapter specificIngredientAdapter;
    SearchView searchView;
    ArrayList<Meal> specificIngredientMeals =new ArrayList<>();
    ArrayList<Meal> MealList =new ArrayList<>();
    ImageView closeScreen;
    SpecificIngredientMealsPresenter ingredientMealsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_ingredient_meals);

        ingredientMealsPresenter = new SpecificIngredientMealsPresenter(this);

        recyclerView= findViewById(R.id.rv_particularIngredientMeals);
        gridlayoutManager =new GridLayoutManager(this,2);
        gridlayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        specificIngredientAdapter = new SpecificIngredientAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(specificIngredientAdapter);

        tvIngredient=findViewById(R.id.tv_searchForParticularIngredient);
        Intent myIntent = getIntent();
        if (myIntent != null) {
            ingredientName = myIntent.getStringExtra("ingredientName");
            tvIngredient.setText(ingredientName);
            getSpecificIngredientMeals(ingredientName);

        }

        closeScreen=findViewById(R.id.btn_closeIngredient);
        closeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView=findViewById(R.id.sv_searchByParticularIngredient);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextChange(String newText) {

                if (!newText.isEmpty()){
                    MealList.clear();
                    specificIngredientAdapter.setList(MealList);
                    specificIngredientAdapter.notifyDataSetChanged();
                    String search = newText.toLowerCase(Locale.ROOT);
                    for (Meal meal : specificIngredientMeals) {
                        if (meal.getStrMeal().toLowerCase(Locale.ROOT).startsWith(search)) {
                            MealList.add(meal);
                        }
                    }
                    specificIngredientAdapter.setList(MealList);
                    specificIngredientAdapter.notifyDataSetChanged();

                }else{
                    MealList.clear();
                    MealList.addAll(specificIngredientMeals);
                    specificIngredientAdapter.setList(MealList);
                    specificIngredientAdapter.notifyDataSetChanged();


                }

                return true;
            }
        });

    }

    @Override
    public void getSpecificIngredientMeals(String ingredientName) {
        ingredientMealsPresenter.getSpecificIngredientMeal(ingredientName);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void resultSuccess(List<Meal> meals) {
        //send data to the adapter :D

        specificIngredientMeals.addAll(meals);
        MealList.addAll(meals);
        specificIngredientAdapter.setList(MealList);
        specificIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToViewDetails(String position) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mealcurrentid", position);
        editor.apply();
        Intent intent = new Intent(this, ViewDetailsActivityMy.class);
        startActivity(intent);

    }
}
