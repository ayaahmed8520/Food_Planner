package view;

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

import com.example.foodplanner.LoginAndSignUpScreen;
import com.example.foodplanner.R;

import firebase.FirebasePresenterImp;
import firebase.FirebasePresenter;
import firebase.FirebaseRepoImp;

public class ProfileFragmentImp extends Fragment implements ProfileFragment {
    AlertDialog.Builder builder;
    FirebasePresenter firebasePresenter;
    private ProgressBar progressBar;

    Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        firebasePresenter = new FirebasePresenterImp(this, FirebaseRepoImp.getInstance(requireContext()));

        builder = new AlertDialog.Builder(requireContext());
        btnLogout=view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLogoutDialog();
            }
        });
    }

    @Override
    public void deleteUser() {
        firebasePresenter.logoutCurrentUser();
    }

    @Override
    public void logOutSuccess() {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(requireContext(), LoginAndSignUpScreen.class);
        startActivity(intent);

    }

    @Override
    public void logOutFailure(Exception exception) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(requireContext(), exception.getMessage(), Toast.LENGTH_LONG).show();

    }

    public void createLogoutDialog() {
        builder.setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);

                        deleteUser();
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