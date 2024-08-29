package com.example.foodplanner.view.weekPlan;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
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

import com.example.foodplanner.model.WeekPlanRepo;
import com.example.foodplanner.model.dp.weekPlanDB.WeekPlanLocalDataSource;
import com.example.foodplanner.view.meal.OnMealClick;
import com.example.foodplanner.view.weeklyPlanMealDetails.WeeklyPlanMealDetailsActivity;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;
import com.example.foodplanner.presenter.WeekPlanMealPresenter;


public class WeekPlanFragment extends Fragment implements WeekPlanInterface, OnMealClick {

    RecyclerView planRecyclerView;
    WeekPlanMealAdapter weekPlanMealAdapter;
    LinearLayoutManager layoutManager;
    WeekPlanMealPresenter weekPlanMealPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weekPlanMealPresenter = new WeekPlanMealPresenter(new WeekPlanRepo(WeekPlanLocalDataSource.getInstance(requireContext())),this);
        initUI(view);

        layoutManager = new LinearLayoutManager(requireContext());
        weekPlanMealAdapter = new WeekPlanMealAdapter(requireContext(), new ArrayList<>(), this,this);
        planRecyclerView.setAdapter(weekPlanMealAdapter);
        planRecyclerView.setLayoutManager(layoutManager);

        observePlannedMeals();

    }
    private void observePlannedMeals() {
        weekPlanMealPresenter.getPlannedMeals().observe(getViewLifecycleOwner(), meals -> {
            weekPlanMealAdapter.setList(meals);
            weekPlanMealAdapter.notifyDataSetChanged();
        });
    }

    private void initUI(@NonNull View view) {
        planRecyclerView = view.findViewById(R.id.rv_PlanMeals);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showPlannedMeals(List<WeeklyPlanMeal> meals) {
        weekPlanMealAdapter.setList(meals);
        weekPlanMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToDisplayPlanMeals(String error) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void removePlannedMeal(WeeklyPlanMeal meal) {
        weekPlanMealPresenter.removeFromPlan(meal);
    }


    @Override
    public void OnMealClicked(String position) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getContext().getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("planMealCurrentId", position);
        editor.apply();

        Intent intent = new Intent(getContext(), WeeklyPlanMealDetailsActivity.class);
        startActivity(intent);
    }
}
