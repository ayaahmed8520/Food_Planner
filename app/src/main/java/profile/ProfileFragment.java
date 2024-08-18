package profile;

public interface ProfileFragment {
    public void deleteUser();
    public void logOutSuccess();
    public void logOutFailure(Exception exception);
}
