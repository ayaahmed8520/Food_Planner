package splash;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.LoginAndSignUpScreen;
import com.example.foodplanner.R;
import com.example.foodplanner.SignUp;

public class SplashScreen extends AppCompatActivity{
    private static final int SPLASH_SCREEN_TIME = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen); // Your splash screen layout
        LottieAnimationView animationView = findViewById(R.id.gif_splash);
        animationView.setFailureListener(result -> {
            Log.e("Lottie", "Error loading animation", result);
        });

        // Handler to start the StartActivity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginAndSignUpScreen.class);
                startActivity(intent);
                finish(); // Close the SplashScreen activity so it won't be backable
            }
        }, SPLASH_SCREEN_TIME);
    }
}