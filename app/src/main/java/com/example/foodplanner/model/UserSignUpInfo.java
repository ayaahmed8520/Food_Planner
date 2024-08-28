package com.example.foodplanner.model;

public class UserSignUpInfo {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String confirmPassword;


    public UserSignUpInfo(String userName, String userEmail, String userPassword, String confirmPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
