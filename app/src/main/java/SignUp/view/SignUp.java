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
import android.widget.Button;
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


public class SignUp extends AppCompatActivity implements SignUpView {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-zA-Z])" +      //any letter
                    ".{8,}" +               //at least 8 characters
                    "$");
    private TextInputLayout et_Username, et_userEmail, et_userPassword,et_ConfirmPassword;
    private ProgressBar progressBar;
    private FirebasePresenter firebasePresenter;
    private TextView backToLogin;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_Username = findViewById(R.id.til_signup_person_name);
        et_userEmail = findViewById(R.id.til_signup_email);
        et_userPassword = findViewById(R.id.til_signup_password);
        et_ConfirmPassword = findViewById(R.id.til_signup_confirm_password);
        backToLogin =findViewById(R.id.tv_signup_login);
        progressBar= findViewById(R.id.progress_bar);
        btnSignUp = findViewById(R.id.btn_signup_signup);


        //create presenter obj
        firebasePresenter = new FirebasePresenterImp(this, FirebaseRepoImp.getInstance(getApplicationContext()));

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //textWatcher to observe user input... is it empty?
        et_Username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_Username.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    et_Username.setError("Field can't be empty");
                }
            }
        });
        et_userEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_userEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    et_userEmail.setError("Field can't be empty");

                }

            }
        });
        et_userPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_userPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    et_userPassword.setError("Field can't be empty");

                }

            }
        });
        et_ConfirmPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_ConfirmPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    et_ConfirmPassword.setError("Field can't be empty");

                }

            }
        });


    }


    private boolean userNameValidation() {
        String userName = et_Username.getEditText().getText().toString();

        if (userName.isEmpty()) {
            et_Username.setError("Field can't be empty");
            return false;
        } else if (userName.length() > 15) {
            et_Username.setError("Username too long");
            return false;
        } else {
            et_Username.setError(null);
            return true;
        }
    }

    private boolean emailValidation() {
        String userEmail = et_userEmail.getEditText().getText().toString().trim();

        if (userEmail.isEmpty()) {
            et_userEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            et_userEmail.setError("Please enter a valid email address");
            return false;
        } else {
            et_userEmail.setError(null);
            return true;
        }
    }


    private boolean passwordValidation() {
        String userPassword = et_userPassword.getEditText().getText().toString();

        if (userPassword.isEmpty()) {
            et_userPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(userPassword).matches()) {
            et_userPassword.setError("Password should be at least 8 characters including(digits and letters) in English");
            return false;
        } else {
            et_userPassword.setError(null);
            return true;
        }
    }

    private boolean confirmPasswordValidate() {
        String confirmPasswordInput = et_ConfirmPassword.getEditText().getText().toString();

        if (confirmPasswordInput.isEmpty()) {
            et_ConfirmPassword.setError("Field can't be empty");
            return false;
        } else if (!et_userPassword.getEditText().getText().toString().equals(confirmPasswordInput)) {
            et_ConfirmPassword.setError("Confirm password does not match password!");
            return false;
        } else {
            et_ConfirmPassword.setError(null);
            return true;
        }
    }

    // SIGNUP ON CLICK
    public void confirmInput(View v) {
        if (emailValidation() & userNameValidation() & passwordValidation() & confirmPasswordValidate()) {
            progressBar.setVisibility(View.VISIBLE);

            Log.i("TAGTAGTAG", "confirmInput: " + et_Username.getEditText().getText().toString());

            userRegister(et_Username.getEditText().getText().toString(),
                    et_userEmail.getEditText().getText().toString(),
                    et_userPassword.getEditText().getText().toString(),
                    et_ConfirmPassword.getEditText().getText().toString()
            );

        }
    }


    @Override
    public void userRegister(String disPlayName, String email, String password, String confirmPassword) {
        firebasePresenter.registerUser(disPlayName, email, password, confirmPassword);

    }

    @Override
    public void registerViewSuccess() {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void registrationViewFailure(@NonNull Task<AuthResult> task) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, (task.getException()).getMessage(), Toast.LENGTH_LONG).show();
        Log.i("TAGonFailureRegistration", "onFailureRegistration: "+task.getException().getMessage());

    }
}

