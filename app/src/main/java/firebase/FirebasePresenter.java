package firebase;



public interface FirebasePresenter {
    public void registerUser(String userName,String userEmail,String userPassword,String confirmPassword);
    public void logoutCurrentUser();
}
