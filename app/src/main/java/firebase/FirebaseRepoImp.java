package firebase;


import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import SignUp.model.UserSignUpInfo;
import profile.LogOutResult;
import SignUp.view.SignUpResult;


public class FirebaseRepoImp implements FirebaseRepo {

    private Context context;
    private FirebaseAuth firebaseAuth;
    private  SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String PREFERENCES_NAME ="foodPlanner_preferences";

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @SuppressLint("StaticFieldLeak")
    private static FirebaseRepoImp repo = null;

    public static FirebaseRepoImp getInstance(Context _context) {
        if (repo == null) {
            repo = new FirebaseRepoImp(_context);
        }

        return repo;
    }

    private FirebaseRepoImp(Context _context) {

        context=_context;
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }


    @Override
    public void userRegistration(UserSignUpInfo signupUser , SignUpResult signUpResult) {

        firebaseAuth.createUserWithEmailAndPassword(signupUser.getUserEmail(), signupUser.getUserPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {

                                String userID = user.getUid();
                                editor.putString("clientID", userID);
                                editor.commit();
                                signUpResult.registerSuccess();
                            }
                        } else {
                            signUpResult.registerFailure(task);


                        }
                    }
                });

    }

    @Override
    public void logoutTheCurrentUser(LogOutResult logOutResult) {
        try {
            firebaseAuth.signOut();
            editor.putString("clientID", null);
            editor.commit();
            logOutResult.logOutSuccess();
        }catch (Exception exception){
            logOutResult.logOutFailure(exception);
        }


    }


}
