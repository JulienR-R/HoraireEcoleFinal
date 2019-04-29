package com.example.admingraphic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.database.Cursor;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admingraphic.admin.AdminCenterActivity;
import com.example.admingraphic.user.UserCenterActivity;

import java.util.UUID;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    @BindView(R.id.login_switch) ToggleButton _switch;
    private SharedPreferences sharedPreferences;
    private ActionBar ab;



    private Boolean ToogleButtonState;
  //  private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
     //   ab = getSupportActionBar();
     //   ab.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
     //   db = new DatabaseHelper(this);

        _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ToogleButtonState = isChecked;
            }
        });




        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!ToogleButtonState) {
                    onLoginSuccess();

                } else {
                    loginAdmin();
                }
            } });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);


                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void loginAdmin() {

        if (!validate()) {
            onLoginFailed(1);
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
    }

/*
        Cursor rs = db.getInformationAdmin(email, password);
        if(rs.moveToFirst()){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);

            editor.commit();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            //

                            Intent ajoutIntent = new Intent(Login.this, Services.class);

                            startActivity(ajoutIntent);
                            finish();
                            progressDialog.dismiss();
                        }
                    }, 3000);


        }else {
            onLoginFailed(2);
        }
    }

*/



    public void login() {


        if (!validate()) {
            onLoginFailed(1);
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
/*
        Cursor rs = db.getInformationContactUnique(email, password);

        if(rs.moveToFirst()){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.commit();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            //

                            Intent ajoutIntent = new Intent(Login.this, Services.class);

                            startActivity(ajoutIntent);
                            finish();
                            progressDialog.dismiss();
                        }
                    }, 3000);


        } else {
            onLoginFailed(2);
        }
        */
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, UserCenterActivity.class);
        startActivity(intent);


    }

    public void onLoginFailed(int code) {

        if(code == 1){

            Toast.makeText(getBaseContext(), "Vous devez entrer les informations correctement", Toast.LENGTH_LONG).show();

        }else {


            Toast.makeText(getBaseContext(), "L'adresse courriel n'existe pas dans notre base de donnees", Toast.LENGTH_LONG).show();
        }
        _loginButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
