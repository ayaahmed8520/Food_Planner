package com.example.foodplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.foodplanner.model.WeekPlanRepo;
import com.example.foodplanner.model.dp.weekPlanDB.WeekPlanLocalDataSource;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;
import com.example.foodplanner.presenter.WeekPlanMealPresenter;
import com.example.foodplanner.view.weekPlan.WeekPlanInterface;

public class CalendarActivity extends AppCompatActivity implements WeekPlanInterface {

    CalendarView simpleCalendarView;
    Button btn_saveOnApp;
    Spinner sp_mealType;
    private String selectedDate;

    WeeklyPlanMeal weeklyPlanMeal;
    WeekPlanMealPresenter weekPlanMealPresenter;

    private int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calendar);

        btn_saveOnApp = findViewById(R.id.btn_save);
        sp_mealType = findViewById(R.id.spinner_mealType);
        simpleCalendarView = findViewById(R.id.mealsCalender);



        weekPlanMealPresenter = new WeekPlanMealPresenter(new WeekPlanRepo(WeekPlanLocalDataSource.getInstance(getApplicationContext())), this);


        selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());


        simpleCalendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });

        btn_saveOnApp.setOnClickListener(view -> {


            Intent intent = getIntent();
            String mealImageUrl = intent.getStringExtra("img_meal");
            String mealName = intent.getStringExtra("tv_mealName");
            String mealId = intent.getStringExtra("tv_mealId");

            String mealType = sp_mealType.getSelectedItem().toString();

            Toast.makeText(this, "Date add: " + selectedDate + " MealType: " + mealType, Toast.LENGTH_SHORT).show();
            weeklyPlanMeal = new WeeklyPlanMeal(mealId, selectedDate, mealType, mealName, mealImageUrl);

            // Use the presenter to add meal to the plan
            weekPlanMealPresenter.addMealToPlan(weeklyPlanMeal);
            Toast.makeText(this, "Meal added to Plan Successfully: " + selectedDate + " MealType: " + mealType, Toast.LENGTH_SHORT).show();



        });
    }


    @Override
    public void showPlannedMeals(List<WeeklyPlanMeal> meals) {

    }

    @Override
    public void failedToDisplayPlanMeals(String error) {

    }

    @Override
    public void removePlannedMeal(WeeklyPlanMeal meal) {

    }
}











































