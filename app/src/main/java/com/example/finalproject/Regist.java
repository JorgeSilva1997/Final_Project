package com.example.finalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class Regist extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";


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

                    insert(name, pass, mail, number, nif);
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
    //Check Number
    private boolean checkEmail()
    {
        final EditText number = (EditText) findViewById(R.id.number);
        String numberInput = number.getText().toString();
        int numberInt = Integer.parseInt(numberInput);
        if ( numberInt != 9)
        {
            number.setError("Please enter a valid number");
        }
        else
            {
                number.setError(null);
            }   return true;
    }

    //Check NIF
    private boolean checkNif()
    {
        final EditText nif = (EditText) findViewById(R.id.nif);
        String nifInput = nif.getText().toString();
        int nifInt = Integer.parseInt(nifInput);
        if (nifInt != 9)
        {
            nif.setError("Please enter a valid nif");
        }
        else
        {
            nif.setError(null);
        }   return true;
    }

    //Metodo INSERT
    public void insert(String name, String pass, String mail, String number, String nif)
    {
        String url = prefix_url + "/users/insert";
        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("nome", name);
        jsonParams.put("password", pass);
        jsonParams.put("email", mail);
        jsonParams.put("number", number);
        jsonParams.put("nif", nif);

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            boolean status = response.getBoolean("status");

                            if (status) {

                            } else {

                            }
                        } catch (JSONException ex) {
                            Log.d("regist", "" + ex);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Regist.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));

                return headers;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
}