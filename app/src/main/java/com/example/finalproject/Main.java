package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Main extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php";
    int id, tipo;
    String nome, password, email, numero, nif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        id = getIntent().getIntExtra("ID", -1);
        tipo = getIntent().getIntExtra("TIPO", -1);

        Toast.makeText(Main.this, "" + id, Toast.LENGTH_SHORT).show();
        //Toast.makeText(Main.this, "" + tipo, Toast.LENGTH_SHORT).show();
    }

    public void btnPerfil(View view) {

        String url = prefix_url + "/api/utilizadores/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status = response.getBoolean("status");
                            if (status) {
                                JSONObject obj = response.getJSONObject(("DATA"));
                                nome = obj.optString("nome");
                                password = obj.optString("password");
                                email = obj.optString("email");
                                numero = obj.optString("numero");
                                nif = obj.optString("nif");
                                Intent intent = new Intent(Main.this, Perfil_User.class);
                                intent.putExtra("NOME", nome);
                                intent.putExtra("PASSWORD", password);
                                intent.putExtra("EMAIL", email);
                                intent.putExtra("NUMERO", numero);
                                intent.putExtra("NIF", nif);
                                startActivity(intent);
                                //Toast.makeText(Main.this, "" + status, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Main.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(Main.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main.this, "Erro 2!", Toast.LENGTH_SHORT).show();
                    }
                });
                VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
            }

}
