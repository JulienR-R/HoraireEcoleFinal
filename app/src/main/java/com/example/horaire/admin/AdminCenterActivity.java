package com.example.horaire.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.horaire.LoginActivity;
import com.example.horaire.R;


/**
 * Created by 201663676 on 2019-04-23.
 */

public class AdminCenterActivity  extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btnAdminHoraireView, btnAdminEmployeView;
    private BottomNavigationView bottomNavigationView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_center);
        ButtonAdminCenter();
        setToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filtre = new IntentFilter(AutoScheduledUsersToShifts.ACTION);
        receiverFromService = new ReceiverFromService();
        registerReceiver(receiverFromService,filtre);    
    }
    
    public void ButtonAdminCenter(){

        btnAdminHoraireView = findViewById(R.id.admin_horaireview);
        btnAdminHoraireView.setOnClickListener(this);
        btnAdminEmployeView = findViewById(R.id.admin_employeview);
        btnAdminEmployeView.setOnClickListener(this);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomOptions);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.admin_horaireview:
                 intent = new Intent(AdminCenterActivity.this, AdminHoraireViewActivity.class);
                startActivity(intent);
                break;



            case R.id.admin_employeview:
                 intent = new Intent(AdminCenterActivity.this, AdminEmployeViewActivity.class);
                startActivity(intent);
                break;



            default:
                break;
        }
    }

    BottomNavigationView.OnNavigationItemSelectedListener BottomOptions = new  BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {




            switch (menuItem.getItemId()) {

                case R.id.nav_creation_horaire:

                        intent = new Intent(AdminCenterActivity.this, AdminHoraireCreationActivity.class);
                        startActivity(intent);

                    break;

                case R.id.nav_creation_usager:

                    intent = new Intent(AdminCenterActivity.this, AdminEmployeCreationActivity.class);
                    startActivity(intent);


                    break;

                case R.id.nav_script:
                    if(menuItem.getTitle().toString().equals("Lancer attributions")){
                    IntentFilter filtre = new IntentFilter(AutoScheduledUsersToShifts.ACTION);
                    receiverFromService = new ReceiverFromService();
                    registerReceiver(receiverFromService,filtre);
                    Intent activerAttributions = new Intent(getBaseContext(), AutoScheduledUsersToShifts.class);
                    pi = PendingIntent.getService(getApplicationContext(), 0,
                            activerAttributions, PendingIntent.FLAG_CANCEL_CURRENT);
                    am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long startTime = SystemClock.elapsedRealtime();
                    am.setInexactRepeating(AlarmManager.RTC_WAKEUP, startTime,AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15, pi);
                    menuItem.setTitle("Arrêter attributions");
                }
                else{
                    unregisterReceiver(receiverFromService);
                    am.cancel(pi);
                    stopService(new Intent(getBaseContext(),AutoScheduledUsersToShifts.class));
                    menuItem.setTitle("Lancer attributions");
                }
                    break;
            }



            return true;
        }};




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_logout:
                notifyLogout();
                return true;
            default:
                return false;
        }
    }

    public void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.title_admin_center);
    }

    public void notifyLogout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_logout)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("stayConnected",false);
                        editor.apply();
                      Intent i = new Intent(AdminCenterActivity.this, LoginActivity.class);
                      startActivity(i);
                    }
                }).create().show();
    }





}
