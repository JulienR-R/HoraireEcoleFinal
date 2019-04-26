package com.example.admingraphic.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admingraphic.R;
import com.example.admingraphic.admin.AdminHoraireViewActivity;

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
                Intent intentHoraireChoice = new Intent(this, HoraireChoiceActivity.class);
                startActivity(intentHoraireChoice);
            break;

            case R.id.user_admincontact:
            // passer à une application externe de courriel
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
            case R.id.action_edit_password:
                editPassword();
                break;
            case R.id.action_logout:
                notifyLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle("Menu Principal");
    }

    public void notifyLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation?")
                .setMessage("Êtes-vous sure de vouloir vous déconnecter?")
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // connection key down and move to loginpage
                    }
                }).create().show();
    }

    public void editPassword(){
        final EditText newpassword = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Changement de mot de passe")
                .setMessage("Écrire un nouveau mot de passe pour remplacer l'ancien")
            .setNegativeButton(R.string.btnCancel, null)
            .setView(newpassword)
            .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // si password n'est pas le même qu'actuel et qu'il n'est pas vide
                    // changer password dans la base de donnée
                    // sinon, toast expliquant l'erreur (Tu as oublié de mettre un mot de passe)
                }
            }).create().show();
    }
}