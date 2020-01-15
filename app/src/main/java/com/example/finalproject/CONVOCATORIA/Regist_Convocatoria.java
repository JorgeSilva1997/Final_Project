package com.example.finalproject.CONVOCATORIA;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterEquipa;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterProva;
import com.example.finalproject.EQUIPA.Equipa_Model;
import com.example.finalproject.PROVA.Prova;
import com.example.finalproject.PROVA.Prova_Model;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Regist_Convocatoria extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";

    ArrayList<Equipa_Model> arrayEquipa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registo_convocatoria);

        // usado dentro so status para receber o id de cada equipa
        // int tipo = response.getInt("tipo");

    }

    public void btnAdd(View view)
    {

        EditText Numerojogo = (EditText) findViewById(R.id.numeroJogo);
        EditText Data = (EditText) findViewById(R.id.datahora);
        EditText Prova = (EditText) findViewById(R.id.prova);
        EditText Escalao = (EditText) findViewById(R.id.escalao);
        EditText Eq_visitada = (EditText) findViewById(R.id.equipa_visitada);
        EditText Eq_visitante = (EditText) findViewById(R.id.equipa_visitante);
        EditText Pavilhao = (EditText) findViewById(R.id.pavilhao);
        EditText User = (EditText) findViewById(R.id.user);

        String id = Numerojogo.getText().toString();
        String datahora = Data.getText().toString();
        String prova_id = Prova.getText().toString();
        String escalao_id = Escalao.getText().toString();
        String eq_visitada_id = Eq_visitada.getText().toString();
        String eq_visitante_id = Eq_visitante.getText().toString();
        String pavilhao_id = Pavilhao.getText().toString();
        String user_id = User.getText().toString();

        Button regist = (Button) findViewById(R.id.btnadd);


        //validating inputs
                if (TextUtils.isEmpty(id)) {
                    Numerojogo.setError("Insira um número de jogo");
                    Numerojogo.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(datahora)) {
                    Data.setError("Insira uma data");
                    Data.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(prova_id)) {
                    Prova.setError("Insira um tipo de prova");
                    Prova.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(escalao_id)) {
                    Escalao.setError("Insira um escalão");
                    Escalao.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(eq_visitada_id)) {
                    Eq_visitada.setError("Insira uma equipa visitada");
                    Eq_visitada.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(eq_visitante_id)) {
                    Eq_visitante.setError("Insira uma equipa visitante");
                    Eq_visitante.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pavilhao_id)) {
                    Pavilhao.setError("Insira um pavilhão");
                    Pavilhao.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(user_id)) {
                    User.setError("Insira um árbitro");
                    User.requestFocus();
                    return;
                }
                else {

                    add(id, datahora, prova_id, escalao_id, eq_visitada_id, eq_visitante_id, pavilhao_id, user_id);
                }
    }

    //Metodo INSERT
    public void add(String id, String datahora, String prova_id, String escalao_id, String eq_visitada_id, String eq_visitante_id, String pavilhao_id, String user_id)
    {
        String url = prefix_url + "/teste/convocatoria/insert";
        //String url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api/userss/insert";
        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("id", id);

        jsonParams.put("datahora", datahora);

        jsonParams.put("prova_id", prova_id);

        jsonParams.put("escalao_id", escalao_id);

        jsonParams.put("equipa_visitada_id", eq_visitada_id);

        jsonParams.put("equipa_visitante_id", eq_visitante_id);

        jsonParams.put("pavilhao_id", pavilhao_id);

        jsonParams.put("user_id", user_id);
        Log.d("parametros json: ", "" + jsonParams);
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