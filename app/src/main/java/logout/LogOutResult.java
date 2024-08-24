package logout;

public interface LogOutResult {
    public void logOutSuccess();
    public void logOutFailure(Exception exception);
}
