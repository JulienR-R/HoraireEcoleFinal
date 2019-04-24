package com.example.admingraphic.user;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admingraphic.R;
import com.example.admingraphic.admin.EmployeDetailActivity;

public class UserHoraireViewActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horaireview_view);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.user_horaireview_item, R.id.itemPlageTitle, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intentHoraireDetail = new Intent(this, HoraireViewDetailActivity.class);
        //put extra to get the horaire
        startActivity(intentHoraireDetail);
    }
}