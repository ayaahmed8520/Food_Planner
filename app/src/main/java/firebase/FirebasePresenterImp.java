package firebase;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import SignUp.model.UserSignUpInfo;
import SignUp.view.SignUpView;
import network.LogOutResult;
import network.SignUpResult;
import profile.ProfileFragment;

public class FirebasePresenterImp implements FirebasePresenter, SignUpResult , LogOutResult {
    SignUpView signUpViewInterface;
    FirebaseRepo firebaseRepositoryInterface;
    ProfileFragment profileFragmentInterface;

    public FirebasePresenterImp(SignUpView signUpViewInterface, FirebaseRepo firebaseRepositoryInterface) {
        this.signUpViewInterface= signUpViewInterface;
        this.firebaseRepositoryInterface = firebaseRepositoryInterface;
    }
    public FirebasePresenterImp(ProfileFragment profileFragmentInterface, FirebaseRepo firebaseRepositoryInterface) {
        this.profileFragmentInterface= profileFragmentInterface;
        this.firebaseRepositoryInterface = firebaseRepositoryInterface;
    }



    @Override
    public void registerUser(String disPlayName,String email,String password,String confirmPassword) {
        firebaseRepositoryInterface.userRegistration(new UserSignUpInfo(disPlayName,email,password,confirmPassword) , this);

    }

    @Override
    public void logoutCurrentUser() {
        firebaseRepositoryInterface.logoutTheCurrentUser(this);
    }

    @Override
    public void registerSuccess() {
        signUpViewInterface.registerViewSuccess();
    }

    @Override
    public void registerFailure(@NonNull Task<AuthResult> task) {
        signUpViewInterface.registerViewFailure(task);


    }

    @Override
    public void logOutSuccess() {
        profileFragmentInterface.logOutSuccess();

    }

    @Override
    public void logOutFailure(Exception exception) {
        profileFragmentInterface.logOutFailure(exception);

    }
}
