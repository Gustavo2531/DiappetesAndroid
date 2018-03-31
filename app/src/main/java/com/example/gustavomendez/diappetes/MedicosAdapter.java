package com.example.gustavomendez.diappetes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gustavomendez on 31/03/18.
 */

public class MedicosAdapter extends ArrayAdapter<Medicos> {
    private Context context;
    public MedicosAdapter(@NonNull Context context, int resource, @NonNull List<Medicos> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.medicos_f, parent, false);
        }
        Medicos match = getItem(position);
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewDoctores);
        //Log.d("score", "Error: " + match.nombre);
        nombre.setText("Dr./a "+match.nombre +" "+match.apellido );
        //equipo01.setText(match.birth_year);

        //convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }
}