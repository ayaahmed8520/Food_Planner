package com.example.foodplanner.view.fav;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import com.example.foodplanner.model.FavRepo;
import com.example.foodplanner.model.dp.MealLocalDataSourceImpl;
import com.example.foodplanner.presenter.FavPresenter;
import com.example.foodplanner.view.meal.OnMealClick;
import com.example.foodplanner.model.mealDetails.MealDetails;

import com.example.foodplanner.view.weeklyPlanMealDetails.WeeklyPlanMealDetailsActivity;


public class Favorite extends Fragment implements FavoriteFragmentIn, OnMealClick {
    private RecyclerView favoriteRecyclerView;
    private FavoriteMealsAdapter favoriteMealsAdapter;
    private LinearLayoutManager layoutManager;
    private FavPresenter favPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favPresenter = new FavPresenter(new FavRepo(MealLocalDataSourceImpl.getInstance(getContext())),this);
        initUI(view);

        layoutManager = new LinearLayoutManager(requireContext());
        favoriteMealsAdapter = new FavoriteMealsAdapter(requireContext(), new ArrayList<>(), this, this);
        favoriteRecyclerView.setAdapter(favoriteMealsAdapter);
        favoriteRecyclerView.setLayoutManager(layoutManager);


        updateFavoriteMeals();
    }

    private void initUI(@NonNull View view) {
        favoriteRecyclerView = view.findViewById(R.id.rv_myFavorite);
    }

    private void updateFavoriteMeals() {
        favPresenter.getFavoriteMeals().observe(getViewLifecycleOwner(), meals -> {
            favoriteMealsAdapter.setList(meals);
            favoriteMealsAdapter.notifyDataSetChanged();
        });
    }

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
        favPresenter.removeFromFav(meal);
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
