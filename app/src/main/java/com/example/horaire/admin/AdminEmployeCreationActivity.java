package com.example.horaire.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.horaire.R;

public class AdminEmployeCreationActivity extends AppCompatActivity {

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
	if(     !createDescription.getText().toString().isEmpty() &&
                    !createNom.getText().toString().isEmpty()  &&
                    !createEmail.getText().toString().isEmpty() &&
                    !createPassword.getText().toString().isEmpty() &&
                    createSenority.getSelectedItem().toString() != null &&
                    createAdmin.getSelectedItem().toString() != null){
                notifyConfirm();
            }
            else{
                String temp = getString(R.string.message_error);
                Toast.makeText(AdminEmployeCreationActivity.this, temp, Toast.LENGTH_SHORT).show();
            }
        }
    };




    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Créer usager");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return false;
        }




}}


