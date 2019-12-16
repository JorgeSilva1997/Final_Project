package com.example.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main_admin extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php";
    int id, tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin);

        id = getIntent().getIntExtra("ID", -1);
        tipo = getIntent().getIntExtra("TIPO", -1);

    }

    public void btnRegist(View view) {

        Intent intent = new Intent(Main_admin.this, Regist.class);
        intent.putExtra("ID", id);
        startActivity(intent);

    }

}
