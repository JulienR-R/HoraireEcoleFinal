package com.example.horaire.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.horaire.R;

public class EmployeCreationActivity extends AppCompatActivity {

    private EditText createDescription;
    private EditText createNom;
    private EditText createEmail;
    private EditText createPassword;
    private Spinner createSenority;
    private Spinner createAdmin;
    private Button btnSave;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employe_create_user);
        createDescription = findViewById(R.id.create_prenom);
        createNom = findViewById(R.id.create_nom);
        createEmail = findViewById(R.id.create_email);
        createPassword = findViewById(R.id.create_password);
        createSenority = findViewById(R.id.create_senority);
        createAdmin = findViewById(R.id.create_admin);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(Sauvegarder);

        setToolbar();
    }

    View.OnClickListener Sauvegarder = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Créer usager");
    }

    public void onBackPressed() {
        //save TEMP data
        finish();
    }

}


