package com.example.finalproject.USER;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.CONVOCATORIA.Convocatoria_Model;
import com.example.finalproject.MAIN.Main;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyPlay_User extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";

    TextView numero;
    TextView data;
    TextView prova;
    TextView escalao;
    TextView equipa_visitada;
    TextView equipa_visitante;
    TextView pavilhao;
    TextView user;
    int id;

    ArrayList<Convocatoria_Model> arrayConvocatoria= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myplay_user);

        numero = (TextView) findViewById(R.id.id_convocatoria);
        data = (TextView) findViewById(R.id.datahora);
        prova = (TextView) findViewById(R.id.prova_nome);
        escalao = (TextView) findViewById(R.id.escalao_nome);
        equipa_visitada = (TextView) findViewById(R.id.equipa_visitada_nome);
        equipa_visitante = (TextView) findViewById(R.id.equipa_visitante_nome);
        pavilhao = (TextView) findViewById(R.id.pavilhao_nome);
        user = (TextView) findViewById(R.id.user_nome);

        id = getIntent().getIntExtra("ID", -1);
        Toast.makeText(MyPlay_User.this, "" + id, Toast.LENGTH_SHORT).show();
        //tipo = getIntent().getIntExtra("TIPO", -1);
        filllista();
    }

    private void filllista(){
        String url = prefix_url + "/convocatorias/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status = response.getBoolean("status");
                            if (status) {

                                JSONArray array = response.getJSONArray("DATA");

                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject object1 = array.getJSONObject(i);
                                    arrayConvocatoria.add(new Convocatoria_Model("#"+object1.getString("id_convocatoria"),
                                            object1.getString("datahora"), object1.getString("prova_id"),
                                            object1.getString("prova_nome"), object1.getString("escalao_id"),
                                            object1.getString("escalao_nome"), object1.getString("equipa_visitada_id"),
                                            object1.getString("equipa_visitada_nome"), object1.getString("equipa_visitante_id"),
                                            object1.getString("equipa_visitante_nome"), object1.getString("pavilhao_id"),
                                            object1.getString("pavilhao_nome"), object1.getString("user_id"),
                                            object1.getString("user_nome")));

                                    // TextViews para passar os parametros
                                    numero.setText(object1.getString("id_convocatoria"));
                                    data.setText(object1.getString("datahora"));
                                    prova.setText(object1.getString("prova_nome"));
                                    escalao.setText(object1.getString("escalao_nome"));
                                    equipa_visitada.setText(object1.getString("equipa_visitada_nome"));
                                    equipa_visitante.setText(object1.getString("equipa_visitante_nome"));
                                    pavilhao.setText(object1.getString("pavilhao_nome"));
                                    user.setText(object1.getString("user_nome"));



                                }
                            } else {
                                Toast.makeText(MyPlay_User.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(MyPlay_User.this, "Erro 1!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyPlay_User.this, "Não tem jogos associados!", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
