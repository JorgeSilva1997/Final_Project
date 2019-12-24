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


public class Edit_Convocatoria extends AppCompatActivity {
    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_pavilhao);
        EditText name = (EditText) findViewById(R.id.name);
        EditText rua = (EditText) findViewById(R.id.rua);
        EditText localidade = (EditText) findViewById(R.id.localidade);
        EditText distancia = (EditText) findViewById(R.id.distancia);

        id = getIntent().getStringExtra("ID");
        name.setText(getIntent().getStringExtra("nome"));
        rua.setText(getIntent().getStringExtra("rua"));
        localidade.setText(getIntent().getStringExtra("localidade"));
        distancia.setText(getIntent().getStringExtra("distancia"));
    }

    public void btnRegist(View view)
    {
        EditText name = (EditText) findViewById(R.id.name);
        EditText road = (EditText) findViewById(R.id.rua);
        EditText place = (EditText) findViewById(R.id.localidade);
        EditText distance = (EditText) findViewById(R.id.distancia);

        String nome = name.getText().toString();
        String rua = road.getText().toString();
        String localidade = place.getText().toString();
        String distancia = distance.getText().toString();

        Button regist = (Button) findViewById(R.id.btnregist);

        //validating inputs
                if (TextUtils.isEmpty(nome)) {
                    name.setError("Please enter the name of the pavilion");
                    name.requestFocus();
                    return;
                }else {
                    insert(nome, rua, localidade, distancia);
                }
    }

    //Metodo INSERT
    public void insert(String nome, String rua, String localidade, String distancia){
        String url = prefix_url + "/convocatoria/update/" + id;
        Log.d("updateabd; ID", id + "; NOME: " + nome);
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
                            if (response.getBoolean("status")) {
                                //Bloco de codigo
                                Toast.makeText(Edit_Convocatoria.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {

                                //Bloco de codigo
                                Toast.makeText(Edit_Convocatoria.this, "Erro na inserção!", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException ex) {
                            Log.d("regist", "" + ex);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit_Convocatoria.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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