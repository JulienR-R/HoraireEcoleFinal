package com.example.horaire.user;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.horaire.R;
import com.example.horaire.database.AttributionPlageHoraire;
import com.example.horaire.database.HorairesDataBase;

import java.util.List;


public class UserHoraireViewActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private List<AttributionPlageHoraire> attributionPlageHoraires;
    private UserHoraire_Adapter adapter = new UserHoraire_Adapter();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horaireview_view);


        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        PopulateHoraire populateHoraire = new PopulateHoraire();
        populateHoraire.execute();
        setToolbar();
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Horaire confirmé");
    }


    private class PopulateHoraire extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(UserHoraireViewActivity.this);
            attributionPlageHoraires = horairesDataBase.attributionPlageHoraireAccess().getAttributionPlageHoraires();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            for (AttributionPlageHoraire att: attributionPlageHoraires) {
                att.infos(UserHoraireViewActivity.this);

            }
            adapter.submitList(attributionPlageHoraires);
            recyclerView.setAdapter(adapter);



        }

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
}