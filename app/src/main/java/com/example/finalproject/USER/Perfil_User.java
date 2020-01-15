package com.example.finalproject.USER;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterConvocatoria;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterEscalao;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterPerfil;
import com.example.finalproject.ARRAYADAPTER.MyArrayAdapterUser;
import com.example.finalproject.ESCALAO.Escalao;
import com.example.finalproject.R;
import com.example.finalproject.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Perfil_User extends AppCompatActivity {

    String prefix_url = "http://andrefelix.dynip.sapo.pt/projetofinalpm/index.php";
    int id;


    ListView lista_user;

    ArrayList<User_Model> arrayUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_user);
        id = getIntent().getIntExtra("ID", -1);

        lista_user = (ListView) findViewById(R.id.lista_user);

        Toast.makeText(Perfil_User.this, "" + id, Toast.LENGTH_SHORT).show();

        perfil();
    }

    public void perfil() {

        String url = prefix_url + "/api/utilizadores/" + id;
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
                                    arrayUser.add(new User_Model(object1.getString("id"), object1.getString("nome"), object1.getString("password"), object1.getString("email"), object1.getString("number"), object1.getString("nif"), object1.getString("tipo")));
                                    MyArrayAdapterPerfil itemsAdapter = new MyArrayAdapterPerfil(Perfil_User.this, arrayUser);
                                    ((ListView) findViewById(R.id.lista_user)).setAdapter(itemsAdapter);

                                }

                            } else {
                                Toast.makeText(Perfil_User.this, "" + status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
                            Toast.makeText(Perfil_User.this, "Erro 1!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Perfil_User.this, "Erro 2!", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}
