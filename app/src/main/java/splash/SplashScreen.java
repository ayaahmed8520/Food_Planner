package splash;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.LoginAndSignUpScreen;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.SignUp;

import firebase.FirebaseRepoImp;

public class SplashScreen extends AppCompatActivity{
    private static final int SPLASH_SCREEN_TIME = 3000;
    private LottieAnimationView lottieAnimationView;// 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen); // Your splash screen layout
        String userID= FirebaseRepoImp.getInstance(this).getSharedPreferences().getString("userID",null);
        lottieAnimationView = findViewById(R.id.gif_splash);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userID!=null){
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SplashScreen.this, LoginAndSignUpScreen.class);
                    startActivity(intent);
                }
            }
        }, SPLASH_SCREEN_TIME);


    }
}