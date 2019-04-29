package com.example.admingraphic.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.admingraphic.R;
import com.example.admingraphic.admin.EmployeDetailActivity;

public class HoraireChoiceActivity extends AppCompatActivity {
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        ListView list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horairechoice_view);
        list = findViewById(R.id.list_plageschoice);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.user_horairechoice_item, R.id.infos, values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(view.getId() == R.id.checkBox){
                    // changer le nombre d'heure choisi (R.id.timeInfo.setText(nbrHeure))
                    View.OnClickListener ocl = new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            ImageView checkbox= findViewById(R.id.checkBox);
                            if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czklow).getConstantState())) {
                                checkbox.setImageResource(R.drawable.czkmedium);
                            } else if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkmedium).getConstantState())) {
                                checkbox.setImageResource(R.drawable.czkhigh);
                            } else if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkhigh).getConstantState())) {
                                checkbox.setImageResource(R.drawable.czklow);
                            }
                        }
                    };
                } else {
                    String item = (String) parent.getAdapter().getItem(position);
                    Intent intentHoraireDetail = new Intent(HoraireChoiceActivity.this, HoraireViewDetailActivity.class);
                    //put extra to get the horairedetail with item
                    startActivity(intentHoraireDetail);
                }
            }
        });

        final Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // vérifie minimum une colonne est cochée
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié de cocher!)
            }
        });
        final Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setToolbar();
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Liste des choix de plage-horaire");
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
                .setMessage("Êtes-vous sure de vouloir finaliser le choix d'horaire de la semaine? Vous ne pourrez plus le modifier.")
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // envoyer à la base de donnée PERMANENTE et retourner au menu principal
                        finish(); // not backpress because no need to save TEMP data
                    }
                }).create().show();
    }
}