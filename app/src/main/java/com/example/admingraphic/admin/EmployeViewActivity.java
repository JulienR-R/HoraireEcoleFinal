package com.example.admingraphic.admin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.admingraphic.R;
import com.example.admingraphic.user.HoraireViewDetailActivity;

public class EmployeViewActivity extends AppCompatActivity {
    Toolbar toolbar;
    public void onCreate(Bundle savedInstanceState) {
        ListView list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employeview_view);
        list = findViewById(R.id.list_plages);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.admin_employeview_item, R.id.itemEmployeName, values);

    list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String itemClicked = (String) parent.getItemAtPosition(position);
            Intent intentEmployeDetail = new Intent(EmployeViewActivity.this, EmployeDetailActivity.class);
            //put extra to get the horaire
            startActivity(intentEmployeDetail);
        }
    });
    setToolbar();
}

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Liste des employés");
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