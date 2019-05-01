package com.example.admingraphic.user;

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

public class UserHoraireViewActivity extends AppCompatActivity {
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        ListView list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horaireview_view);
        list = findViewById(R.id.list_plages);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.user_horaireview_item, R.id.itemPlageTitle, values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = (String) parent.getItemAtPosition(position);
                Intent intentHoraireDetail = new Intent(UserHoraireViewActivity.this, HoraireViewDetailActivity.class);
                //put extra to get the horaire
                startActivity(intentHoraireDetail);
            }
        });
        setToolbar();
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_user_horaire);
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