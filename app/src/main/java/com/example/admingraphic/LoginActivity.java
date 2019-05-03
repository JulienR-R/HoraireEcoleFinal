package com.example.admingraphic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admingraphic.admin.AdminCenterActivity;
import com.example.admingraphic.database.HorairesDataBase;
import com.example.admingraphic.database.PopulateDatabase;
import com.example.admingraphic.database.User;
import com.example.admingraphic.user.UserCenterActivity;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText pwd;
    Button loginButton;
    User currentUser;
    CheckBox checkBoxConnected;
    private SharedPreferences sharedPreferences;
    private static final int MESSAGE_POST_EXECUTE = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //si le user avait check stayConnected on saute le login
        goToMainActivity();

        //populate database
        new Thread(new Runnable() {
            @Override
            public void run() {
                PopulateDatabase populateDatabase = new PopulateDatabase(LoginActivity.this);
            }
        });

        setContentView(R.layout.login);
        email = findViewById(R.id.input_email);
        pwd = findViewById(R.id.input_password);
        checkBoxConnected = findViewById(R.id.stayConnected);
        loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length()!=0 && pwd.getText().length() !=0) {
                    final Handler loadHoraireHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            switch (msg.what) {
                                case MESSAGE_POST_EXECUTE:
                                    loadHoraireOnPostExecute();
                                    break;
                                default:
                                    break;
                            }
                        }
                    };
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(LoginActivity.this);
                            currentUser = horairesDataBase.userAccess().getUser(email.getText().toString(), pwd.getText().toString());
                            sendPostExecuteMessage();
                        }

                        private void sendPostExecuteMessage() {
                            Message postExecuteMsg = new Message();
                            postExecuteMsg.what = MESSAGE_POST_EXECUTE;
                            loadHoraireHandler.sendMessage(postExecuteMsg);
                        }
                    }).start();
                }
            } });

    }

    private void loadHoraireOnPostExecute(){
        if(currentUser == null){
            Toast.makeText(this, "Please Sign Up", Toast.LENGTH_SHORT).show();
            return;
        }

        //on store le statut du user s'il veut rester connecté
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isAdmin", currentUser.getIsAdmin());
        editor.putBoolean("stayConnected",checkBoxConnected.isChecked());
        editor.apply();

        Intent intent = currentUser.getIsAdmin()? new Intent(this, AdminCenterActivity.class):
                new Intent(this, UserCenterActivity.class) ;
        startActivity(intent);
        finish();
    }

    public void goToMainActivity(){
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        boolean stayConnected = sharedPreferences.getBoolean("stayConnected",false);
        if(stayConnected){
            boolean isAdmin = sharedPreferences.getBoolean("isAdmin",false);
            Intent goToPage = isAdmin? new Intent(this,AdminCenterActivity.class):
                    new Intent(this,UserCenterActivity.class);
            startActivity(goToPage);
            finish();
        }
    }
}
