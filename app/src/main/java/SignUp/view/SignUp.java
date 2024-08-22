package SignUp.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import java.util.regex.Pattern;

import Login.view.Login;
import firebase.FirebasePresenterImp;
import firebase.FirebasePresenter;
import firebase.FirebaseRepoImp;


public class SignUp extends AppCompatActivity implements SignUpListener {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-zA-Z])" +      //any letter
                    ".{8,}" +               //at least 8 characters
                    "$");

    private TextInputLayout userName, userEmail, userPassword, userConfirmPassword;
    private ProgressBar progressBar;
    private FirebasePresenter firebasePresenter;
    private TextView backToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        userName = findViewById(R.id.textInput_userName);
        userEmail = findViewById(R.id.textInput_userEmail);
        userPassword = findViewById(R.id.textInput_userPassword);
        userConfirmPassword = findViewById(R.id.textInput_userConfirmPassword);
        backToLogin =findViewById(R.id.tv_login);
        progressBar= findViewById(R.id.progress_bar);


        firebasePresenter = new FirebasePresenterImp(this,FirebaseRepoImp.getInstance(getApplicationContext()));

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

    }




    private boolean validateUsername() {
        String usernameInput = userName.getEditText().getText().toString();

        if (usernameInput.isEmpty()) {
            userName.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            userName.setError("Username too long");
            return false;
        } else {
            userName.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = userEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            userEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            userEmail.setError("Please enter a valid email address");
            return false;
        } else {
            userEmail.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = userPassword.getEditText().getText().toString();

        if (passwordInput.isEmpty()) {
            userPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            userPassword.setError("Password should be at least 8 characters including(digits and letters) in English");
            return false;
        } else {
            userPassword.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String confirmPasswordInput = userConfirmPassword.getEditText().getText().toString();

        if (confirmPasswordInput.isEmpty()) {
            userConfirmPassword.setError("Field can't be empty");
            return false;
        } else if (!userPassword.getEditText().getText().toString().equals(confirmPasswordInput)) {
            userConfirmPassword.setError("Confirm password does not match password!");
            return false;
        } else {
            userConfirmPassword.setError(null);
            return true;
        }
    }

    // SIGNUP ON CLICK
    public void confirmInput(View v) {
        if (validateEmail() & validateUsername() & validatePassword() & validateConfirmPassword()) {
            progressBar.setVisibility(View.VISIBLE);

            Log.i("SignUpTAG", "confirmInput: " + userName.getEditText().getText().toString());

            userRegister(userName.getEditText().getText().toString(),
                    userEmail.getEditText().getText().toString(),
                    userPassword.getEditText().getText().toString(),
                    userConfirmPassword.getEditText().getText().toString()
            );

        }
    }


    @Override
    public void userRegister(String disPlayName, String email, String password, String confirmPassword) {
        firebasePresenter.registerUser(disPlayName, email, password, confirmPassword);

    }

    @Override
    public void registerSuccess() {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void registerFailure(@NonNull Task<AuthResult> task) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, (task.getException()).getMessage(), Toast.LENGTH_LONG).show();
        Log.i("TAGonFailureRegistration", "onFailureRegistration: "+task.getException().getMessage());

    }
}