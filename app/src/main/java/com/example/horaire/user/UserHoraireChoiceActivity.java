package com.example.horaire.user;

import android.content.DialogInterface;
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

import com.example.horaire.R;
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


    public void onCreate(Bundle savedInstanceState) {
      //  ListView list;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_horairechoice_view);


        recyclerView = findViewById(R.id.recyclerView);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        PopulateHoraireChoices populateHoraireChoices = new PopulateHoraireChoices();
        populateHoraireChoices.execute();

        /*
        setContentView(R.layout.user_horairechoice_view);
        list = findViewById(R.id.list_plageschoice);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.user_horairechoice_item, R.id.infos, values){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row =  super.getView(position, convertView, parent);

                View checkbox = row.findViewById(R.id.checkBox);
                checkbox.setTag(checkbox);
                checkbox.setOnClickListener(UserHoraireChoiceActivity.this);
                return row;
            }
        };
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
*/
        final FloatingActionButton btnConfirm = findViewById(R.id.saveChoice);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // vérifie minimum une colonne est cochée
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié de cocher!)
            }
        });


        /*final Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        */
        setToolbar();

        adapter.setOnItemClickListener(new UserHoraireChoice_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserHoraireChoice_Item item) {
                changeImage(item);
            }


        });
    }



    private class PopulateHoraireChoices extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(UserHoraireChoiceActivity.this);
            plageHoraireList = horairesDataBase.plageHoraireAccess().getPlageHoraires();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");


            for (int i = 0; i < plageHoraireList.size(); i++) {
                arrayList.add(new UserHoraireChoice_Item(R.drawable.czkNo, plageHoraireList.get(i).getDescription(), df.format(plageHoraireList.get(i).getDate()), String.valueOf(plageHoraireList.get(i).getHeureDebut()), String.valueOf(plageHoraireList.get(i).getHeureFin())));

            }
            adapter.submitList(arrayList);
            recyclerView.setAdapter(adapter);



        }

    }

    private void changeImage(UserHoraireChoice_Item item) {

        if (item.getmImageResource() == R.drawable.czkNo) {
            item.setmImageResource(R.drawable.czkYes);
        } else {
            item.setmImageResource(R.drawable.czkNo);
        }

        adapter.submitList(arrayList);
        recyclerView.setAdapter(adapter);
    }

    /*

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.checkBox:
                    Toast.makeText(this, "Checkbox "+v.getTag(), Toast.LENGTH_SHORT).show();
                    // changer le nombre d'heure choisi (R.id.timeInfo.setText(nbrHeure))
                    ImageView checkbox= (ImageView) v;
                    if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkNo).getConstantState())) {
                        checkbox.setImageResource(R.drawable.czkmedium);
                    } else if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkmedium).getConstantState())) {
                        checkbox.setImageResource(R.drawable.czkYes);
                    } else if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkYes).getConstantState())) {
                        checkbox.setImageResource(R.drawable.czkNo);
                    }
                    break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Toast.makeText(this, "Item Click "+position, Toast.LENGTH_SHORT).show();
            Intent intentHoraireDetail = new Intent(UserHoraireChoiceActivity.this, UserHoraireViewDetailActivity.class);
            //put extra to get the horairedetail with item
            startActivity(intentHoraireDetail);
        }


    */
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



    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_horairechoice_final)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // envoyer à la base de donnée PERMANENTE




                        finish();
                    }
                }).create().show();
    }
}