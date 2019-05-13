package com.example.horaire.admin;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;


import com.example.horaire.R;
import com.example.horaire.database.HorairesDataBase;
import com.example.horaire.database.PlageHoraire;


import java.util.List;

/**
 * Created by 201663676 on 2019-04-23.
 */
public class AdminHoraireViewActivity extends AppCompatActivity implements AdminHoraire_EditDelete_Frag.OnFragmentInteractionListener{
   private Toolbar toolbar;


    private RecyclerView recyclerView;
    private List<PlageHoraire> plageHoraireList;
    private AdminHoraire_Adapter horairesAdapter = new AdminHoraire_Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.admin_horaire_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PopulateHoraireList populateHoraireList = new PopulateHoraireList();
        populateHoraireList.execute();



        setToolbar();

        horairesAdapter.setOnItemClickListener(new AdminHoraire_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(PlageHoraire plageHoraire) {
                //Implementez logique
            }

            @Override
            public void onCreateClick(PlageHoraire plageHoraire) {
                openFragment(plageHoraire);
            }


        });




        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                DeletePlageHoraireAsync deletePlageHoraireAsync = new DeletePlageHoraireAsync(viewHolder.getAdapterPosition());
                deletePlageHoraireAsync.execute();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onFragmentInteraction() {

        onBackPressed();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fragmentManager = getSupportFragmentManager();


        if (fragmentManager.getBackStackEntryCount() == 0) {
            this.finish();
        } else {

            fragmentManager.popBackStack();

            this.getSupportActionBar().setTitle("Liste d'horaires");
            horairesAdapter.submitList(plageHoraireList);
            recyclerView.setAdapter(horairesAdapter);


        }
    }

    private class DeletePlageHoraireAsync extends AsyncTask<Void, Void, Void>{

        private int mPosition;

        public DeletePlageHoraireAsync(int position) {

            mPosition = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(AdminHoraireViewActivity.this);
            horairesDataBase.plageHoraireAccess().deletePlageHoraire(horairesAdapter.getPlageAt(mPosition));
            plageHoraireList = horairesDataBase.plageHoraireAccess().getPlageHoraires();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            horairesAdapter.submitList(plageHoraireList);
            recyclerView.setAdapter(horairesAdapter);

        }
    }

    private void openFragment(PlageHoraire plageHoraire) {
        AdminHoraire_EditDelete_Frag admin_horaireEditDeleteFrag =  AdminHoraire_EditDelete_Frag.newInstance(plageHoraire);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_horaire_modify, admin_horaireEditDeleteFrag, "FRAGMENT_MODIFY_HORAIRE").commit();
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