package com.example.foodplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import Login.view.Login;
import SignUp.view.SignUp;


public class LoginAndSignUpScreen extends AppCompatActivity {

    private Button btnLogin, btnSignup, btnSkip;
    private SignInButton btnGoogleSign;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private SharedPreferences.Editor editor;
    private  SharedPreferences sharedPreferences;
    String googleClientId = "868989801427-d91ml5v8ih7d0nuo0hgar45tpsr2ht2l.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_up_screen);

        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        btnSkip =  findViewById(R.id.btn_Skip);
        btnGoogleSign = findViewById(R.id.btn_googleSignIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndSignUpScreen.this, Login.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndSignUpScreen.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndSignUpScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signInWithGoogle();

    }
















    private void signInWithGoogle() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(googleClientId)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(LoginAndSignUpScreen.this, googleSignInOptions);

        btnGoogleSign.setOnClickListener(view -> {
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, 100);
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            // When user already sign in redirect to Leading activity
            startActivity(new Intent(LoginAndSignUpScreen.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Google sign in successful", Toast.LENGTH_SHORT).show();

                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    assert user != null;
                                    String clientID = user.getUid();
                                    sharedPreferences = getSharedPreferences("foodPlanner_preferences", MODE_PRIVATE);
                                    editor = sharedPreferences.edit();
                                    editor.putString("clientID", clientID);
                                    editor.apply();


                                    startActivity(new Intent(LoginAndSignUpScreen.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                } else {
                                    Toast.makeText(LoginAndSignUpScreen.this, "Firebase authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Sign-In Failed: " + e.getStatusCode(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}