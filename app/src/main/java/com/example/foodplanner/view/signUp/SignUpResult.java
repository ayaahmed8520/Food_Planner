package com.example.foodplanner.view.signUp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface SignUpResult {
    public void registerSuccess();
    public void registerFailure(@NonNull Task<AuthResult> task);
}
