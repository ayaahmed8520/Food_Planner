package Login.model;

public interface LoginListener {
    void validationMsgError(String message);
    void successLogin(String userId);
    void LoginError(String message);
}
