package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;

public class LoginAndSignUpScreen extends AppCompatActivity {
    private Button btnSignUp, btnLogin, btnSkip;
    private SignInButton btnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_up_screen); // Your start screen layout

        // Initialize buttons
        btnSignUp = findViewById(R.id.btn_start_signup);
        btnLogin = findViewById(R.id.btn_start_login);
        btnSkip = findViewById(R.id.btn_start_skip);
        btnGoogleSignIn = findViewById(R.id.btn_start_google);

        // Set onClick listeners
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignUpActivity
                Intent signUpIntent = new Intent(LoginAndSignUpScreen.this, SignUp.class);
                startActivity(signUpIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                Intent loginIntent = new Intent(LoginAndSignUpScreen.this, Login.class);
                startActivity(loginIntent);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Skip login/sign up and go to main activity
                Intent mainIntent = new Intent(LoginAndSignUpScreen.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Google sign-in
                // Implement Google sign-in here
            }
        });
    }
}