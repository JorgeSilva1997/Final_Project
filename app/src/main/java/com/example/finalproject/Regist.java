package com.example.finalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.util.regex.Pattern;


public class Regist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
    }

    public void btnRegist(View view)
    {

        EditText nome = (EditText) findViewById(R.id.name);
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);
        EditText numero = (EditText) findViewById(R.id.number);
        EditText NIF = (EditText) findViewById(R.id.nif);
        //final Spinner type = (Spinner) findViewById(R.id.spinnerTipo);

        String name = nome.getText().toString();
        String pass = password.getText().toString();
        String mail = email.getText().toString();
        String number = numero.getText().toString();
        String nif = NIF.getText().toString();
        Button regist = (Button) findViewById(R.id.btnregist);


        //validating inputs
                if (TextUtils.isEmpty(name)) {
                    nome.setError("Please enter your username");
                    nome.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mail)) {
                    email.setError("Please enter your email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    numero.setError("Please enter your number");
                    numero.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(nif)) {
                    NIF.setError("Please enter your NIF");
                    NIF.requestFocus();
                    return;
                }
                else {
                    //insert();
                }
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

        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
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