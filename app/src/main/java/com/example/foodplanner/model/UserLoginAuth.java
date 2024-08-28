package com.example.foodplanner.model;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.foodplanner.view.login.LoginListener;

public class UserLoginAuth {

    private FirebaseAuth firebaseAuth;

    public UserLoginAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void loginUser(String email, String password, final LoginListener listener) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String clientID = firebaseAuth.getUid();
                            listener.userLoginSuccess(clientID);
                        } else {
                            listener.userLoginFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    private boolean isAValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isAValidPassword(String password) {
        return password.length() >= 6;
    }

    public boolean validateCredentials(String email, String password, final LoginListener listener) {
        if (!isAValidEmail(email)) {
            listener.validationError("Invalid email address");
            return false;
        } else if (!isAValidPassword(password)) {
            listener.validationError("Password should be at least 6 characters");
            return false;
        }
        return true;
    }
}