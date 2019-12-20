package com.example.finalproject.ARRAYADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalproject.ESCALAO.Escalao_Model;
import com.example.finalproject.R;

import java.util.ArrayList;

public class MyArrayAdapterEscalao extends ArrayAdapter<Escalao_Model> {

    public MyArrayAdapterEscalao(Context context, ArrayList<Escalao_Model> contacts)
    {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Escalao_Model c = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.linha_prova, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.id)).setText(c.getID());
        ((TextView) convertView.findViewById(R.id.nome)).setText(c.getNOME());

        return convertView;
    }
}
