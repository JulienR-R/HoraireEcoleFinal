package com.example.admingraphic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 201663676 on 2019-04-23.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Button btnConnect = (Button) findViewById(R.id.login_connection);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // list de vérification
                // 1. username et password vide ou pas
                // 2. le username existe dans la base de donnée
                // 3. le password est le bon pour le username
                // si oui, se connecter dans le menu approprié (admin ou user)
                //setContentView(R.layout.admin_page);
            }
        });
    }
}
