package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void btnLogin(View view)
    {
        final EditText name = (EditText)findViewById(R.id.user);
        final EditText pass = (EditText)findViewById(R.id.pass);
        Button login = (Button)findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!name.getText().toString().isEmpty() && !pass.getText().toString().isEmpty())
                {
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Login.this, "Preencha os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
