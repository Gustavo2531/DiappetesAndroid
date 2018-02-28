package com.example.gustavomendez.diappetes;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecomenFragment extends Fragment {

    private TextView textViewRecomen;
    public RecomenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_recomen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        textViewRecomen = (TextView) view.findViewById(R.id.textRecomen);
        textViewRecomen.setText("Estas abajo del limite de glucosa, tienes un descuento!! Felicidades!!");


    }


}
