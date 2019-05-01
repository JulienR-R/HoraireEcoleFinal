package com.example.admingraphic.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import com.example.admingraphic.R;

public class HoraireChoiceActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        ListView list;
        super.onCreate(savedInstanceState);
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
                checkbox.setOnClickListener(HoraireChoiceActivity.this);
                return row;
            }
        };
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        final Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // vérifie minimum une colonne est cochée
                notifyConfirm();
                //sinon, toast expliquant l'erreur (Tu as oublié de cocher!)
            }
        });
        final Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setToolbar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkBox:
                Toast.makeText(this, "Checkbox "+v.getTag(), Toast.LENGTH_SHORT).show();
                // changer le nombre d'heure choisi (R.id.timeInfo.setText(nbrHeure))
                ImageView checkbox= (ImageView) v;
                if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czklow).getConstantState())) {
                    checkbox.setImageResource(R.drawable.czkmedium);
                } else if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkmedium).getConstantState())) {
                    checkbox.setImageResource(R.drawable.czkhigh);
                } else if (checkbox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.czkhigh).getConstantState())) {
                    checkbox.setImageResource(R.drawable.czklow);
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Toast.makeText(this, "Item Click "+position, Toast.LENGTH_SHORT).show();
        Intent intentHoraireDetail = new Intent(HoraireChoiceActivity.this, HoraireViewDetailActivity.class);
        //put extra to get the horairedetail with item
        startActivity(intentHoraireDetail);
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

    public void notifyConfirm() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_confirmation)
                .setMessage(R.string.message_horairechoice_final)
                .setNegativeButton(R.string.btnCancel, null)
                .setPositiveButton(R.string.btnConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // envoyer à la base de donnée PERMANENTE
                        Button mButton = findViewById(R.id.user_horairechoice);
                        mButton.setText(R.string.user_horairechoice_edit);
                        finish();
                    }
                }).create().show();
    }
}