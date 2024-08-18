package Login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;

import Login.model.LoginListener;
import Login.presenter.LoginPresenterImp;
import Login.presenter.LoginPresenter;

public class Login extends AppCompatActivity implements LoginListener {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private ProgressBar mProgressBar;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenterImp(this);

        mEmailEditText = findViewById(R.id.email_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mLoginButton = findViewById(R.id.login_button);
        mProgressBar = findViewById(R.id.progress_bar);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if (validateCredentials(email, password)) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPresenter.userLogin(email, password);
                }
            }
        });
    }

    @Override
    public void MsgError(String message) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLogin(String userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("foodPlanner_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("clientID", userId);
        editor.apply();

        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginError(String message) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private boolean validateCredentials(String email, String password) {
        boolean flag = true;

        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError("Password field can't be empty");
            mPasswordEditText.requestFocus();
            flag = false;
        } else if (password.length() < 8) {
            mPasswordEditText.setError("Password should be at least 8 characters including (digits and letters)");
            mPasswordEditText.requestFocus();
            flag = false;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailEditText.setError("Email field can't be empty");
            mEmailEditText.requestFocus();
            flag = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditText.setError("Enter a valid email address: example@example.com");
            mEmailEditText.requestFocus();
            flag = false;
        }

        return flag;
    }
}