package profile;

public interface ProfileFragment {
    public void logoutCurrentUser();
    public void logOutSuccess();
    public void logOutFailure(Exception exception);
}
