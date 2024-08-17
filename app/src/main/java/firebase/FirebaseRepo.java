package firebase;


import SignUp.model.UserSignUpInfo;
import network.LogOutResult;
import network.SignUpResult;

public interface FirebaseRepo {
    public void userRegistration(UserSignUpInfo userSignUpInfo , SignUpResult signUpResult);
    public void logoutTheCurrentUser(LogOutResult logOutResult);



}
