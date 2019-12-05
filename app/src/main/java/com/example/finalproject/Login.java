package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.regex.Pattern;

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







    // Código para verificar se a PASSWORD cumpre os requisitos (Usado no Regist.java)
    public Boolean checkPass(String password)
    {
        Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=_])" +
                "(?=\\S+$)" +
                ".{6,12}" +
                "$");
        if (!PASSWORD_PATTERN.matcher(password).matches())
        {
            return false;
        }
        else
        {
            return true;
        }
    }



    // Encriptação da PASSWORD
    public String computeMD5Hash(String password)
    {
        byte[] md5Input = password.getBytes();
        BigInteger md5Data = null;
        try
        {
            md5Data = new BigInteger(1, md5.encryptMD5(md5Input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String md5Str = md5Data.toString(16);

        return md5Str;
    }
}
