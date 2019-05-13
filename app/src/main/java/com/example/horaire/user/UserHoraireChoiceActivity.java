package com.example.horaire.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.horaire.R;
import com.example.horaire.database.ChoixPlageHoraire;
import com.example.horaire.database.HorairesDataBase;
import com.example.horaire.database.PlageHoraire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UserHoraireChoiceActivity extends AppCompatActivity  {
    Toolbar toolbar;
    private  ArrayList<UserHoraireChoice_Item> arrayList = new ArrayList<>();
    private List<PlageHoraire> plageHoraireList;
    private UserHoraireChoice_Adapter adapter = new UserHoraireChoice_Adapter();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private List<UserHoraireChoice_Item> listChoix;


    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horairechoice_view);
        listChoix = new ArrayList<UserHoraireChoice_Item>();

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.saveChoice);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        PopulateHoraireChoices populateHoraireChoices = new PopulateHoraireChoices();
        populateHoraireChoices.execute();
        floatingActionButton.setOnClickListener(SauvegarderChangements);

        setToolbar();

        adapter.setOnItemClickListener(new UserHoraireChoice_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserHoraireChoice_Item item) {
                changeImage(item);

                if(listChoix.contains(item)) {
                    listChoix.remove(item);
                }else{
                    listChoix.add(item);
                }


            }


        });
    }

    View.OnClickListener SauvegarderChangements = new View.OnClickListener() {
    @Override
    public void onClick(View view) {


            new AlertDialog.Builder(view.getContext())
                    .setTitle("Sauvegarder")
                    .setMessage("Êtes-vous sûr de vos choix?")
                    .setNegativeButton(R.string.btnCancel, null)
                    .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            if(!listChoix.isEmpty()){
                            ConfirmChoices confirmChoices = new ConfirmChoices();
                            confirmChoices.execute();
                                finish();
                            }else{
                                String temp = getString(R.string.message_error);
                                Toast.makeText(UserHoraireChoiceActivity.this, temp, Toast.LENGTH_SHORT).show();
                            }



                        }
                    }).create().show();




    }
};

    private class PopulateHoraireChoices extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(UserHoraireChoiceActivity.this);
            plageHoraireList = horairesDataBase.plageHoraireAccess().getPlageHoraires();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


            for (int i = 0; i < plageHoraireList.size(); i++) {
                arrayList.add(new UserHoraireChoice_Item( R.drawable.czklow, plageHoraireList.get(i).getDescription(), df.format(plageHoraireList.get(i).getDate()), String.valueOf(plageHoraireList.get(i).getHeureDebut()), String.valueOf(plageHoraireList.get(i).getHeureFin())));

            }
            adapter.submitList(arrayList);
            recyclerView.setAdapter(adapter);



        }

    }


    private class ConfirmChoices extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(UserHoraireChoiceActivity.this);

            SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            long id = preferences.getLong("id", 0);

            for(int i = 0; i < listChoix.size(); i++){
            horairesDataBase.choixPlageHoraireAccess().insertChoixPlageHoraire(new ChoixPlageHoraire(id , listChoix.get(i).getmPlageHoraireId()));
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


            for (int i = 0; i < plageHoraireList.size(); i++) {
                arrayList.add(new UserHoraireChoice_Item(R.drawable.czklow, plageHoraireList.get(i).getDescription(), df.format(plageHoraireList.get(i).getDate()), String.valueOf(plageHoraireList.get(i).getHeureDebut()), String.valueOf(plageHoraireList.get(i).getHeureFin())));

            }
            adapter.submitList(arrayList);
            recyclerView.setAdapter(adapter);



        }

    }

    private void changeImage(UserHoraireChoice_Item item) {

        if (item.getmImageResource() == R.drawable.czklow) {
            item.setmImageResource(R.drawable.czkhigh);
        } else {
            item.setmImageResource(R.drawable.czklow);
        }



        adapter.submitList(arrayList);
        recyclerView.setAdapter(adapter);
    }


    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_user_horairechoice);
    }

    public void onBackPressed() {
        //save TEMP data
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