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
    SignUpView signUpView;
    FirebaseRepo firebaseRepo;
    ProfileFragment profileFragment;

    public FirebasePresenterImp(SignUpView signUpView, FirebaseRepo firebaseRepo) {
        this.signUpView = signUpView;
        this.firebaseRepo = firebaseRepo;
    }
    public FirebasePresenterImp(ProfileFragment profileFragment, FirebaseRepo firebaseRepo) {
        this.profileFragment = profileFragment;
        this.firebaseRepo = firebaseRepo;
    }



    @Override
    public void registerUser(String disPlayName,String email,String password,String confirmPassword) {
        firebaseRepo.userRegistration(new UserSignUpInfo(disPlayName,email,password,confirmPassword) , this);

    }

    @Override
    public void logoutCurrentUser() {
        firebaseRepo.logoutTheCurrentUser(this);
    }

    @Override
    public void registerSuccess() {
        signUpView.registerViewSuccess();
    }

    @Override
    public void registerFailure(@NonNull Task<AuthResult> task) {
        signUpView.registerViewFailure( task);


    }

    @Override
    public void logOutSuccess() {
        profileFragment.logOutSuccess();

    }

    @Override
    public void logOutFailure(Exception exception) {
        profileFragment.logOutFailure(exception);

    }
}
