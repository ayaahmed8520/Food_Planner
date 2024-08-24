package calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.provider.CalendarContract;

import dp.MealDetails;
import firebase.FirebaseRepoImp;
import weakPlan.dp.WeeklyPlanMeal;
import weakPlan.presenter.WeekPlanMealPresenter;
import weakPlan.view.WeekPlanFragment;

public class CalendarActivity extends AppCompatActivity {

    CalendarView simpleCalendarView;
    Button btn_saveOnApp;
    Spinner sp_mealType;
    private String selectedDate;

    WeeklyPlanMeal weeklyPlanMeal;

    private int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calendar);

        btn_saveOnApp = findViewById(R.id.btn_save);
        sp_mealType = findViewById(R.id.spinner_mealType);
        simpleCalendarView = findViewById(R.id.mealsCalender);



        // Set initial date
        selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        // Set date change listener for CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });


        btn_saveOnApp.setOnClickListener(view -> {

            // Retrieve the passed data
            Intent intent = getIntent();
            String mealImageUrl = intent.getStringExtra("img_meal");
            String mealName = intent.getStringExtra("tv_mealName");
            String mealId = intent.getStringExtra("tv_mealId");

            String mealType = sp_mealType.getSelectedItem().toString();

            Toast.makeText(this, "Date add: " + selectedDate + " MealType: " + mealType, Toast.LENGTH_SHORT).show();
            weeklyPlanMeal = new WeeklyPlanMeal(mealId, selectedDate, mealType, mealName, mealImageUrl);

            WeekPlanMealPresenter.addMeal(weeklyPlanMeal, this);
            Toast.makeText(this, "Meal add to Plane Successfully: " + selectedDate + " MealType: " + mealType, Toast.LENGTH_SHORT).show();

             Intent intent1 = new Intent(CalendarActivity.this, WeekPlanFragment.class);


        });





    }
}










































