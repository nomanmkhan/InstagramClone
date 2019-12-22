package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsernameLogin,edtPasswordLogin;
    private Button btnSignupLoginActivity,btnLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        edtUsernameLogin = findViewById(R.id.edtUsenameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        edtPasswordLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });

        btnSignupLoginActivity = findViewById(R.id.btnSignupLoginActivity);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);

        btnSignupLoginActivity.setOnClickListener(this);
        btnLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null)
            ParseUser.getCurrentUser().logOut();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLoginActivity:

                if (edtUsernameLogin.getText().toString().equals("") || edtPasswordLogin.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this,"Username, Password are required!",FancyToast.LENGTH_LONG
                    ,FancyToast.INFO,true).show();
                }else {

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Logging up  "+ edtUsernameLogin.getText().toString());
                    progressDialog.show();

                    ParseUser.logInInBackground(edtUsernameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null){
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged in successfully!",
                                        FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                                transitionToSocialMediaActivity();
                            }else FancyToast.makeText(LoginActivity.this, e.getMessage(),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                            progressDialog.dismiss();
                        }
                    });
                }



                break;

            case R.id.btnSignupLoginActivity:

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);

                break;
        }

    }
    public void loginRootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();

        }


    }
    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this,SocialMedia.class);
        startActivity(intent);

    }
}
