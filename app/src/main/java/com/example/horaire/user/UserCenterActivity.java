package com.example.horaire.user;


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

import com.example.horaire.LoginActivity;
import com.example.horaire.R;
import com.example.horaire.admin.AdminCenterActivity;


/**
 * Created by 201663676 on 2019-04-23.
 */

public class UserCenterActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnUserHoraireView, btnUserHoraireChoice, btnUserAdminContact;
    Intent intent;

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
                intent = new Intent(this, UserHoraireViewActivity.class);
                startActivity(intent);
                break;

            case R.id.user_horairechoice:
                //vérifier que le choix d'horaire est activé
                intent = new Intent(this, UserHoraireChoiceActivity.class);
                startActivity(intent);
                break;

            case R.id.user_admincontact:
                intent = new Intent(Intent.ACTION_SEND);
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
            /*case R.id.action_edit_password:
                editPassword();
                return true;*/
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
                        intent = new Intent(UserCenterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).create().show();
    }

    /*public void editPassword(){
        final EditText newpassword = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_edit_password)
                .setMessage(R.string.message_edit_password)
                .setNegativeButton(R.string.btnCancel, null)
                .setView(newpassword)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {*/
                        // si password n'est pas le même qu'actuel et qu'il n'est pas vide
                        // changer password dans la base de donnée
                        // sinon, toast expliquant l'erreur (Tu as oublié de mettre un mot de passe)
                    /*}
                }).create().show();
    }*/
}