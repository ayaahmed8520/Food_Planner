package Login.presenter;


import Login.model.LoginListener;
import Login.model.LoginModel;

public class LoginPresenterImp implements LoginPresenter, LoginListener {

    private LoginModel loginModel;
    private LoginListener loginListener;

    public LoginPresenterImp(LoginListener listener) {
        loginModel = new LoginModel();
        loginListener = listener;
    }

    @Override
    public void userLogin(String userEmail, String userPassword) {
        loginModel.loginUser(userEmail, userPassword, loginListener);
    }

    public boolean validateCredentials(String email, String password) {
        return loginModel.validateCredentials(email, password, loginListener);
    }

    @Override
    public void MsgError(String message) {

    }

    @Override
    public void successLogin(String userId) {
        loginListener.successLogin(userId);
    }

    @Override
    public void LoginError(String errorMessage) {
        loginListener.LoginError(errorMessage);
    }
}
