package com.example.foodplanner.view.logout;

public interface ProfileFragmentIn {
    public void logoutCurrentUser();
    public void successLogOut();
    public void failureLogOut(Exception exception);
}
