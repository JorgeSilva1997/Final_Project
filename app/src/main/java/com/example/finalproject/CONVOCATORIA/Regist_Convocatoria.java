package com.example.finalproject.CONVOCATORIA;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Regist_Convocatoria extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_pavilhao);
    }

    public void btnRegist(View view)
    {

        EditText name = (EditText) findViewById(R.id.name);
        EditText street = (EditText) findViewById(R.id.rua);
        EditText place = (EditText) findViewById(R.id.localidade);
        EditText distance = (EditText) findViewById(R.id.distancia);

        String nome = name.getText().toString();
        String rua = street.getText().toString();
        String localidade = place.getText().toString();
        String distancia = distance.getText().toString();


        Button regist = (Button) findViewById(R.id.btnregist);


        //validating inputs
                if (TextUtils.isEmpty(nome)) {
                    name.setError("Please enter the name of the pavilion");
                    name.requestFocus();
                    return;
                }
                else {

                    insert(nome, rua, localidade, distancia);
                }
    }

    //Metodo INSERT
    public void insert(String nome, String rua, String localidade, String distancia)
    {
        String url = prefix_url + "/convocatoria/insert";
        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("nome", nome);
        jsonParams.put("rua", rua);
        jsonParams.put("localidade", localidade);
        jsonParams.put("distancia", distancia);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            boolean status = response.getBoolean("status");
                            if (status) {
                                //Bloco de codigo
                                Toast.makeText(Regist_Convocatoria.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                //Bloco de codigo
                                Toast.makeText(Regist_Convocatoria.this, "Erro na inserção!", Toast.LENGTH_SHORT).show();
                                Log.d("regist1", "" + status);
                            }
                        } catch (JSONException ex) {
                            Log.d("regist2", "" + ex);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Regist_Convocatoria.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("regist3", "" + error.getMessage());
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