package favorite.view;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import favorite.presenter.FavPresenter;
import meal.view.OnMealClick;
import dp.MealDetails;
import mealDetails.view.ViewDetailsActivityMy;
import weakPlan.WeeklyPlanMealDetails.view.WeeklyPlanMealDetailsActivity;
import weakPlan.dp.WeeklyPlanMealDetails;


public class Favorite extends Fragment implements FavoriteFragmentIn, OnMealClick {
    RecyclerView favoriteRecyclerView;
    FavoriteMealsAdapter favoriteMealsAdapter;
    LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);

        layoutManager = new LinearLayoutManager(requireContext());
        favoriteMealsAdapter = new FavoriteMealsAdapter(requireContext(), new ArrayList<>(), this,this);
        favoriteRecyclerView.setAdapter(favoriteMealsAdapter);
        favoriteRecyclerView.setLayoutManager(layoutManager);

        FavPresenter.getMeals((LifecycleOwner) requireContext(),requireContext(),this);

    }
    private void initUI(@NonNull View view) {
        favoriteRecyclerView = view.findViewById(R.id.rv_myFavorite);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showMeals(List<MealDetails> meals) {
        favoriteMealsAdapter.setList(meals);
        favoriteMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToDisplay(String error) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void removeMeal(MealDetails meal) {
        FavPresenter.removeFromFav(meal,requireContext());
    }


    @Override
    public void OnMealClicked(String position) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getContext().getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mealcurrentid", position);
        editor.apply();

        Intent intent = new Intent(getContext(), WeeklyPlanMealDetailsActivity.class);
        startActivity(intent);
    }
}