package com.example.foodplanner.view.weeklyPlanMealDetails;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import com.example.foodplanner.model.firebase.InFirebaseImp;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.network.ApiService;

import retrofit2.Retrofit;
import com.example.foodplanner.presenter.WeeklyPlanMealDetailsPresenter;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;

public class WeeklyPlanMealDetailsActivity extends AppCompatActivity implements WeeklyPlanMealMealDetailsIn {

    private RecyclerView recyclerViewDetails;
    private WeeklyPlanMealDetailsAdapter mealDetailsAdapter;
    private String mealId;
    private ApiService apiService;
    private ArrayList<WeeklyPlanMealDetails> mealDetailsArrayList = new ArrayList<>();
    private WeeklyPlanMealDetailsPresenter planMealDetailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_plan_meal_details);
        planMealDetailsPresenter = new WeeklyPlanMealDetailsPresenter(this);
        initValues();
        if (mealId != null) {
            planMealDetailsPresenter.getPlanMealDetails(mealId);
        } else {
            Toast.makeText(this, "Meal ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void initValues() {
        recyclerViewDetails = findViewById(R.id.rv_mealDetails);
        mealId = InFirebaseImp.getInstance(this).getSharedPreferences().getString("planMealCurrentId", null);
        Retrofit retrofitClient = ApiClient.getClient();
        apiService = retrofitClient.create(ApiService.class);


        mealDetailsAdapter = new WeeklyPlanMealDetailsAdapter(mealDetailsArrayList);
        recyclerViewDetails.setAdapter(mealDetailsAdapter);
    }

    @Override
    public void resultSuccess(List<WeeklyPlanMealDetails> meals) {
        mealDetailsArrayList.clear();
        mealDetailsArrayList.addAll(meals);
        mealDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultFailure(String error) {
        Toast.makeText(this, "Failed to fetch meal details: " + error, Toast.LENGTH_SHORT).show();
    }
}
