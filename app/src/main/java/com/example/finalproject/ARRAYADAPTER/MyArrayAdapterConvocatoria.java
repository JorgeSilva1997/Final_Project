package com.example.finalproject.ARRAYADAPTER;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalproject.CONVOCATORIA.Convocatoria_Model;
import com.example.finalproject.PAVILHAO.Pavilhao_Model;
import com.example.finalproject.R;

import java.util.ArrayList;

public class MyArrayAdapterConvocatoria extends ArrayAdapter<Convocatoria_Model>
{
    public MyArrayAdapterConvocatoria(Context context, ArrayList<Convocatoria_Model> contacts)
    {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Convocatoria_Model c = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_convocatoria, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.id_convocatoria)).setText(c.getId_convocatoria());
        ((TextView) convertView.findViewById(R.id.user_nome)).setText(c.getUser_nome());

        ((TextView) convertView.findViewById(R.id.equipa_visitada_nome)).setText(c.getEquipa_visitada_nome());
        ((TextView) convertView.findViewById(R.id.user_nome)).setText(c.getUser_nome());
        ((TextView) convertView.findViewById(R.id.datahora)).setText(c.getDatahora());
        ((TextView) convertView.findViewById(R.id.equipa_visitante_nome)).setText(c.getEquipa_visitante_nome());
        ((TextView) convertView.findViewById(R.id.pavilhao_nome)).setText(c.getPavilhao_nome());
        ((TextView) convertView.findViewById(R.id.escalao_nome)).setText(c.getEscalao_nome());
        ((TextView) convertView.findViewById(R.id.prova_nome)).setText(c.getProva_nome());



        return convertView;
    }
}

