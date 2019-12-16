package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    int id, tipo;
    String nome, password, email, numero, nif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        id = getIntent().getIntExtra("ID", -1);
        tipo = getIntent().getIntExtra("TIPO", -1);

        Toast.makeText(Main.this, "" + id, Toast.LENGTH_SHORT).show();
        //Toast.makeText(Main.this, "" + tipo, Toast.LENGTH_SHORT).show();
    }

    public void btnPerfil(View view) {

        Intent intent = new Intent(Main.this, Perfil_User.class);
        intent.putExtra("ID", id);
        startActivity(intent);

            }

}
