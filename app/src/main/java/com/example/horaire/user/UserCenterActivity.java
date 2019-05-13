package com.example.horaire.user;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.horaire.Login;
import com.example.horaire.R;


/**
 * Created by 201663676 on 2019-04-23.
 */

public class UserCenterActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnUserHoraireView, btnUserHoraireChoice, btnUserAdminContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_center);
        ButtonUserCenter();
        setToolbar();
    }

    public void ButtonUserCenter(){
        btnUserHoraireView = findViewById(R.id.user_horaireview);
        btnUserHoraireView.setOnClickListener(this);
        btnUserHoraireChoice = findViewById(R.id.user_horairechoice);
        btnUserHoraireChoice.setOnClickListener(this);
        btnUserAdminContact = findViewById(R.id.user_admincontact);
        btnUserAdminContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_horaireview:
                Intent intentHoraireView = new Intent(this, UserHoraireViewActivity.class);
                startActivity(intentHoraireView);
                break;

            case R.id.user_horairechoice:
                //vérifier que le choix d'horaire est activé
                Intent intentHoraireChoice = new Intent(this, UserHoraireChoiceActivity.class);
                startActivity(intentHoraireChoice);
                break;

            case R.id.user_admincontact:
               Intent intent = new Intent(Intent.ACTION_SEND);
               intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "manon_robert-rochon@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Problèmes/Informations");
                intent.putExtra(Intent.EXTRA_TEXT, "Écrivez votre message ici.");

                startActivity(Intent.createChooser(intent, "Send Email"));

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_logout:
                notifyLogout();
                return true;
            default:
                return false;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.title_user_center);
    }

    public void notifyLogout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_logout)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("stayConnected",false);
                        editor.apply();
                        Intent i = new Intent(UserCenterActivity.this, Login.class);
                        startActivity(i);
                    }
                }).create().show();
    }

}