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

public class HoraireCreationEditActivity extends AppCompatActivity  implements View.OnClickListener{
    Toolbar toolbar;
    Button btnCancel, btnConfirm_edithoraire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horairecreation_editview);
        ButtonAdminHoraireEdit();
        setToolbar();
    }

    public void ButtonAdminHoraireEdit(){
        btnConfirm_edithoraire = findViewById(R.id.btnConfirm_edithoraire);
        btnConfirm_edithoraire.setOnClickListener(this);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                onBackPressed();
                break;

            case R.id.btnConfirm_edithoraire:
                // vérifie si les text sont remplis
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié d'écrire!)
                break;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_admin_horairecreation_edit);
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
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_horairecreation_edit_cancel)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();
    }

    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_horairecreation_edit_confirm)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // modifier à la base de donnée TEMP
                        finish();
                    }
                }).create().show();
    }
}
