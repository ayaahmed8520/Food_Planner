package firebase;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import SignUp.model.UserSignUpInfo;
import SignUp.view.SignUpListener;
import network.LogOutResult;
import network.SignUpResult;
import profile.ProfileFragment;

public class FirebasePresenterImp implements FirebasePresenter, SignUpResult , LogOutResult {
    SignUpListener signUpListenerInterface;
    FirebaseRepo firebaseRepositoryInterface;
    ProfileFragment profileFragmentInterface;

    public FirebasePresenterImp(SignUpListener signUpListenerInterface, FirebaseRepo firebaseRepositoryInterface) {
        this.signUpListenerInterface = signUpListenerInterface;
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
        signUpListenerInterface.registerSuccess();
    }

    @Override
    public void registerFailure(@NonNull Task<AuthResult> task) {
        signUpListenerInterface.registerFailure(task);


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
