package com.example.foodplanner.model.firebase;


import com.example.foodplanner.model.UserSignUpInfo;
import com.example.foodplanner.view.logout.LogOutResult;
import com.example.foodplanner.view.signUp.SignUpResult;

public interface InFirebase {
    public void userRegistration(UserSignUpInfo userSignUpInfo , SignUpResult signUpResult);
    public void logoutTheCurrentUser(LogOutResult logOutResult);

}
