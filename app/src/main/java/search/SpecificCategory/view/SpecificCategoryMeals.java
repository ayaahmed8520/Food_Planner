package search.SpecificCategory.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;


import java.util.ArrayList;
import java.util.Locale;

import meal.model.Meal;
import mealDetails.view.ViewDetailsActivityMy;
import search.SpecificCategory.presenter.SpecificCategoryMealsPresenter;

public class SpecificCategoryMeals extends AppCompatActivity implements SpecificCategoryMealsInterface {
    String categoryName;
    TextView tvCategory;
    RecyclerView recyclerView;
    GridLayoutManager gridlayoutManager;
    SpecificCategoryAdapter specificCategoryAdapter;
    SearchView searchView;
    ArrayList<Meal> specificCategoryMeals =new ArrayList<>();
    ArrayList<Meal> MealList =new ArrayList<>();
    ImageView closeScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_category_meals);

        recyclerView= findViewById(R.id.rv_particularCategoryMeals);
        gridlayoutManager =new GridLayoutManager(this,2);
        gridlayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        specificCategoryAdapter = new SpecificCategoryAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(specificCategoryAdapter);

        tvCategory=findViewById(R.id.tv_searchForParticularCategory);
        Intent myIntent = getIntent();
        if (myIntent != null) {
            categoryName = myIntent.getStringExtra("categoryName");
            tvCategory.setText(categoryName);
            getSpecificCategoryMeals(categoryName);

        }

        closeScreen=findViewById(R.id.btn_closeCategory);
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
                    specificCategoryAdapter.setList(MealList);
                    specificCategoryAdapter.notifyDataSetChanged();
                    String search = newText.toLowerCase(Locale.ROOT);
                    for (Meal meal : specificCategoryMeals) {
                        if (meal.getStrMeal().toLowerCase(Locale.ROOT).startsWith(search)) {
                            MealList.add(meal);
                        }
                    }
                    specificCategoryAdapter.setList(MealList);
                    specificCategoryAdapter.notifyDataSetChanged();

                }else{
                    MealList.clear();
                    MealList.addAll(specificCategoryMeals);
                    specificCategoryAdapter.setList(MealList);
                    specificCategoryAdapter.notifyDataSetChanged();


                }

                return true;
            }
        });

    }

    @Override
    public void getSpecificCategoryMeals(String categoryName) {
        SpecificCategoryMealsPresenter.getSpecificCategoryMeals(categoryName ,this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void resultSuccess(ArrayList<Meal> meals) {
        specificCategoryMeals.addAll(meals);
        MealList.addAll(meals);
        specificCategoryAdapter.setList(MealList);
        specificCategoryAdapter.notifyDataSetChanged();

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