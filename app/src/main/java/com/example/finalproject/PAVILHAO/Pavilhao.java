package com.example.finalproject.PAVILHAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapter;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pavilhao extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    ArrayList<Pavilhao_Model> arrayPavilhao = new ArrayList<>();
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pavilhao);

        lista = (ListView) findViewById(R.id.lista);
        //id = getIntent().getIntExtra("ID", -1);
        //tipo = getIntent().getIntExtra("TIPO", -1);
        filllista();
    }
    private void filllista(){
        arrayPavilhao.removeAll(arrayPavilhao);

        /////////////////////////   GET     /////////////////////////
        String url = prefix_url + "/pavilhao";
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
                                    arrayPavilhao.add(new Pavilhao_Model(object1.getString("id"), object1.getString("nome"), object1.getString("rua"), object1.getString("localidade"), object1.getString("distancia")));
                                    //id.setText(object1.getString("id"));
                                    //nome.setText(object1.getString("nome"));
                                    //password.setText(object1.getString("password"));
                                    //email.setText(object1.getString("email"));
                                    //numero.setText(String.valueOf(object1.getInt("number")));
                                    //nif.setText(String.valueOf(object1.getInt("nif")));
                                    MyArrayAdapter itemsAdapter = new MyArrayAdapter(Pavilhao.this, arrayPavilhao);
                                    ((ListView) findViewById(R.id.lista)).setAdapter(itemsAdapter);
                                }

                            } else {
                                Toast.makeText(Pavilhao.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(Pavilhao.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Pavilhao.this, "Erro 2!", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    // Bloco de código para o adicionar nova Equipa
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_prova, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.Add:

                Intent intent = new Intent(Pavilhao.this, Pavilhao.class);
                startActivity(intent);
                filllista();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}