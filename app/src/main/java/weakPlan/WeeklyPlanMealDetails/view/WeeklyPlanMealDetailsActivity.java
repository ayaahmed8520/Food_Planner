package weakPlan.WeeklyPlanMealDetails.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;

import dp.MealDetails;
import favorite.presenter.FavPresenter;
import favorite.view.MyAddAndRemoveFavIn;
import firebase.FirebaseRepoImp;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import meal.view.OnMealClick;
import mealDetails.model.MealDetailsResponse;
import mealDetails.view.MealDetailsAdapter;
import mealDetails.view.MealDetailsIn;
import network.ApiClient;
import network.ApiService;
import retrofit2.Retrofit;
import weakPlan.WeeklyPlanMealDetails.model.WeeklyPlanMealDetailsResponse;
import weakPlan.dp.WeeklyPlanMealDetails;
import weakPlan.view.AddAndRemoveFromPlan;

public class WeeklyPlanMealDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDetails;
    private WeeklyPlanMealDetailsAdapter mealDetailsAdapter;
    String mealId;
    private ApiService apiService;
    ArrayList<WeeklyPlanMealDetails> mealDetailsArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_plan_meal_details);
        initValues();
        callApi();
    }

    private void initValues()
    {
        recyclerViewDetails = findViewById(R.id.rv_mealDetails);
        mealId = FirebaseRepoImp.getInstance(this).getSharedPreferences().getString("planMealCurrentId",null);
        Retrofit retrofitClient = ApiClient.getClient();
        apiService = retrofitClient.create(ApiService.class);
    }



    @SuppressLint("CheckResult")
    private void callApi()
    {
        Observable<WeeklyPlanMealDetailsResponse> callFirst = apiService.getMealDetailsByID2(mealId);
        callFirst.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (@SuppressLint("CheckResult") WeeklyPlanMealDetailsResponse myResponse) -> {
                            mealDetailsArrayList = myResponse.getMeals();
                            recyclerViewDetails.setHasFixedSize(true);

                            mealDetailsAdapter = new WeeklyPlanMealDetailsAdapter(mealDetailsArrayList);

                            recyclerViewDetails.setAdapter(mealDetailsAdapter);
                        },
                        error->{
                            error.printStackTrace();
                        }
                );
    }


}

