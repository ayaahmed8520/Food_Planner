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

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import meal.model.MealResponse;
import meal.model.Meal;
import meal.view.MealAdapter;
import meal.view.MainMealAdapter;
import meal.view.OnMealClick;
import mealDetails.view.ViewDetailsActivityMy;
import network.ApiClient;
import network.ApiService;
import retrofit2.Retrofit;


public class Home extends Fragment implements OnMealClick  {

    private RecyclerView recyclerViewFirst;
    private RecyclerView recyclerViewSecond;
    private RecyclerView recyclerViewThird;
    private MealAdapter firstAdapter;
    private MealAdapter secondAdapter;
    private MainMealAdapter mainMealAdapter;
    private ArrayList<Meal> simpleMealsFirst;
    private ArrayList<Meal> simpleMealsSecond;
    private ArrayList<Meal> simpleMealsThird;
    private String[] randomCategories;
    private String[] randomIngredient;
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

        mainMealAdapter = new MainMealAdapter(simpleMealsFirst, Home.this);
        firstAdapter = new MealAdapter(simpleMealsSecond, Home.this);
        secondAdapter = new MealAdapter(simpleMealsThird, Home.this);

        recyclerViewFirst.setAdapter(mainMealAdapter);
        recyclerViewSecond.setAdapter(firstAdapter);
        recyclerViewThird.setAdapter(secondAdapter);

        randomCategories = new String[]{"Beef","Chicken","Dessert","Lamb","Miscellaneous","Pork","Seafood","Side","Vegetarian"};
        randomIngredient = new String[]{"Salmon","Pork","Potatoes","Currants","Pine Nuts","Cheese","Sea Salt","Single Cream","Cucumber"};

        apiFirstCall();
        apiSecondCall();
        apiThirdCall();
    }




    @SuppressLint("CheckResult")
    private void apiFirstCall() {
        Observable<MealResponse> callFirst = apiService.getASingleRandomMeal();
        callFirst.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMealsFirst = myResponse.getMeals();
                            if (simpleMealsFirst == null) {
                                simpleMealsFirst = new ArrayList<>();
                            }
                            recyclerViewFirst.setHasFixedSize(true);
                            mainMealAdapter = new MainMealAdapter(simpleMealsFirst, Home.this);
                            recyclerViewFirst.setAdapter(mainMealAdapter);
                        },
                        error -> {
                            error.printStackTrace();
                        }
                );
    }




    @SuppressLint("CheckResult")
    private void apiSecondCall() {
        Observable<MealResponse> callSecond = apiService.getFilterByCategory(randomCategories[new Random().nextInt(randomCategories.length)]);

        callSecond.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMealsSecond = myResponse.getMeals();
                            firstAdapter = new MealAdapter(simpleMealsSecond, Home.this);
                            recyclerViewSecond.setAdapter(firstAdapter);
                        },
                        error -> error.printStackTrace()
                );
    }

    @SuppressLint("CheckResult")
    private void apiThirdCall() {
        Observable<MealResponse> callThird = apiService.getFilterByMealIngredient(randomIngredient[new Random().nextInt(randomIngredient.length)]);

        callThird.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMealsThird = myResponse.getMeals();
                            secondAdapter = new MealAdapter(simpleMealsThird, Home.this);
                            recyclerViewThird.setAdapter(secondAdapter);
                        },
                        error -> error.printStackTrace()
                );
    }



    public void OnMealClicked(String position) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MEAL_CURRENT_ID_KEY, position);
        editor.apply();

        Intent intent = new Intent(getContext(), ViewDetailsActivityMy.class);
        startActivity(intent);
    }



}