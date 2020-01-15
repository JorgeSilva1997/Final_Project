package com.example.finalproject.ARRAYADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.USER.User_Model;

import java.util.ArrayList;

public class MyArrayAdapterPerfil extends ArrayAdapter<User_Model> {

    public MyArrayAdapterPerfil(Context context, ArrayList<User_Model> contacts)
    {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User_Model c = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.linha_perfil, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.nome)).setText(c.getNome());
        ((TextView) convertView.findViewById(R.id.pass)).setText(c.getPassword());
        ((TextView) convertView.findViewById(R.id.email)).setText(c.getEmail());
        ((TextView) convertView.findViewById(R.id.number)).setText(c.getNumber());
        ((TextView) convertView.findViewById(R.id.nif)).setText(c.getNif());

        return convertView;
    }
}
