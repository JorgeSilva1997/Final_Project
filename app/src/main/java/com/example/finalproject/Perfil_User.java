package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Perfil_User extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php";
    int id;

    TextView nome = (TextView) findViewById(R.id.EditTextNome);
    TextView password = (TextView) findViewById(R.id.EditTextPassword);
    TextView email = (TextView) findViewById(R.id.EditTextEmail);
    TextView numero = (TextView) findViewById(R.id.EditTextNumber);
    TextView nif = (TextView) findViewById(R.id.EditTextNif);

    /*String NOME = nome.getText().toString();
    String PASSWORD = password.getText().toString();
    String EMAIL = email.getText().toString();
    String NUMERO = numero.getText().toString();
    String NIF = nif.getText().toString();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_user);
        id = getIntent().getIntExtra("ID", -1);

        Toast.makeText(Perfil_User.this, "" + id, Toast.LENGTH_SHORT).show();

        perfil();
    }

    public void perfil() {
        String url = prefix_url + "/api/utilizadores/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status = response.getBoolean("status");
                            if (status) {

                                JSONArray array = response.getJSONArray("DATA");

                                for (int i = 0; i < array.length(); i++) {

                                    // CODIGO
                                    JSONObject object1 = array.getJSONObject(i);
                                    nome.setText(object1.getString("nome"));
                                    password.setText(object1.getString("password"));
                                    email.setText(object1.getString("email"));
                                    numero.setText(object1.getString("numero"));
                                    nif.setText(object1.getString("nif"));

                                }

                            } else {
                                Toast.makeText(Perfil_User.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(Perfil_User.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Perfil_User.this, "Erro 2!", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}
