package com.example.finalproject.USER;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;
import com.example.finalproject.md5;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class Regist extends AppCompatActivity {
    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        tipo = 1;

        Spinner spinTipo = findViewById(R.id.spinnerTipo);
        spinTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void btnRegist(View view)
    {

        EditText name = (EditText) findViewById(R.id.name);
        EditText pass = (EditText) findViewById(R.id.password);
        EditText mail = (EditText) findViewById(R.id.email);
        EditText numero = (EditText) findViewById(R.id.number);
        EditText NIF = (EditText) findViewById(R.id.nif);
        //final Spinner type = (Spinner) findViewById(R.id.spinnerTipo);

        String nome = name.getText().toString();
        String password = pass.getText().toString();
        String email = mail.getText().toString();
        String number = numero.getText().toString();
        String nif = NIF.getText().toString();
        Button regist = (Button) findViewById(R.id.btnregist);


        //validating inputs
                if (TextUtils.isEmpty(nome)) {
                    name.setError("Please enter your username");
                    name.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    pass.setError("Please enter your password");
                    pass.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    mail.setError("Please enter your email");
                    mail.requestFocus();
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

                    insert(nome, password, email, number, nif, tipo);
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
    public String validateEmail(String mail)
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
        }   return mail;
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

    //Metodo INSERT
    public void insert(String nome, String password, String email, String number, String nif, int tipo)
    {
        String url = prefix_url + "/teste/users/insert";
        Map<String, String> jsonParams = new HashMap<String, String>();

        String tipoString = String.valueOf(tipo);

        jsonParams.put("nome", nome);

        String passCheck = computeMD5Hash(password);
        jsonParams.put("password", passCheck);

        String emailCheck = validateEmail(email);
        jsonParams.put("email", emailCheck);

        jsonParams.put("number", number);

        jsonParams.put("nif", nif);

        jsonParams.put("tipo", tipoString);

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            boolean status = response.getBoolean("status");
                            //Toast.makeText(Regist.this, "" + status, Toast.LENGTH_SHORT).show();

                            if (status) {

                                //Bloco de codigo
                                Toast.makeText(Regist.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {

                                //Bloco de codigo
                                Toast.makeText(Regist.this, "Erro na inserção!", Toast.LENGTH_SHORT).show();

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
                headers.put("User_Model-agent", System.getProperty("http.agent"));

                return headers;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
}