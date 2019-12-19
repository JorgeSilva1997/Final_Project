package com.example.finalproject.EQUIPA;

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
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterEquipa;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Equipa extends AppCompatActivity {


    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    ArrayList<Equipa_Model> arrayEquipa = new ArrayList<>();
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipa);

        lista = (ListView) findViewById(R.id.lista);


        /////////////////////////   GET     /////////////////////////
        String url = prefix_url + "/equipass";
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
                                    arrayEquipa.add(new Equipa_Model(object1.getString("id"), object1.getString("nome")));
                                    //id.setText(object1.getString("id"));
                                    //nome.setText(object1.getString("nome"));
                                    //password.setText(object1.getString("password"));
                                    //email.setText(object1.getString("email"));
                                    //numero.setText(String.valueOf(object1.getInt("number")));
                                    //nif.setText(String.valueOf(object1.getInt("nif")));
                                    MyArrayAdapterEquipa itemsAdapter = new MyArrayAdapterEquipa(Equipa.this, arrayEquipa);
                                    ((ListView) findViewById(R.id.lista)).setAdapter(itemsAdapter);
                                }

                            } else {
                                Toast.makeText(Equipa.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(Equipa.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Equipa.this, "Erro 2!", Toast.LENGTH_SHORT).show();
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

                // BLOCO DE CODIGO

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
