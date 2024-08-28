package com.example.foodplanner.view;

import static androidx.appcompat.app.AlertDialog.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.foodplanner.view.home.Home;
import com.example.foodplanner.view.signUp.SignUp;
import com.example.foodplanner.model.network.checkconnection.ConnectivityReceiver;
import com.example.foodplanner.view.fav.Favorite;
import com.example.foodplanner.model.firebase.InFirebaseImp;
import com.example.foodplanner.view.logout.ProfileFragmentImp;
import com.example.foodplanner.model.Search;
import com.example.foodplanner.view.weekPlan.WeekPlanFragment;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiverListener {

    private ActivityMainBinding activityMainBinding;
    private Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        String clientID= InFirebaseImp.getInstance(this).getSharedPreferences().getString("clientID",null);
        builder = new Builder(this);


        ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(connectivityReceiver,filter, getApplicationContext().RECEIVER_NOT_EXPORTED);
        }

        replaceFragment(new Home());


        activityMainBinding.bottomNavigationBar.setOnItemSelectedListener(item -> {
            if(item.getItemId() ==  R.id.nav_home){

                replaceFragment(new Home());

            } else if(item.getItemId() ==  R.id.nav_search){

                replaceFragment(new Search());

            } else if(item.getItemId() ==  R.id.nav_favorite){

                if(clientID!=null){
                    replaceFragment(new Favorite());
                } else {
                    signupToGetMoreFeature();
                }

            } else if(item.getItemId() ==  R.id.nav_profile){

                if(clientID!=null){
                    replaceFragment(new ProfileFragmentImp());
                } else {
                    signupToGetMoreFeature();
                }

            } else if(item.getItemId() ==  R.id.nav_week){

                if(clientID!=null){
                    replaceFragment(new WeekPlanFragment());
                } else {
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



    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        BottomNavigationView bottomNavigationView = activityMainBinding.bottomNavigationBar;

        if (isConnected) {
            // Show all menu items
            bottomNavigationView.getMenu().findItem(R.id.nav_home).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.nav_search).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.nav_profile).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.nav_week).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.nav_favorite).setVisible(true);
            Toast.makeText(this, "Connected to the Internet", Toast.LENGTH_SHORT).show();


        } else {
            // Show only favorite fragment menu item
            bottomNavigationView.getMenu().findItem(R.id.nav_home).setVisible(false);
            bottomNavigationView.getMenu().findItem(R.id.nav_search).setVisible(false);
            bottomNavigationView.getMenu().findItem(R.id.nav_profile).setVisible(false);
            bottomNavigationView.getMenu().findItem(R.id.nav_week).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.nav_favorite).setVisible(true);

           // replaceFragment(new Favorite()); // Switch to Favorite fragment when offline

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }
}
