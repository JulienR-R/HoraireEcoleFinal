package com.example.admingraphic.admin.horairecreation;

import android.content.DialogInterface;
import android.content.Intent;
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

public class HoraireCreationDetailActivity extends AppCompatActivity  implements View.OnClickListener{
    Toolbar toolbar;
    Button btnEditHoraire, btnDeleteHoraire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horairecreation_detailview);
        ButtonAdminHoraireCreationDetail();
        setToolbar();
    }

    public void ButtonAdminHoraireCreationDetail(){
        btnEditHoraire = findViewById(R.id.btnEditHoraire);
        btnEditHoraire.setOnClickListener(this);
        btnDeleteHoraire = findViewById(R.id.btnDeleteHoraire);
        btnDeleteHoraire.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDeleteHoraire:
                notifyConfirm();
                break;

            case R.id.btnEditHoraire:
                Intent intentEditHoraire = new Intent(HoraireCreationDetailActivity.this, HoraireCreationEditActivity.class);
                //put extra the horaire to edit
                startActivity(intentEditHoraire);
                break;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_admin_horairecreation_detail);
    }

    public void onBackPressed() {
        finish();
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

    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_horaire_delete)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // enlever de la base de donnée TEMP
                        finish();
                    }
                }).create().show();
    }
}
