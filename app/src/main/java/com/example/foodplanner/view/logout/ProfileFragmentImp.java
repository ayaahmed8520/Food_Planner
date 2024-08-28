package com.example.foodplanner.view.logout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodplanner.view.LoginAndSignUpScreen;
import com.example.foodplanner.R;

import com.example.foodplanner.model.Backup.BackupDB;
import com.example.foodplanner.model.dp.AppDataBase;
import com.example.foodplanner.model.dp.MealDAO;
import com.example.foodplanner.model.firebase.FirebasePresenterImp;
import com.example.foodplanner.model.firebase.FirebasePresenter;
import com.example.foodplanner.model.firebase.InFirebaseImp;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDao;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetailsDao;

public class ProfileFragmentImp extends Fragment implements ProfileFragmentIn {

    AlertDialog.Builder builder;
    FirebasePresenter firebasePresenter;
    private ProgressBar progressBar;
    BackupDB backupDB;
    WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    WeeklyPlanMealDao weeklyPlanMealDao;
    MealDAO mealDAO;


    Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weeklyPlanMealDao = AppDataBase.getInstance(this.getContext()).getWeeklyPlanMealDao();
        weeklyPlanMealDetailsDao = AppDataBase.getInstance(this.getContext()).getWeeklyPlanMealDetailsDao();
        mealDAO = AppDataBase.getInstance(this.getContext()).mealDAO();
        backupDB = new BackupDB(mealDAO,weeklyPlanMealDao,weeklyPlanMealDetailsDao);
        backupDB.backupDataToFirestore();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar= view.findViewById(R.id.progress_bar);

        firebasePresenter = new FirebasePresenterImp(this, InFirebaseImp.getInstance(requireContext()));

        builder = new AlertDialog.Builder(requireContext());
        btnLogout=view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutAlertDialog();

            }
        });
    }

    @Override
    public void logoutCurrentUser() {
        firebasePresenter.logoutCurrentUser();
    }

    @Override
    public void successLogOut() {

        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(requireContext(), LoginAndSignUpScreen.class);
        startActivity(intent);

    }

    @Override
    public void failureLogOut(Exception exception) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(requireContext(), exception.getMessage(), Toast.LENGTH_LONG).show();

    }

    public void LogoutAlertDialog() {
        builder.setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);

                        logoutCurrentUser();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}