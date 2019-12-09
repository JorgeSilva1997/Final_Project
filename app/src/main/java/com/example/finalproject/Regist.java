package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Regist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
    }
/*
    public void goToMain(View view)
    {

        Intent i = new Intent(Regist.this, XPTO.class);
        startActivity(i);

    }
*/
    public void btnRegist(View view)
    {

        final EditText nome = (EditText) findViewById(R.id.name);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText numero = (EditText) findViewById(R.id.number);
        final EditText NIF = (EditText) findViewById(R.id.nif);
        //final Spinner type = (Spinner) findViewById(R.id.spinnerTipo);

        Button regist = (Button) findViewById(R.id.btnregist);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nome.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString();
                String number = numero.getText().toString();
                String nif = NIF.getText().toString();
                //String tipo = type.getTe

        // Restrições à inserção de USER's
        if (name.equals("") || pass.equals("") || mail.equals("") || number.equals("") || nif.equals(""))
        {
            // Mensagem de WARNING para campos vazios
            Toast.makeText(Regist.this, "Campos vazios", Toast.LENGTH_LONG).show();
        }
        else
            {
                // Mensagem de Bem-vindo
                Toast.makeText(getApplicationContext(), "Entrou", Toast.LENGTH_SHORT).show();

                // Verificar se o USER já existe


                // Inserir USER na BD

            }

            }
        });
    }



    // Código para verificar se a PASSWORD cumpre os requisitos
    public Boolean checkPass(String password)
    {
        Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=_])" +
                "(?=\\S+$)" +
                ".{6,15}" +
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

    // Check Email is Valid
    private boolean validateEmail()
    {
        final EditText email = (EditText) findViewById(R.id.email);
        String emailInput = email.getText().toString();

        if (emailInput.isEmpty())
        {
            email.setError("Campo Vazio");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            email.setError("Please enter a valid email address");
        }
        else
        {
            email.setError(null);
        }   return true;
    }

    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
                );
}