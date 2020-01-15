package com.example.finalproject.LOGIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.MAIN.Main;
import com.example.finalproject.MAIN.Main_admin;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;
import com.example.finalproject.md5;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {

// TESTE
    private FirebaseAuth firebaseAuth;
//
    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    // Este ID é devido a ser Multi-User_Model
    int id_int = -1;
    String id_string;
    private EditText name, pass;
    boolean status;

    /////////   Codigo do Feliz ///////////////
    //CheckBox cb1;
    //EditText username, password;
    //SharedPreferences sharedPreferences;
    //SharedPreferences.Editor editor;
    private CheckBox lembrar;
    public static String PREFS_NAME = "mypre";
    public static String PREF_LEMBRAR = "lembrar";
    public static String PREF_USERNAMESP = "username";
    public static String PREF_PASSWORDSP = "password";
    public static String PREF_USERID = "userid";

    /////////   Codigo do Feliz ///////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // TESTE
        firebaseAuth = FirebaseAuth.getInstance();
        //
    }
    public void btnLogin(View view) {
        EditText name = (EditText) findViewById(R.id.user);
        EditText pass = (EditText) findViewById(R.id.pass);

        String nome = name.getText().toString();
        String password = pass.getText().toString();

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
        else {

            autenticacao(nome, password);
            autenticacao_firebase(nome, password);
        }
    }

    public void autenticacao_firebase(String nome, String password) {

        firebaseAuth.createUserWithEmailAndPassword(nome, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())    {
                    Toast.makeText(Login.this, "Registado com sucesso", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void autenticacao(String nome, String password)  {

        String url = prefix_url + "/user/login";
        //SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //String usernamesp = pref.getString(PREF_USERNAMESP, null);
        //String passwordsp = pref.getString(PREF_PASSWORDSP, null);
        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("nome", nome);
        String md5Str = computeMD5Hash(password);
        jsonParams.put("password", md5Str);

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            boolean status = response.getBoolean("status");
                            //Toast.makeText(Login.this, "" + status, Toast.LENGTH_SHORT).show();

                            if (status) {
                                int tipo = response.getInt("tipo");
                                if (tipo == 0){
                                    Intent intent = new Intent(Login.this, Main_admin.class);
                                    intent.putExtra("ID", response.getInt("id"));
                                    //intent.putExtra("TIPO", response.getInt("tipo"));
                                    startActivity(intent);
                                }
                                else {
                                    Intent intent = new Intent(Login.this, Main.class);
                                    intent.putExtra("ID", response.getInt("id"));
                                    //intent.putExtra("TIPO", response.getInt("tipo"));
                                    startActivity(intent);
                                }

                            } else {
                                Toast.makeText(Login.this, "Login Falhou!", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException ex) {
                            Log.d("login", "" + ex);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

    // Função MD5
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

    public void btnForgot(View view) {
        Intent intent = new Intent(Login.this, Forgot_Pass.class);
        startActivity(intent);
    }



}

