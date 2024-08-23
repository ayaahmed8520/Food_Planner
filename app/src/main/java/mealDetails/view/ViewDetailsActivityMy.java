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

import calendar.CalendarActivity;
import favorite.presenter.FavPresenter;
import firebase.FirebaseRepoImp;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import dp.MealDetails;
import meal.view.OnMealClick;
import mealDetails.model.MealDetailsResponse;
import network.ApiClient;
import network.ApiService;
import retrofit2.Retrofit;
import favorite.view.MyAddAndRemoveFavIn;


public class ViewDetailsActivityMy extends AppCompatActivity implements MealDetailsIn, MyAddAndRemoveFavIn , OnMealClick {

    private RecyclerView recyclerViewDetails;
    private MealDetailsAdapter mealDetailsAdapter;
    String mealId;
    private ApiService apiService;
    ArrayList<MealDetails> mealDetailsArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        initValues();
        callApi();
    }

    private void initValues()
    {
        recyclerViewDetails = findViewById(R.id.rv_mealDetails);
        mealId =FirebaseRepoImp.getInstance(this).getSharedPreferences().getString("mealcurrentid",null);
        Retrofit retrofitClient = ApiClient.getClient();
        apiService = retrofitClient.create(ApiService.class);
    }



    @SuppressLint("CheckResult")
    private void callApi()
    {
        Observable<MealDetailsResponse> callFirst = apiService.getMealDetailsByID(mealId);
        callFirst.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (@SuppressLint("CheckResult") MealDetailsResponse myResponse) -> {
                            mealDetailsArrayList = myResponse.getMeals();
                            recyclerViewDetails.setHasFixedSize(true);

                            mealDetailsAdapter = new MealDetailsAdapter(mealDetailsArrayList, this,this , this);

                            recyclerViewDetails.setAdapter(mealDetailsAdapter);
                        },
                        error->{
                            error.printStackTrace();
                        }
                );
    }



    @Override
    public void resultSuccess(MealDetails meals) {
    }

    @Override
    public void resultFailure(String error) {
    }

    @Override
    public void goToCalendar(String meal) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mealcurrentname", meal);
        editor.apply();

        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    @Override
    public void addMeal(MealDetails mealDetails ) {
        FavPresenter.addMeal(mealDetails,this);

    }
    @Override
    public void removeMeal(MealDetails mealDetails ) {
        FavPresenter.removeFromFav(mealDetails,this);
    }


    @Override
    public void OnMealClicked(String index) {

    }
}

