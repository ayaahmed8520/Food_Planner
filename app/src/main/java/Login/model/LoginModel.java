package Login.model;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel {
    private FirebaseAuth mAuth;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
    }


    public void loginUser(String userEmail, String userPassword, final LoginListener listener) {
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getUid();
                            listener.successLogin(userId);
                        } else {
                            listener.LoginError(task.getException().getMessage());
                        }
                    }

                });
    }

    private boolean isAValidEmail(String userEmail) {
        return Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
    }

    private boolean isAValidPassword(String userPassword) {
        return userPassword.length() >= 6;
    }

    public boolean validateCredentials(String email, String password, final LoginListener listener) {
        if (!isAValidEmail(email)) {
            listener.MsgError("Invalid email address");
            return false;
        } else if (!isAValidPassword(password)) {
            listener.MsgError("Password should be at least 6 characters");
            return false;
        }
        return true;
    }
}