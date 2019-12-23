package com.example.finalproject.ESCALAO;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterEscalao;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Escalao extends AppCompatActivity {


    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    ArrayList<Escalao_Model> arrayEscalao = new ArrayList<>();
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escalao);
        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);

        filllista();
    }
    private void filllista(){
        arrayEscalao.removeAll(arrayEscalao);
        /////////////////////////   GET     /////////////////////////
        String url = prefix_url + "/escalao";
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
                                    arrayEscalao.add(new Escalao_Model(object1.getString("id"), object1.getString("nome")));
                                    //id.setText(object1.getString("id"));
                                    //nome.setText(object1.getString("nome"));
                                    //password.setText(object1.getString("password"));
                                    //email.setText(object1.getString("email"));
                                    //numero.setText(String.valueOf(object1.getInt("number")));
                                    //nif.setText(String.valueOf(object1.getInt("nif")));
                                    MyArrayAdapterEscalao itemsAdapter = new MyArrayAdapterEscalao(Escalao.this, arrayEscalao);
                                    ((ListView) findViewById(R.id.lista)).setAdapter(itemsAdapter);
                                }

                            } else {
                                Toast.makeText(Escalao.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(Escalao.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Escalao.this, "Erro 2!", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    // Bloco de código para o adicionar novo escalao

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

                Intent intent = new Intent(Escalao.this, Regist_Escalao.class);
                startActivity(intent);
                filllista();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //CONTEXT MENU//
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Context mContext = this;
        switch (item.getItemId()) {
            case R.id.editar:
                Intent intent = new Intent(Escalao.this, Edit_Escalao.class);
                int itemPosition = info.position;
                String id = arrayEscalao.get(itemPosition).ID;
                intent.putExtra("ID", id);
                intent.putExtra("nome", arrayEscalao.get(itemPosition).NOME);
                startActivity(intent);
                return true;
            case R.id.remover:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(true);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*int itemPosition = info.position;
                                c.moveToPosition(itemPosition);
                                int id_contacto = c.getInt(c.getColumnIndex(Contrato.Contacto._ID));
                                deleteFromBD(id_contacto);*/
                                int itemPosition = info.position;
                                String idremove = arrayEscalao.get(itemPosition).ID;
                                deleteFromBD(idremove);
                                filllista();
                            }
                        });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    //CONTEXT MENU

    private void deleteFromBD(String id){
        String url = prefix_url + "/escalao/delete/" + id ;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean resultado = response.getBoolean("status");
                            if (resultado) {
                                filllista();
                            } else {
                                //password.setText(null);
                                Toast.makeText(Escalao.this, "Eliminar falhou", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Log.d("Erro de ", "" + ex);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Escalao.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}