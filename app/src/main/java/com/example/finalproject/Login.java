package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    // Este ID é devido a ser Multi-User
    int id_int = -1;
    String id_string;

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void btnLogin(View view)
    {
        EditText name = (EditText)findViewById(R.id.user);
        EditText pass = (EditText)findViewById(R.id.pass);

        String nome = name.getText().toString();
        String password = pass.getText().toString();
        Button login = (Button)findViewById(R.id.btnLogin);

        if (!nome.isEmpty() && !password.isEmpty())
        {
            String url = prefix_url + "/user/login";
            Map<String, String> jsonParams = new HashMap<String, String>();
            jsonParams.put("nome", nome);
            jsonParams.put("password", password);

            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, url,
                    new JSONObject(jsonParams),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                Toast.makeText(Login.this, "" + status, Toast.LENGTH_SHORT).show();
                                if (status) {
                                    Intent intent = new Intent(Login.this, Main.class);
                                    intent.putExtra("ID", response.getString("id"));
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Login.this, "Tenta outra vez", Toast.LENGTH_SHORT).show();
                                    id_string = null;
                                }
                            } catch (JSONException ex) {
                                Log.d("login", "" + ex);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "erro", Toast.LENGTH_SHORT).show();
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
            MySingleton.getInstance(Login.this).addToRequestQueue(postRequest);


        }
        else
        {
            Toast.makeText(Login.this, "Preencha os campos.", Toast.LENGTH_SHORT).show();
        }

    }

    public void getUserId(String nome, String password)  {

        String url = prefix_url + "/utilizador/autenticar/" + nome + "/" + password;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        JSONObject obj = response.getJSONObject(("DATA"));
                        id_string = obj.optString("id");
                        id_int = Integer.parseInt(id_string);
                        Intent intent = new Intent(Login.this, Main.class);
                        intent.putExtra("ID", id_int);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "" + status, Toast.LENGTH_SHORT).show();
                        id_string = null;
                    }
                } catch (JSONException ex) {
                    Log.d("login", "" + ex);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}
