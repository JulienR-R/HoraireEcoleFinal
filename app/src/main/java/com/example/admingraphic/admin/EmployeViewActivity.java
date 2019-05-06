package com.example.admingraphic.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admingraphic.R;
import com.example.admingraphic.database.HorairesDataBase;
import com.example.admingraphic.database.User;

import java.util.ArrayList;
import java.util.List;

public class EmployeViewActivity extends AppCompatActivity {
    Toolbar toolbar;
    List<User> usersList;
    ListView list;
    ArrayAdapter<String> adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employeview_view);
        list = findViewById(R.id.list_employes);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(EmployeViewActivity.this);
                usersList = horairesDataBase.userAccess().getUsers();
                List<String> listUsers = new ArrayList<String>();
                for (User user:usersList) {
                    listUsers.add(user.getUserId().split("@")[0]);
                }
                adapter = new ArrayAdapter<String>(EmployeViewActivity.this,
                        android.R.layout.simple_list_item_1,android.R.id.text1,listUsers);

                list.setAdapter(adapter);
            }
        }).start();

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_admin_employe);
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