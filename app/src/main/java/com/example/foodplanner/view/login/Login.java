package com.example.foodplanner.view.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.view.MainActivity;
import com.example.foodplanner.R;

import com.example.foodplanner.model.Backup.BackupDB;
import com.example.foodplanner.presenter.login.LoginPresenterIn;
import com.example.foodplanner.presenter.login.LoginPresenterInImp;
import com.example.foodplanner.model.dp.AppDataBase;
import com.example.foodplanner.model.dp.MealDAO;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDao;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetailsDao;

public class Login extends AppCompatActivity implements LoginListener {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    private LoginPresenterIn loginPresenterIn;

    WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    WeeklyPlanMealDao weeklyPlanMealDao;
    MealDAO mealDAO;
    BackupDB backupDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenterIn = new LoginPresenterInImp(this);

        etEmail = findViewById(R.id.et_userEmail);
        etPassword = findViewById(R.id.textInput_userPassword);
        btnLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progress_bar);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (validateCredentials(email, password)) {
                    progressBar.setVisibility(View.VISIBLE);
                    loginPresenterIn.userLogin(email, password);

                    mealDAO = AppDataBase.getInstance(v.getContext()).mealDAO();
                    weeklyPlanMealDao = AppDataBase.getInstance(v.getContext()).getWeeklyPlanMealDao();
                    weeklyPlanMealDetailsDao = AppDataBase.getInstance(v.getContext()).getWeeklyPlanMealDetailsDao();
                    backupDB = new BackupDB(mealDAO, weeklyPlanMealDao, weeklyPlanMealDetailsDao);
                    backupDB.restoreDataFromFirestore();
                }
            }
        });
    }






    @Override
    public void validationError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userLoginSuccess(String clientID) {
        SharedPreferences sharedPreferences = getSharedPreferences("foodPlanner_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("clientID", clientID);
        editor.apply();

        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void userLoginFailure(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean validateCredentials(String userEmail, String userPassword) {
        boolean flag = true;

        if (TextUtils.isEmpty(userPassword)) {
            etPassword.setError("Password field can't be empty");
            etPassword.requestFocus();
            flag = false;
        } else if (userPassword.length() < 8) {
            etPassword.setError("Password should be at least 8 characters including (digits and letters)");
            etPassword.requestFocus();
            flag = false;
        }

        if (TextUtils.isEmpty(userEmail)) {
            etEmail.setError("Email field can't be empty");
            etEmail.requestFocus();
            flag = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            etEmail.setError("Enter a valid userEmail address: example@example.com");
            etEmail.requestFocus();
            flag = false;
        }

        return flag;
    }
}