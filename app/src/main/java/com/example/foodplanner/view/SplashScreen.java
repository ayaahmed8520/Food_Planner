package com.example.foodplanner.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

import com.example.foodplanner.model.firebase.InFirebaseImp;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LottieAnimationView animationView = findViewById(R.id.lottieAnimation);
        TextView appName = findViewById(R.id.tv_AppName);

        int SPLASH_TIME = 4000;

        String clientID = InFirebaseImp.getInstance(this)
                .getSharedPreferences()
                .getString("clientID", null);



        appName.animate().translationY(-1800).setDuration(1000).setStartDelay(4000);
        animationView.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);

        handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> {
            Intent intent;
            if (clientID != null) {
                intent = new Intent(SplashScreen.this, MainActivity.class);
            } else {
                intent = new Intent(this, LoginAndSignUpScreen.class);
            }

            startActivity(intent);

            overridePendingTransition(0, 0);

        }, SPLASH_TIME);
    }


}
