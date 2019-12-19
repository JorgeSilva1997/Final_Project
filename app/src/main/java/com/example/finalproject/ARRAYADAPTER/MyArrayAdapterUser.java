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

public class MyArrayAdapterUser extends ArrayAdapter<User_Model> {

    public MyArrayAdapterUser(Context context, ArrayList<User_Model> contacts)
    {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User_Model c = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.linha_prova, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.id)).setText(c.getId());
        ((TextView) convertView.findViewById(R.id.nome)).setText(c.getNome());

        return convertView;
    }
}
