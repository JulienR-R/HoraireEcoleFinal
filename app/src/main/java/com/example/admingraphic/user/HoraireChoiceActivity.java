package com.example.admingraphic.user;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admingraphic.R;
import com.example.admingraphic.admin.EmployeDetailActivity;

public class HoraireChoiceActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horairechoice_view);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.user_horairechoice_item, R.id.infos, values);
        setListAdapter(adapter);

        final Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // vérifie minimum une colonne est cochée
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié de cocher!)
            }
        });
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if(v.getId() == R.id.checkBox){
            View.OnClickListener ocl = new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // changer le nombre d'heure choisi (R.id.timeInfo.setText(nbrHeure))
                    ImageView checkbox= findViewById(R.id.checkBox);
                    if (checkbox.getDrawable().equals(R.drawable.czklow)) {
                        checkbox.setImageResource(R.drawable.czkmedium);
                    } else if (checkbox.getDrawable().equals(R.drawable.czkmedium)) {
                        checkbox.setImageResource(R.drawable.czkhigh);
                    } else if (checkbox.getDrawable().equals(R.drawable.czkhigh)) {
                        checkbox.setImageResource(R.drawable.czklow);
                    }
                }
            };
        } else {
            String item = (String) getListAdapter().getItem(position);
            Intent intentEmployeDetail = new Intent(this, EmployeDetailActivity.class);
            //put extra to get the employe with item
            startActivity(intentEmployeDetail);
        }
    }

    public void onBackPressed() {
        //save TEMP data
        finish();
    }

    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation?")
                .setMessage("Êtes-vous sure de vouloir finaliser le choix d'horaire de la semaine? Vous ne pourrez plus le modifier.")
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // envoyer à la base de donnée PERMANENTE et retourner au menu principal
                        finish();
                    }
                }).create().show();
    }
}
