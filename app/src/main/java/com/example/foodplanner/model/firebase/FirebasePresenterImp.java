package com.example.foodplanner.model.firebase;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.example.foodplanner.model.UserSignUpInfo;
import com.example.foodplanner.view.signUp.SignUpListener;
import com.example.foodplanner.view.logout.LogOutResult;
import com.example.foodplanner.view.signUp.SignUpResult;
import com.example.foodplanner.view.logout.ProfileFragmentIn;

public class FirebasePresenterImp implements FirebasePresenter, SignUpResult , LogOutResult {
    SignUpListener signUpListener;
    InFirebase inFirebase;
    ProfileFragmentIn profileFragmentIn;

    public FirebasePresenterImp(SignUpListener signUpListener, InFirebase inFirebase) {
        this.signUpListener = signUpListener;
        this.inFirebase = inFirebase;
    }
    public FirebasePresenterImp(ProfileFragmentIn profileFragmentIn, InFirebase inFirebase) {
        this.profileFragmentIn = profileFragmentIn;
        this.inFirebase = inFirebase;
    }



    @Override
    public void registerUser(String disPlayName,String email,String password,String confirmPassword) {
        inFirebase.userRegistration(new UserSignUpInfo(disPlayName,email,password,confirmPassword) , this);

    }

    @Override
    public void logoutCurrentUser() {
        inFirebase.logoutTheCurrentUser(this);
    }

    @Override
    public void registerSuccess() {
        signUpListener.registerSuccess();
    }

    @Override
    public void registerFailure(@NonNull Task<AuthResult> task) {
        signUpListener.registerFailure(task);


    }

    @Override
    public void logOutSuccess() {
        profileFragmentIn.successLogOut();

    }

    @Override
    public void logOutFailure(Exception exception) {
        profileFragmentIn.failureLogOut(exception);

    }
}
