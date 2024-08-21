package mealDetails.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;

import calendar.CalendarfromViewDetails;
import favorite.presenter.FavoritePresenter;
import firebase.FirebaseRepoImp;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import mealDetails.model.MealListDetails;
import mealDetails.model.SingleMealDetails;
import network.ApiClient;
import network.ApiService;
import retrofit2.Retrofit;
import search.view.AddAndRemoveFavoriteViewInterface;


public class ViewDetailsActivity extends AppCompatActivity implements MealDetailsInterface,AddAndRemoveFavoriteViewInterface {

    private RecyclerView recyclerViewDetails;
    private MealDetailsAdapter mealDetailsAdapter;
    String mealId;
    private ApiService retrofitInterface;
    ArrayList<SingleMealDetails> detailedMeals;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        initValues();
        callApi();
    }

    @SuppressLint("CheckResult")
    private void callApi()
    {
        Observable<MealListDetails> callFirst = retrofitInterface.getMealDetailsByID(mealId);
        callFirst.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (@SuppressLint("CheckResult") MealListDetails myResponse) -> {
                            detailedMeals = myResponse.getMeals();
                            recyclerViewDetails.setHasFixedSize(true);

                            mealDetailsAdapter = new MealDetailsAdapter(detailedMeals, this,this);



                            recyclerViewDetails.setAdapter(mealDetailsAdapter);
                        },
                        error->{
                            error.printStackTrace();
                        }
                );
    }
    private void initValues()
    {
        recyclerViewDetails = findViewById(R.id.rv_mealDetails);
        mealId = FirebaseRepoImp.getInstance(this).getSharedPreferences().getString("mealcurrentid",null);
        Retrofit retrofitClient = ApiClient.getClient();
        retrofitInterface = retrofitClient.create(ApiService.class);
    }


    @Override
    public void onSuccessResult(SingleMealDetails meals) {
    }

    @Override
    public void onFailureResult(String error) {
    }

    @Override
    public void navigateToCalendar(String meal) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mealcurrentname", meal);
        editor.apply();

        Intent intent = new Intent(this, CalendarfromViewDetails.class);
        startActivity(intent);
    }

    @Override
    public void addMeal(SingleMealDetails detailedMeal ) {
        FavoritePresenter.addMeal(detailedMeal,this);

    }
    @Override
    public void removeMeal(SingleMealDetails detailedMeal ) {
        FavoritePresenter.removeFromFav(detailedMeal,this);
    }


}