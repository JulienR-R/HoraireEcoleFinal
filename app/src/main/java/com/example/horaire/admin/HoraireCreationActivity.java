package com.example.horaire.admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;


import com.example.horaire.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by 201663676 on 2019-04-23.
 */

public class HoraireCreationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

private Toolbar toolbar;
    private EditText editDescription;
    private Button editHeureDebut;
    private Button editHeureFin;
    private Button editDate;
    private Spinner editEffectif;
    private EditText dateChoisie;
    private EditText heureDebut;
    private EditText heureFin;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horaire_creation);
        editDescription = findViewById(R.id.create_description);
        editDate = findViewById(R.id.create_date);
        editEffectif = findViewById(R.id.create_effectif);
        heureDebut = findViewById(R.id.editHeureDebut);
        dateChoisie = findViewById(R.id.editDate);
        heureFin = findViewById(R.id.editHeureFin);
        editHeureDebut = findViewById( R.id.create_heure_debut);
        editHeureFin = findViewById( R.id.create_heure_fin);
        btnSave = findViewById( R.id.btnSave);
        editDate.setOnClickListener(RecupererDate);
        btnSave.setOnClickListener(Sauvegarder);
        editHeureFin.setOnClickListener(RecupererHeureFin);
        editHeureDebut.setOnClickListener(RecupererHeureDebut);
        setToolbar();

    }




    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Créer horaire");
    }

    public void onBackPressed() {
        //save TEMP data
        finish();
    }

    View.OnClickListener RecupererDate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        }
    };

    View.OnClickListener RecupererHeureDebut = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "heureDebut ");


        }



    };

    View.OnClickListener RecupererHeureFin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "heureFin");
        }



    };

    View.OnClickListener Sauvegarder = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }



    };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateChoisie.setText(DateFormat.getDateInstance().format(calendar.getTime()));
        Date date = calendar.getTime();


    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {



        if(view.getTag() == "heureDebut"){
            heureDebut.setText( String.valueOf(hourOfDay + ":" + minuteOfDay ));
        }else{
            heureFin.setText( String.valueOf(hourOfDay + ":" + minuteOfDay ));
        }

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
                .setMessage(R.string.message_horairecreation_final)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // ajouter à la base de donnée PERMANENTE, envoie aux employés messages pour faire leurs choix et retourner au menu principal
                        

                        
                        
                        finish();
                    }
                }).create().show();
    }



}