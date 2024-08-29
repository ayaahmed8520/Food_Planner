package com.example.foodplanner.view.mealDetails;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import com.example.foodplanner.model.FavRepo;
import com.example.foodplanner.model.dp.MealLocalDataSourceImpl;
import com.example.foodplanner.presenter.FavPresenter;
import com.example.foodplanner.view.fav.FavoriteFragmentIn;
import com.example.foodplanner.view.fav.MyAddAndRemoveFavIn;
import com.example.foodplanner.model.firebase.InFirebaseImp;
import com.example.foodplanner.model.mealDetails.MealDetails;
import com.example.foodplanner.presenter.MealDetailsPresenter;
import com.example.foodplanner.view.meal.OnMealClick;

public class ViewDetailsActivityMy extends AppCompatActivity implements MealDetailsIn, MyAddAndRemoveFavIn, OnMealClick ,FavoriteFragmentIn {

    private RecyclerView recyclerViewDetails;
    private MealDetailsAdapter mealDetailsAdapter;
    private String mealId;
    private ArrayList<MealDetails> mealDetailsArrayList;
    private MealDetailsPresenter mealDetailsPresenter;
    private FavPresenter favPresenter;  // Instance of FavPresenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        initValues();


        mealDetailsPresenter = new MealDetailsPresenter(this);

        favPresenter = new FavPresenter(new FavRepo(MealLocalDataSourceImpl.getInstance(getApplicationContext())),this);

        if (mealId != null) {
            mealDetailsPresenter.getMealDetails(mealId);
        } else {
            Toast.makeText(this, "Meal ID not found", Toast.LENGTH_SHORT).show();
        }

        recyclerViewDetails.setHasFixedSize(true);
        recyclerViewDetails.setLayoutManager(new LinearLayoutManager(this));

        mealDetailsAdapter = new MealDetailsAdapter(mealDetailsArrayList, this, this, this);
        recyclerViewDetails.setAdapter(mealDetailsAdapter);
    }

    private void initValues() {
        recyclerViewDetails = findViewById(R.id.rv_mealDetails);
        mealDetailsArrayList = new ArrayList<>();
        mealId = InFirebaseImp.getInstance(this).getSharedPreferences().getString("mealcurrentid", null);
    }

    @Override
    public void resultSuccess(List<MealDetails> meals) {
        mealDetailsArrayList.clear();
        mealDetailsArrayList.addAll(meals);
        mealDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultFailure(String error) {
        Toast.makeText(this, "Failed to fetch meal details: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToCalendar(String meal) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mealcurrentname", meal);
        editor.apply();
    }

    @Override
    public void addMeal(MealDetails mealDetails) {
        favPresenter.addMeal(mealDetails);
    }

    @Override
    public void showMeals(List<MealDetails> meals) {

    }

    @Override
    public void failedToDisplay(String error) {

    }

    @Override
    public void removeMeal(MealDetails mealDetails) {
        favPresenter.removeFromFav(mealDetails);
    }

    @Override
    public void OnMealClicked(String index) {
        // Handle meal click
    }
}

