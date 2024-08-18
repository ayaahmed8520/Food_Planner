package Login.model;

public interface LoginListener {
    void MsgError(String message);
    void successLogin(String userId);
    void LoginError(String message);
}
