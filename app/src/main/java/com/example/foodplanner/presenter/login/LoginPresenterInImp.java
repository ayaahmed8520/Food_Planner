package com.example.foodplanner.presenter.login;


import com.example.foodplanner.view.login.LoginListener;
import com.example.foodplanner.model.UserLoginAuth;

public class LoginPresenterInImp implements LoginPresenterIn, LoginListener {

    private UserLoginAuth userLoginAuth;
    private LoginListener loginListener;

    public LoginPresenterInImp(LoginListener listener) {
        userLoginAuth = new UserLoginAuth();
        loginListener = listener;
    }

    @Override
    public void userLogin(String email, String password) {
        userLoginAuth.loginUser(email, password, loginListener);
    }

    public boolean validateCredentials(String email, String password) {
        return userLoginAuth.validateCredentials(email, password, loginListener);
    }

    @Override
    public void validationError(String message) {

    }

    @Override
    public void userLoginSuccess(String clientID) {
        loginListener.userLoginSuccess(clientID);
    }

    @Override
    public void userLoginFailure(String errorMessage) {
        loginListener.userLoginFailure(errorMessage);
    }
}
