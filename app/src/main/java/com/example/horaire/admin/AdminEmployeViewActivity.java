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
import com.example.horaire.database.User;

import java.util.List;


public class AdminEmployeViewActivity extends AppCompatActivity implements AdminEmploye_EditDelete_Frag.OnFragmentInteractionListener {

    private RecyclerView recyclerView;
    private List<User> usersList;
    private AdminEmploye_Adapter adapter = new AdminEmploye_Adapter();
    Toolbar toolbar;


    public void onCreate(Bundle savedInstanceState) {
    //    ListView list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_emp_view);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PopulateListView populateListView = new PopulateListView();
        populateListView.execute();








      /*
       setContentView(R.layout.admin_employeview_view);

        list = findViewById(R.id.list_employes);
        String[] values = new String[] { "George", "Mickael", "Robert",
                "Jean", "Elizabeth", "Thérèse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.admin_employeview_item, R.id.itemEmployeName, values);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = (String) parent.getItemAtPosition(position);
                Intent intentEmployeDetail = new Intent(AdminEmployeViewActivity.this, EmployeDetailActivity.class);
                //put extra to get the horaire
                startActivity(intentEmployeDetail);
            }
        });

        */
        setToolbar();


        adapter.setOnItemClickListener(new AdminEmploye_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                //Implementez logique
            }

            @Override
            public void onCreateClick(User user) {
                openFragment(user);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                DeleteUserAsync deleteUserAsync = new DeleteUserAsync(viewHolder.getAdapterPosition());
                deleteUserAsync.execute();
            }
        }).attachToRecyclerView(recyclerView);


    }



    private class DeleteUserAsync extends AsyncTask<Void, Void, Void>{

        private int mPosition;

       public DeleteUserAsync(int position) {

           mPosition = position;
       }

       @Override
       protected Void doInBackground(Void... voids) {
           HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(AdminEmployeViewActivity.this);
           horairesDataBase.userAccess().deleteUser(adapter.getClientAt(mPosition));
           usersList = horairesDataBase.userAccess().getUsers();
           return null;
       }


       @Override
       protected void onPostExecute(Void result) {
           adapter.submitList(usersList);
           recyclerView.setAdapter(adapter);

       }
   }



    private void openFragment(User user) {
        AdminEmploye_EditDelete_Frag fragment_editDelete_emp =  AdminEmploye_EditDelete_Frag.newInstance(user);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container, fragment_editDelete_emp, "FRAGMENT_ADD_EMP").commit();
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

                adapter.submitList(usersList);
                recyclerView.setAdapter(adapter);


            }
        }




    private class PopulateListView extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(AdminEmployeViewActivity.this);
            usersList = horairesDataBase.userAccess().getUsers();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            adapter.submitList(usersList);
            recyclerView.setAdapter(adapter);

        }
    }


    public void setToolbar(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_admin_employe);
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