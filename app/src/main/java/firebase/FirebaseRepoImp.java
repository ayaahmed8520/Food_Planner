package firebase;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import SignUp.model.UserSignUpInfo;
import network.LogOutResult;
import network.SignUpResult;


public class FirebaseRepoImp implements FirebaseRepo {
    private Context context;
    private FirebaseAuth mAuth;
    private  SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SH_NAME="foodPlanner_preferences";

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    private static FirebaseRepoImp repo = null;

    public static FirebaseRepoImp getInstance(Context _context) {
        if (repo == null) {
            repo = new FirebaseRepoImp(_context);
        }

        return repo;
    }

    private FirebaseRepoImp(Context _context) {
        context=_context;
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = context.getSharedPreferences(SH_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }


    @Override
    public void userRegistration(UserSignUpInfo userSignUpInfo, SignUpResult signUpResult) {
        mAuth.createUserWithEmailAndPassword(userSignUpInfo.getUserEmail(), userSignUpInfo.getUserPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {

                                String clientID = user.getUid();
                                editor.putString("clientID", clientID);
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
            mAuth.signOut();
            editor.putString("userID", null);
            editor.commit();
            logOutResult.logOutSuccess();
        }catch (Exception exception){
            logOutResult.logOutFailure(exception);
        }
    }
}
