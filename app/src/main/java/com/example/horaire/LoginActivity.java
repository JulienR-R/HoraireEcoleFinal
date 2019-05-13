package com.example.horaire;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.horaire.admin.AdminCenterActivity;
import com.example.horaire.database.HorairesDataBase;
import com.example.horaire.database.PopulateDatabase;
import com.example.horaire.database.User;
import com.example.horaire.user.UserCenterActivity;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout relativeLayout1, relativeLayout2;
    private TextView textView;
    private EditText email;
    private EditText pwd;
    private Button loginButton;
    private User currentUser;
    private Switch aSwitch;
    private SharedPreferences sharedPreferences;
    private static final int MESSAGE_POST_EXECUTE = 3;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
        relativeLayout1.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    };

    @Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);





        goToMainActivity();


        new Thread(new Runnable() {
            @Override
            public void run() {
                PopulateDatabase populateDatabase = new PopulateDatabase(LoginActivity.this);
                HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(LoginActivity.this);
                User user = horairesDataBase.userAccess().getUser(1);
                user.setIsAdmin(true);
                horairesDataBase.userAccess().updateUser(user);
            }

        }).start();

        loadPage();
    }

    public void loadPage(){
        setContentView(R.layout.login);
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.title_login);
        handler.postDelayed(runnable,4000 );




        relativeLayout1 = findViewById(R.id.rellay1);
        relativeLayout2 = findViewById(R.id.rellay2);
        textView =  findViewById(R.id.titre);
        email = findViewById(R.id.input_email);
        pwd = findViewById(R.id.input_password);
        aSwitch = findViewById(R.id.laSwitch);


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


        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isAdmin", currentUser.getIsAdmin());

        editor.putBoolean("stayConnected",aSwitch.isChecked());
        editor.putLong("id",currentUser.get_id() );
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
