package search.particularCategory;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;


import java.util.ArrayList;
import java.util.Locale;

import meal.model.SingleMeal;
import mealDetails.view.ViewDetailsActivity;

public class ParticularCategoryMealsActivity extends AppCompatActivity implements ParticularCategoryMealsActivityInterface{
    String categoryName;
    TextView tvCategory;
    RecyclerView recyclerView;
    GridLayoutManager gridlayoutManager;
    ParticularCategoryAdapter particularCategoryAdapter;
    SearchView searchView;
    ArrayList<SingleMeal> mealsByCategory=new ArrayList<>();
    ArrayList<SingleMeal> displayList=new ArrayList<>();
    ImageView closeScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_category_meals);

        recyclerView= findViewById(R.id.rv_particular_category_meals);
        gridlayoutManager =new GridLayoutManager(this,2);
        gridlayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        particularCategoryAdapter= new ParticularCategoryAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(particularCategoryAdapter);

        tvCategory=findViewById(R.id.tv_particular_category);
        Intent myIntent = getIntent();
        if (myIntent != null) {
            categoryName = myIntent.getStringExtra("categoryName");
            tvCategory.setText(categoryName);
            getParticularCategoryMeals(categoryName);

        }

        closeScreen=findViewById(R.id.iv_close_search_by_particular_category);
        closeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        searchView=findViewById(R.id.sv_search_in_particular_area);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!newText.isEmpty()){
                    displayList.clear();
                    particularCategoryAdapter.setList(displayList);
                    particularCategoryAdapter.notifyDataSetChanged();
                    String search = newText.toLowerCase(Locale.ROOT);
                    for (SingleMeal meal :mealsByCategory) {
                        if (meal.getStrMeal().toLowerCase(Locale.ROOT).startsWith(search)) {
                            displayList.add(meal);
                        }
                    }
                    particularCategoryAdapter.setList(displayList);
                    particularCategoryAdapter.notifyDataSetChanged();

                }else{
                    displayList.clear();
                    displayList.addAll(mealsByCategory);
                    particularCategoryAdapter.setList(displayList);
                    particularCategoryAdapter.notifyDataSetChanged();


                }

                return true;
            }
        });

    }

    @Override
    public void getParticularCategoryMeals(String categoryName) {
        ParticularCategoryMealsPresenter.getParticularCategoryMeals(categoryName ,this);
    }

    @Override
    public void onSuccessResult(ArrayList<SingleMeal> meals) {
        //send data to the adapter :D
        mealsByCategory.addAll(meals);
        displayList.addAll(meals);
        particularCategoryAdapter.setList(displayList);
        particularCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailureResult(String error) {
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

        Intent intent = new Intent(this, ViewDetailsActivity.class);
        startActivity(intent);
    }
}