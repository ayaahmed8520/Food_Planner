package Login.presenter;


import Login.model.LoginListener;
import Login.model.LoginModel;

public class LoginPresenterImp implements LoginPresenter, LoginListener {

    private LoginModel mModel;
    private LoginListener mListener;

    public LoginPresenterImp(LoginListener listener) {
        mModel = new LoginModel();
        mListener = listener;
    }

    @Override
    public void userLogin(String email, String password) {
        mModel.loginUser(email, password, mListener);
    }

    public boolean validateCredentials(String email, String password) {
        return mModel.validateCredentials(email, password, mListener);
    }

    @Override
    public void onValidationError(String message) {

    }

    @Override
    public void onLoginSuccess(String userId) {
        mListener.onLoginSuccess(userId);
    }

    @Override
    public void onLoginError(String errorMessage) {
        mListener.onLoginError(errorMessage);
    }
}
