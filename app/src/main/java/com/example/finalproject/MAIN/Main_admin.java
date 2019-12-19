package com.example.finalproject.MAIN;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.EQUIPA.Equipa;
import com.example.finalproject.ESCALAO.Escalao;
import com.example.finalproject.PAVILHAO.Pavilhao;
import com.example.finalproject.PROVA.Prova;
import com.example.finalproject.USER.List_Users;
import com.example.finalproject.R;

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

    public void btnUsers(View view) {
        Intent intent = new Intent(Main_admin.this, List_Users.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    public void btnPavilhao(View view)  {
        Intent intent = new Intent(Main_admin.this, Pavilhao.class);
        startActivity(intent);
    }

    public void btnProva(View view)  {
        Intent intent = new Intent(Main_admin.this, Prova.class);
        startActivity(intent);
    }

    public void btnEscaloes(View view)  {
        Intent intent = new Intent(Main_admin.this, Escalao.class);
        startActivity(intent);
    }

    public void btnEquipa(View view) {
        Intent intent = new Intent(Main_admin.this, Equipa.class);
        startActivity(intent);
    }



    // Bloco de código para o LOGOUT

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.LogOut:
                SharedPreferences mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.remove("name");
                editor.remove("password");
                editor.commit();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
