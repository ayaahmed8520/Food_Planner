package com.example.foodplanner.view.SpecificArea;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.foodplanner.presenter.SpecificCountryMealPresenter;


public class SpecificAreaMeal extends AppCompatActivity implements SpecificAreaMealInterface {
    String areaName;
    TextView tvArea;
    RecyclerView recyclerView;
    GridLayoutManager gridlayoutManager;
    SpecificAreaAdapter specificAreaAdapter;
    SearchView searchView;
    ArrayList<Meal> SpecificAreaMeals =new ArrayList<>();
    ArrayList<Meal> MealList =new ArrayList<>();
    ImageView closeScreen;

    SpecificCountryMealPresenter specificCountryMealPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_area_meal);

        specificCountryMealPresenter = new SpecificCountryMealPresenter(this);

        recyclerView= findViewById(R.id.rv_particularAreaMeals);
        gridlayoutManager =new GridLayoutManager(this,2);
        gridlayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        specificAreaAdapter = new SpecificAreaAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(specificAreaAdapter);

        tvArea=findViewById(R.id.tv_searchForParticularArea);
        Intent myIntent = getIntent();
        if (myIntent != null) {
            areaName = myIntent.getStringExtra("areaName");
            tvArea.setText(areaName);
            getSpecificAreaMeals(areaName);
        }

        closeScreen=findViewById(R.id.btn_closeArea);
        closeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView=findViewById(R.id.sv_searchByParticularArea);
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
                    specificAreaAdapter.setList(MealList);
                    specificAreaAdapter.notifyDataSetChanged();
                    Log.i("TAG", "onQueryTextChange displayList: "+ MealList);
                    Log.i("TAG", "onQueryTextChange mealsByArea: "+ SpecificAreaMeals);
                    String search = newText.toLowerCase(Locale.ROOT);
                    for (Meal meal : SpecificAreaMeals) {
                        if (meal.getStrMeal().toLowerCase(Locale.ROOT).startsWith(search)) {
                            MealList.add(meal);
                        }
                    }
                    specificAreaAdapter.setList(MealList);
                    specificAreaAdapter.notifyDataSetChanged();

                }else{
                    MealList.clear();
                    MealList.addAll(SpecificAreaMeals);
                    specificAreaAdapter.setList(MealList);
                    specificAreaAdapter.notifyDataSetChanged();


                }

                return true;
            }
        });
    }

    @Override
    public void getSpecificAreaMeals(String areaName) {
       specificCountryMealPresenter.getSpecificAreaMeal(areaName);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void resultSuccess(List<Meal> meals) {

        SpecificAreaMeals.addAll(meals);
        MealList.addAll(meals);
        specificAreaAdapter.setList(MealList);
        specificAreaAdapter.notifyDataSetChanged();



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