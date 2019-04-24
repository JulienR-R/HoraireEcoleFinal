package com.example.admingraphic.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.admingraphic.R;
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
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> listPlageTitle;
    HashMap<String, List<String>> listPlageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_horaireview_view);
        ExpendableViewHoraireView();
        setToolbar();
    }

    public void ExpendableViewHoraireView(){
        expandableListView = findViewById(R.id.expandablePlage);
        listPlageDetail = ExpandableListDataPump.getData();
        listPlageTitle = new ArrayList<>(listPlageDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, listPlageTitle, listPlageDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intentEmployeDetail = new Intent(getBaseContext(), EmployeDetailActivity.class);
                //put extra to get the employe
                startActivity(intentEmployeDetail);
                return false;
            }
        });
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Horaire de la semaine");
    }

    public void onBackPressed() {
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
}
