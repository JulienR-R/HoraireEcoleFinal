package com.example.admingraphic.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.admingraphic.R;
import com.example.admingraphic.database.HorairesDataBase;
import com.example.admingraphic.database.PlageHoraire;
import com.example.admingraphic.database.User;
import com.example.admingraphic.expendablelistview.CustomExpandableListAdapter;
import com.example.admingraphic.expendablelistview.ExpandableListDataPump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 201663676 on 2019-04-23.
 */

public class AdminHoraireViewActivity extends AppCompatActivity{
    Toolbar toolbar;
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setToolbar();
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
