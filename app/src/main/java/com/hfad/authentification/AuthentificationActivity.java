package com.hfad.authentification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthentificationActivity extends Activity {

    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        final EditText login =(EditText) findViewById(R.id.user_email);
        final EditText pass = (EditText) findViewById(R.id.user_password);

        final Button loginButton = (Button) findViewById(R.id.connect);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String loginTxt = login.getText().toString();
                final String passTxt = pass.getText().toString();

                // Déclaration du patter pour vérifier si l'adresse mail est valide.

                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

                // Déclaration du matcher qui va comparer le pattern avec le string passé en paramètre.

                Matcher m = p.matcher(loginTxt);

                // Si l'adresse mail est non valide, on afficher un message d'erreur avec la classe
                //Toast.

                if (!m.matches())
                {
                    Toast.makeText(AuthentificationActivity.this,R.string.email_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si un des champs est vide ?

                if (loginTxt.equals("") || passTxt.equals(""))
                {
                    Toast.makeText(AuthentificationActivity.this,"Champ email ou password vide",Toast.LENGTH_SHORT).show();
                    return;
                }


                //Utilisation des Intent
                Intent intent = new Intent(AuthentificationActivity.this,DisplayActivity.class);
                intent.putExtra(EXTRA_LOGIN, login.getText().toString());
                intent.putExtra(EXTRA_PASSWORD,pass.getText().toString());

                startActivity(intent);
            }
        });
    }
}
