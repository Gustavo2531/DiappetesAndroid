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

/**
 * Created by gustavomendez on 28/02/18.
 */

public class AlimentosAdapter extends ArrayAdapter<Alimentos> {
    private Context context;
    public AlimentosAdapter(@NonNull Context context, int resource, @NonNull List<Alimentos> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.alimentos_f, parent, false);
        }
        Alimentos match = getItem(position);
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewAlim);

        nombre.setText(match.nombre);
        //equipo01.setText(match.birth_year);

        //convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }
}


