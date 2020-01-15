package com.example.finalproject.ARRAYADAPTER;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalproject.PAVILHAO.Pavilhao_Model;
import com.example.finalproject.R;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Pavilhao_Model>
{
    public MyArrayAdapter(Context context, ArrayList<Pavilhao_Model> contacts)
    {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Pavilhao_Model c = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row, parent, false);
        }
//        ((TextView) convertView.findViewById(R.id.id)).setText(c.getID());
        ((TextView) convertView.findViewById(R.id.nome)).setText(c.getNOME());

        ((TextView) convertView.findViewById(R.id.rua)).setText(c.getRUA());
        ((TextView) convertView.findViewById(R.id.localidade)).setText(c.getLOCALIDADE());
        ((TextView) convertView.findViewById(R.id.distancia)).setText(c.getDISTANCIA());



        return convertView;
    }
}

