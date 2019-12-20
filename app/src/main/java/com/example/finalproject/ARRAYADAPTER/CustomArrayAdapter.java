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

public class CustomArrayAdapter extends ArrayAdapter<User_Model> {
    public CustomArrayAdapter(Context context, ArrayList<User_Model> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User_Model c = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.perfil_user, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.EditTextNome)).setText(c.getNome());
        ((TextView) convertView.findViewById(R.id.EditTextPassword)).setText(c.getPassword());
        ((TextView) convertView.findViewById(R.id.EditTextEmail)).setText(String.valueOf(c.getEmail()));
        ((TextView) convertView.findViewById(R.id.EditTextNumber)).setText(String.valueOf(c.getNumber()));
        ((TextView) convertView.findViewById(R.id.EditTextNif)).setText(c.getNif());


        return convertView;
    }
}