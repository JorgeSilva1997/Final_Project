package com.example.finalproject.USER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterUser;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class List_Users extends AppCompatActivity {
    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php/api";
    int id;
    ArrayList<User_Model> arrayUsers = new ArrayList<>();
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__user);
        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);
        id = getIntent().getIntExtra("ID", -1);
        filllista();
    }
        private void filllista(){
        arrayUsers.removeAll(arrayUsers);
        /////////////////////////   GET     /////////////////////////
        String url = prefix_url + "/utilizador";
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
                                    arrayUsers.add(new User_Model(object1.getString("id"), object1.getString("nome"),
                                            object1.getString("password"), object1.getString("email"),
                                            object1.getString("number"), object1.getString("nif"), object1.getString("tipo")));
                                    //id.setText(object1.getString("id"));
                                    //nome.setText(object1.getString("nome"));
                                    //password.setText(object1.getString("password"));
                                    //email.setText(object1.getString("email"));
                                    //numero.setText(String.valueOf(object1.getInt("number")));
                                    //nif.setText(String.valueOf(object1.getInt("nif")));
                                    MyArrayAdapterUser itemsAdapter = new MyArrayAdapterUser(List_Users.this, arrayUsers);
                                    ((ListView) findViewById(R.id.lista)).setAdapter(itemsAdapter);
                                }
                            } else {
                                Toast.makeText(List_Users.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(List_Users.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(List_Users.this, "Erro 2!", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    // Options MENU BAR
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
                Intent intent = new Intent(List_Users.this, Regist.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //OPTIONS MENU BAR//

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
                Intent intent = new Intent(List_Users.this, Edit_User.class);
                int itemPosition = info.position;
                String id = arrayUsers.get(itemPosition).id;
                intent.putExtra("ID", id);
                intent.putExtra("nome", arrayUsers.get(itemPosition).nome);
                intent.putExtra("password", arrayUsers.get(itemPosition).password);
                intent.putExtra("email", arrayUsers.get(itemPosition).email);
                intent.putExtra("number", arrayUsers.get(itemPosition).number);
                intent.putExtra("nif", arrayUsers.get(itemPosition).nif);
                intent.putExtra("tipo", arrayUsers.get(itemPosition).tipo);
                startActivity(intent);
                arrayUsers.removeAll(arrayUsers);
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
                                String idremove = arrayUsers.get(itemPosition).id;
                                deleteFromBD(idremove);
                                arrayUsers.removeAll(arrayUsers);
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
        String url = prefix_url + "/users/delete/" + id ;
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
                                Toast.makeText(List_Users.this, "Eliminar falhou", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Log.d("Erro de ", "" + ex);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(List_Users.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}