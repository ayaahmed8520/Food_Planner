package firebase;


import SignUp.model.UserSignUpInfo;
import profile.LogOutResult;
import SignUp.view.SignUpResult;

public interface FirebaseRepo {
    public void userRegistration(UserSignUpInfo userSignUpInfo , SignUpResult signUpResult);
    public void logoutTheCurrentUser(LogOutResult logOutResult);

}
