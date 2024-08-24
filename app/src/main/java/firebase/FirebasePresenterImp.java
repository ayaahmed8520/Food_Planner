package firebase;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import SignUp.model.UserSignUpInfo;
import SignUp.view.SignUpListener;
import logout.LogOutResult;
import SignUp.view.SignUpResult;
import logout.ProfileFragmentIn;

public class FirebasePresenterImp implements FirebasePresenter, SignUpResult , LogOutResult {
    SignUpListener signUpListener;
    FirebaseRepo firebaseRepo;
    ProfileFragmentIn profileFragmentIn;

    public FirebasePresenterImp(SignUpListener signUpListener, FirebaseRepo firebaseRepo) {
        this.signUpListener = signUpListener;
        this.firebaseRepo = firebaseRepo;
    }
    public FirebasePresenterImp(ProfileFragmentIn profileFragmentIn, FirebaseRepo firebaseRepo) {
        this.profileFragmentIn = profileFragmentIn;
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
        signUpListener.registerSuccess();
    }

    @Override
    public void registerFailure(@NonNull Task<AuthResult> task) {
        signUpListener.registerFailure(task);


    }

    @Override
    public void logOutSuccess() {
        profileFragmentIn.successLogOut();

    }

    @Override
    public void logOutFailure(Exception exception) {
        profileFragmentIn.failureLogOut(exception);

    }
}
