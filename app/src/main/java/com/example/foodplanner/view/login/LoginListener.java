package com.example.foodplanner.view.login;

public interface LoginListener {
    void validationError(String message);
    void userLoginSuccess(String userId);
    void userLoginFailure(String message);
}
