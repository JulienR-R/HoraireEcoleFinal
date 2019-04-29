package com.example.admingraphic.admin.horairecreation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.admingraphic.R;

/**
 * Created by 201663676 on 2019-04-23.
 */

public class HoraireCreationAddActivity extends AppCompatActivity  implements View.OnClickListener{
    Toolbar toolbar;
    Button btnCancel, btnConfirm_horairecreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horairecreation_addview);
        ButtonAdminHoraireAdd();
        setToolbar();
    }

    public void ButtonAdminHoraireAdd(){
        btnConfirm_horairecreation = findViewById(R.id.btnConfirm_horairecreation);
        btnConfirm_horairecreation.setOnClickListener(this);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                onBackPressed();
                break;

            case R.id.btnConfirm_horairecreation:
                // vérifie si les text sont remplis
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié d'écrire!)
                break;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Création de plage-horaire");
    }

    public void onBackPressed() {
        notifyCancel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return false;
        }
    }

    public void notifyCancel() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation?")
                .setMessage("Êtes-vous sure de vouloir annuler l'ajout de cette plage? Votre progression sera perdu.")
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();
    }

    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation?")
                .setMessage("Êtes-vous sure de vouloir ajouter cette plage à l'horaire?")
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // ajouter à la base de donnée TEMP
                        finish();
                    }
                }).create().show();
    }
}
