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
        setContentView(R.layout.edit_convocatoria);

        EditText Numerojogo = (EditText) findViewById(R.id.numeroJogo);
        EditText Data = (EditText) findViewById(R.id.datahora);
        EditText Prova = (EditText) findViewById(R.id.prova);
        EditText Escalao = (EditText) findViewById(R.id.escalao);
        EditText Eq_visitada = (EditText) findViewById(R.id.equipa_visitada);
        EditText Eq_visitante = (EditText) findViewById(R.id.equipa_visitante);
        EditText Pavilhao = (EditText) findViewById(R.id.pavilhao);
        EditText User = (EditText) findViewById(R.id.user);

        //id = getIntent().getStringExtra("ID");
        Numerojogo.setText(getIntent().getStringExtra("ID"));
        Data.setText(getIntent().getStringExtra("datahora"));
        Prova.setText(getIntent().getStringExtra("prova_id"));
        Escalao.setText(getIntent().getStringExtra("escalao_id"));
        Eq_visitada.setText(getIntent().getStringExtra("equipa_visitada_id"));
        Eq_visitante.setText(getIntent().getStringExtra("equipa_visitante_id"));
        Pavilhao.setText(getIntent().getStringExtra("pavilhao_id"));
        User.setText(getIntent().getStringExtra("user_id"));
    }

    public void btnRegist(View view)
    {
        EditText Numerojogo = (EditText) findViewById(R.id.numeroJogo);
        EditText Data = (EditText) findViewById(R.id.datahora);
        EditText Prova = (EditText) findViewById(R.id.prova);
        EditText Escalao = (EditText) findViewById(R.id.escalao);
        EditText Eq_visitada = (EditText) findViewById(R.id.equipa_visitada);
        EditText Eq_visitante = (EditText) findViewById(R.id.equipa_visitante);
        EditText Pavilhao = (EditText) findViewById(R.id.pavilhao);
        EditText User = (EditText) findViewById(R.id.user);

        String numerojogo = Numerojogo.getText().toString();
        String data = Data.getText().toString();
        String prova = Prova.getText().toString();
        String escalao = Escalao.getText().toString();
        String eq_visitada = Eq_visitada.getText().toString();
        String eq_visitante = Eq_visitante.getText().toString();
        String pavilhao = Pavilhao.getText().toString();
        String user = User.getText().toString();

        Button regist = (Button) findViewById(R.id.btnregist);

        //validating inputs
                if (TextUtils.isEmpty(numerojogo)) {
                    Numerojogo.setError("Por favor preencha o campo numero do jogo.");
                    Numerojogo.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(data)) {
                    Data.setError("Por favor preencha o campo data.");
                    Data.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(prova)) {
                    Prova.setError("Por favor preencha o campo prova.");
                    Prova.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(escalao)) {
                    Escalao.setError("Por favor preencha o campo escalao.");
                    Escalao.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(eq_visitada)) {
                    Eq_visitada.setError("Por favor preencha o campo equipa visitada.");
                    Eq_visitada.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(eq_visitante)) {
                    Eq_visitante.setError("Por favor preencha o campo equipa visitante.");
                    Eq_visitante.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pavilhao)) {
                    Pavilhao.setError("Por favor preencha o campo pavilhão.");
                    Pavilhao.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(user)) {
                    User.setError("Por favor preencha o campo user.");
                    User.requestFocus();
                    return;
                }
                else {
                    update(numerojogo, data, prova, escalao, eq_visitada, eq_visitante, pavilhao, user);
                }
    }

    //Metodo UPDATE
    public void update(String numerojogo, String data, String prova, String escalao, String eq_visitada, String eq_visitante, String pavilhao, String user){

        String url = prefix_url + "/api/convocatoria/" + id + "/update";
        Log.d("updateabd; ID", id + "; NOME: " + numerojogo);
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("ID", numerojogo);
        jsonParams.put("datahora", data);
        jsonParams.put("prova_id", prova);
        jsonParams.put("escalao_id", escalao);
        jsonParams.put("eq_visitada_id", eq_visitada);
        jsonParams.put("eq_visitante_id", eq_visitante);
        jsonParams.put("pavilhao_id", pavilhao);
        jsonParams.put("user_id", user);

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("status")) {
                                //Bloco de codigo
                                Toast.makeText(Edit_Convocatoria.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {

                                //Bloco de codigo
                                Toast.makeText(Edit_Convocatoria.this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();

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