package Login.model;

public interface LoginListener {
    void onValidationError(String message);
    void onLoginSuccess(String userId);
    void onLoginError(String message);
}
