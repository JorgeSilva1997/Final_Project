package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil_User extends AppCompatActivity {

    String name;
    String password;
    String email;
    String numero;
    String nif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_user);

        name = getIntent().getStringExtra("NOME");
        password = getIntent().getStringExtra("PASSWORD");
        email = getIntent().getStringExtra("EMAIL");
        numero = getIntent().getStringExtra("NUMERO");
        nif = getIntent().getStringExtra("NIF");
    }

}
