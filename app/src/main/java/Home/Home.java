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


public class Home extends Fragment implements OnMealClick{
    private RecyclerView recyclerViewFirst;
    private RecyclerView recyclerViewSecond;
    private MealAdapter adapter;
    private MainMealAdapter adapterBig;
    private ArrayList<SingleMeal> simpleMeals;
    private String[] randomCountries;
    private String[] randomCategories;
    Retrofit retrofitClient;
    ApiService retrofitInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrofitClient = ApiClient.getClient();
        retrofitInterface = retrofitClient.create(ApiService.class);

        initializeVariables(view);
        //setListeners();
        apiFirstCall();
        apiSecondCall();
    }

    private void initializeVariables(View view)
    {
        recyclerViewFirst = view.findViewById(R.id.recycler_mainItem);
        recyclerViewSecond = view.findViewById(R.id.recycler_randomCategory);
        randomCategories = new String[]{"Beef","Chicken","Dessert","Lamb","Miscellaneous","Pork","Seafood","Side","Vegetarian"};
    }

    @SuppressLint("CheckResult")
    private void apiFirstCall()
    {
        Observable<MealList> callFirst = retrofitInterface.getASingleRandomMeal();


        callFirst.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMeals = myResponse.getMeals();
                            recyclerViewFirst.setHasFixedSize(true);
                            adapterBig = new MainMealAdapter(simpleMeals, Home.this);
                            recyclerViewFirst.setAdapter(adapterBig);
                        },
                        error->{
                            error.printStackTrace();
                        }
                );
    }
    @SuppressLint("CheckResult")
    private void apiSecondCall()
    {
        Observable<MealList> callSecond = retrofitInterface.getFilterByCategory(randomCategories[new Random().nextInt(randomCategories.length)]);

        callSecond.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myResponse -> {
                            simpleMeals = myResponse.getMeals();
                            adapter = new MealAdapter(simpleMeals, Home.this);
                            recyclerViewSecond.setAdapter(adapter);
                        },
                        error->{
                            error.printStackTrace();
                        }
                );
    }
    private void setListeners()
    {
//        icon.setOnCheckedChangeListener((buttonView, isChecked) ->{
//            if (isChecked){
//                Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_LONG).show();
//            }else {
//                Toast.makeText(getApplicationContext(),"bye bye",Toast.LENGTH_LONG).show();
//            }
//        } );
    }

    public void OnMealClicked(String position) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getContext().getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mealcurrentid", position);
        editor.apply();

        Intent intent = new Intent(getContext(), ViewDetailsActivity.class);
        startActivity(intent);
    }


}