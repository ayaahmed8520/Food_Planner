package Home;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import meal.model.MealList;
import meal.model.SingleMeal;
import meal.view.MealAdapter;
import meal.view.MainMealAdapter;
import meal.view.OnMealClick;
import mealDetails.view.ViewDetailsActivity;
import network.ApiClient;
import network.ApiService;
import retrofit2.Retrofit;

public class Home extends Fragment implements OnMealClick {
    private RecyclerView recyclerViewFirst;
    private RecyclerView recyclerViewSecond;
    private RecyclerView recyclerViewThird;
    private MealAdapter adapter;
    private MealAdapter adapter2;
    private MainMealAdapter adapterBig;
    private ArrayList<SingleMeal> simpleMealsFirst;
    private ArrayList<SingleMeal> simpleMealsSecond;
    private ArrayList<SingleMeal> simpleMealsThird;
    private String[] randomCountries;
    private String[] randomCategories;
    private String[] randomIngrediant;
    Retrofit retrofitClient;
    ApiService apiService;

    private static final String MEAL_CURRENT_ID_KEY = "mealcurrentid";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrofitClient = ApiClient.getClient();
        apiService = retrofitClient.create(ApiService.class);

        recyclerViewFirst = view.findViewById(R.id.recycler_mainItem);
        recyclerViewSecond = view.findViewById(R.id.recycler_randomCategory);
        recyclerViewThird = view.findViewById(R.id.recycler_randomIngredient);

        simpleMealsFirst = new ArrayList<>();
        simpleMealsSecond = new ArrayList<>();
        simpleMealsThird = new ArrayList<>();

        adapterBig = new MainMealAdapter(simpleMealsFirst, Home.this);
        adapter = new MealAdapter(simpleMealsSecond, Home.this);
        adapter2 = new MealAdapter(simpleMealsThird, Home.this);

        recyclerViewFirst.setAdapter(adapterBig);
        recyclerViewSecond.setAdapter(adapter);
        recyclerViewThird.setAdapter(adapter2);

        randomCategories = new String[]{"Beef","Chicken","Dessert","Lamb","Miscellaneous","Pork","Seafood","Side","Vegetarian"};
        randomIngrediant = new String[]{"Beef","Chicken","Potatoes","curry","Pie","Cheese","Seafood","Side","Couscous"};

        apiFirstCall();
        apiSecondCall();
        apiThirdCall();
    }


    private void handleError(Throwable error) {
        error.printStackTrace();
        Toast.makeText(getContext(), "An error occurred, please try again", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("CheckResult")
    private void apiFirstCall() {
        Observable<MealList> callFirst = apiService.getASingleRandomMeal();
        callFirst.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMealsFirst = myResponse.getMeals();
                            if (simpleMealsFirst == null) {
                                simpleMealsFirst = new ArrayList<>();
                            }
                            recyclerViewFirst.setHasFixedSize(true);
                            adapterBig = new MainMealAdapter(simpleMealsFirst, Home.this);
                            recyclerViewFirst.setAdapter(adapterBig);
                        },
                        error -> {
                            error.printStackTrace();
                        }
                );
    }




    @SuppressLint("CheckResult")
    private void apiSecondCall() {
        Observable<MealList> callSecond = apiService.getFilterByCategory(randomCategories[new Random().nextInt(randomCategories.length)]);

        callSecond.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMealsSecond = myResponse.getMeals();
                            adapter = new MealAdapter(simpleMealsSecond, Home.this);
                            recyclerViewSecond.setAdapter(adapter);
                        },
                        error -> error.printStackTrace()
                );
    }

    @SuppressLint("CheckResult")
    private void apiThirdCall() {
        Observable<MealList> callThird = apiService.getFilterByMealIngredient(randomIngrediant[new Random().nextInt(randomIngrediant.length)]);

        callThird.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMealsThird = myResponse.getMeals();
                            adapter2 = new MealAdapter(simpleMealsThird, Home.this);
                            recyclerViewThird.setAdapter(adapter2);
                        },
                        error -> error.printStackTrace()
                );
    }



    public void OnMealClicked(String position) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MEAL_CURRENT_ID_KEY, position);
        editor.apply();

        Intent intent = new Intent(getContext(), ViewDetailsActivity.class);
        startActivity(intent);
    }



}