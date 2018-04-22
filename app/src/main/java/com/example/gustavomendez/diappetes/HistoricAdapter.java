package com.example.gustavomendez.diappetes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoricAdapter extends ArrayAdapter<Historic>  {

    private Context context;
    public HistoricAdapter(@NonNull Context context, int resource, @NonNull List<Historic> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.historic_layout, parent, false);
        }
        Historic match = getItem(position);
        TextView ayunD = (TextView) convertView.findViewById(R.id.textDiaAyunoHistoric);
        TextView postD = (TextView) convertView.findViewById(R.id.textDiaPostHistoric);
        TextView ayun = (TextView) convertView.findViewById(R.id.textAyunHistoric);
        TextView post = (TextView) convertView.findViewById(R.id.textPostHistoric);

        //Log.d("score", "Error: " + match.nombre);
        ayunD.setText(match.glucosaADia);
        postD.setText(match.glucosaPDia);
        ayun.setText(match.glucosaAyuno);
        post.setText(match.glucosaPost);
        //nombre.setText("Dr./a "+match.nombre +" "+match.apellido );
        //equipo01.setText(match.birth_year);

        //convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }
}

