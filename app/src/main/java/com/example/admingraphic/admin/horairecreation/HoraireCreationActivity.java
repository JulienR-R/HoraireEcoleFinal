package com.example.admingraphic.admin.horairecreation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.admingraphic.R;

/**
 * Created by 201663676 on 2019-04-23.
 */

public class HoraireCreationActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnFinalizeHoraire, btnAddHoraire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horairecreation_list_view);
        list = findViewById(R.id.list_creationplages);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.admin_horairecreation_list_item, R.id.itemPlageTitle_creation, values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                Intent intentHoraireDetail = new Intent(HoraireCreationActivity.this, HoraireCreationDetailActivity.class);
                //put extra the horaire to get detail
                startActivity(intentHoraireDetail);
            }
        });

        ButtonAdminHoraireCreation();
        setToolbar();
    }

    public void ButtonAdminHoraireCreation(){
        btnAddHoraire = findViewById(R.id.btnAdd);
        btnAddHoraire.setOnClickListener(this);
        btnFinalizeHoraire = findViewById(R.id.btnFinalize);
        btnFinalizeHoraire.setOnClickListener(this);
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
                Intent intentAddHoraire = new Intent(HoraireCreationActivity.this, HoraireCreationAddActivity.class);
                startActivity(intentAddHoraire);
                break;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Liste des plages de la prochaine semaine");
    }

    public void onBackPressed() {
        //save TEMP data
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
