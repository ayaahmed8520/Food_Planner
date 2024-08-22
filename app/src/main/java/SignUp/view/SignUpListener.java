package SignUp.view;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface SignUpListener {

    public void userRegister(String userName,String userEmail,String userPassword,String confirmPassword);
    public void registerSuccess();
    public void registerFailure(@NonNull Task<AuthResult> task);
}
