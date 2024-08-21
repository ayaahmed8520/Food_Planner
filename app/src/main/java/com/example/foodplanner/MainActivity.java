package com.example.foodplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.foodplanner.databinding.ActivityMainBinding;
import Home.Home;
import SignUp.view.SignUp;
import favorite.view.Favorite;
import firebase.FirebaseRepoImp;
import profile.ProfileFragment;
import profile.ProfileFragmentImp;
import search.model.Search;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        String clientID= FirebaseRepoImp.getInstance(this).getSharedPreferences().getString("clientID",null);
        builder = new AlertDialog.Builder(this);


        replaceFragment(new Home());

        activityMainBinding.bottomNavigationBar.setOnItemSelectedListener(item -> {

            if(item.getItemId() ==  R.id.nav_home){
                replaceFragment(new Home());
            }
            else if(item.getItemId() ==  R.id.nav_search){
                replaceFragment(new Search());
            }
            else if(item.getItemId() ==  R.id.nav_favorite){
                if(clientID!=null){
                    replaceFragment(new Favorite());
                }else {
                    signupToGetMoreFeature();
                }
            }
            else if(item.getItemId() ==  R.id.nav_profile){
                if(clientID!=null){
                    replaceFragment(new ProfileFragmentImp());
                }else {
                    signupToGetMoreFeature();
                }
            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void signupToGetMoreFeature() {
        builder.setTitle("Sign up for more features!")
                .setMessage("Save your favorite dishes \n and plan your meals")
                .setCancelable(true)
                .setPositiveButton("Sign up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, SignUp.class);
                        startActivity(intent);
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