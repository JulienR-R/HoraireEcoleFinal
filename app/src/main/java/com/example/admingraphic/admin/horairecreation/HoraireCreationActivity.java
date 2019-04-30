package com.example.admingraphic.admin.horairecreation;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.admingraphic.R;

import java.util.List;

/**
 * Created by 201663676 on 2019-04-23.
 */

public class HoraireCreationActivity extends ListActivity implements View.OnClickListener{
    Toolbar toolbar;
    List<String> listPlageTitle;
    Button btnFinalizeHoraire, btnAddHoraire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horaireview_view);
        ButtonAdminHoraireCreation();
        //get the listPlageTitle
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.admin_horairecreation_listview, R.id.recyclerview_plages, listPlageTitle);
        setListAdapter(adapter);
        setToolbar();
    }

    public void ButtonAdminHoraireCreation(){
        btnAddHoraire = findViewById(R.id.btnAdd);
        btnAddHoraire.setOnClickListener(this);
        btnFinalizeHoraire = findViewById(R.id.btnFinalize);
        btnFinalizeHoraire.setOnClickListener(this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intentDetailHoraire = new Intent(this, HoraireCreationDetailActivity.class);
        //put extra the horaire to get detail
        startActivity(intentDetailHoraire);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinalize:
                //vérifie minimum d'une plage-horaire créée
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié de créer une plage-horaire!)
                break;

            case R.id.btnAdd:
                Intent intentAddHoraire = new Intent(this, HoraireCreationAddActivity.class);
                startActivity(intentAddHoraire);
                break;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setTitle("Liste des plages créées pour la prochaine semaine");
    }

    public void onBackPressed() {
        //save TEMP data
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation?")
                .setMessage("Êtes-vous sure de vouloir finaliser l'horaire de la semaine? Vous ne pourrez plus le modifier.")
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // ajouter à la base de donnée PERMANENTE, envoie aux employés messages pour faire leurs choix et retourner au menu principal
                        finish();
                    }
                }).create().show();
    }
}
