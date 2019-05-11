package com.example.horaire.admin;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.horaire.R;
import com.example.horaire.admin.horairecreation.Horaire_Adapter;
import com.example.horaire.database.HorairesDataBase;
import com.example.horaire.database.PlageHoraire;
import com.example.horaire.database.User;


import java.util.ArrayList;

import java.util.List;

/**
 * Created by 201663676 on 2019-04-23.
 */
public class AdminHoraireViewActivity extends AppCompatActivity{
    Toolbar toolbar;
 //   ArrayAdapter<String> adapter;
   // ListView list;

    private RecyclerView recyclerView;
    private List<PlageHoraire> plageHoraireList;
    private Horaire_Adapter horairesAdapter = new Horaire_Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.horaire_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PopulateHoraireList populateHoraireList = new PopulateHoraireList();
        populateHoraireList.execute();


        /*
        setContentView(R.layout.admin_horaireview_view);
        list = findViewById(R.id.listPlage);






        new Thread(new Runnable() {
            @Override
            public void run() {
                HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(AdminHoraireViewActivity.this);
                List<PlageHoraire> plageHoraires = horairesDataBase.plageHoraireAccess().getPlageHoraires();
                List<String> listPlageTitle = new ArrayList<String>();
                for (PlageHoraire plageHoraire:plageHoraires) {
                    listPlageTitle.add(plageHoraire.toString());
                }
                adapter = new ArrayAdapter<String>(AdminHoraireViewActivity.this,
                        android.R.layout.simple_list_item_1,android.R.id.text1,listPlageTitle);

                list.setAdapter(adapter);
            }
        }).start();

        */
        setToolbar();
    }


    private class PopulateHoraireList extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(AdminHoraireViewActivity.this);
            plageHoraireList = horairesDataBase.plageHoraireAccess().getPlageHoraires();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            horairesAdapter.submitList(plageHoraireList);
            recyclerView.setAdapter(horairesAdapter);

        }
    }


    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_admin_horaire);
    }

    public void onBackPressed() {finish(); }

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