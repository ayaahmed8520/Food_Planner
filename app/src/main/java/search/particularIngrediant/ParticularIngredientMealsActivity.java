package search.particularIngrediant;

import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Locale;

import meal.model.SingleMeal;
import mealDetails.view.ViewDetailsActivity;

public class ParticularIngredientMealsActivity extends AppCompatActivity implements ParticularIngredientMealsActivityInterface {
    String ingredientName;
    TextView tvIngredient;
    RecyclerView recyclerView;
    GridLayoutManager gridlayoutManager;
    ParticularIngredientAdapter particularIngredientAdapter;
    SearchView searchView;
    ArrayList<SingleMeal> mealsByIngredient=new ArrayList<>();
    ArrayList<SingleMeal> displayList=new ArrayList<>();
    ImageView closeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_ingredient_meals);

        recyclerView= findViewById(R.id.rv_particular_ingredient_meals);
        gridlayoutManager =new GridLayoutManager(this,2);
        gridlayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        particularIngredientAdapter= new ParticularIngredientAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(particularIngredientAdapter);

        tvIngredient=findViewById(R.id.tv_particular_ingredient);
        Intent myIntent = getIntent();
        if (myIntent != null) {
            ingredientName = myIntent.getStringExtra("ingredientName");
            tvIngredient.setText(ingredientName);
            getParticularIngredientMeals(ingredientName);

        }

        closeScreen=findViewById(R.id.close_ingredient_meals);
        closeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView=findViewById(R.id.sv_search_by_particular_ingredient);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!newText.isEmpty()){
                    displayList.clear();
                    particularIngredientAdapter.setList(displayList);
                    particularIngredientAdapter.notifyDataSetChanged();
                    String search = newText.toLowerCase(Locale.ROOT);
                    for (SingleMeal meal :mealsByIngredient) {
                        if (meal.getStrMeal().toLowerCase(Locale.ROOT).startsWith(search)) {
                            displayList.add(meal);
                        }
                    }
                    particularIngredientAdapter.setList(displayList);
                    particularIngredientAdapter.notifyDataSetChanged();

                }else{
                    displayList.clear();
                    displayList.addAll(mealsByIngredient);
                    particularIngredientAdapter.setList(displayList);
                    particularIngredientAdapter.notifyDataSetChanged();


                }

                return true;
            }
        });

    }

    @Override
    public void getParticularIngredientMeals(String ingredientName) {
        ParticularIngredientMealsPresenter.getParticularIngredientMeals(ingredientName ,this);


    }

    @Override
    public void onSuccessResult(ArrayList<SingleMeal> meals) {
        //send data to the adapter :D

        mealsByIngredient.addAll(meals);
        displayList.addAll(meals);
        particularIngredientAdapter.setList(displayList);
        particularIngredientAdapter.notifyDataSetChanged();
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