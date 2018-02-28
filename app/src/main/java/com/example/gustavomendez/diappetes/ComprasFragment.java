package com.example.gustavomendez.diappetes;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComprasFragment extends Fragment {
    private WebView webView3;

    public ComprasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compras, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        webView3 = (WebView) view.findViewById(R.id.webView3);
        webView3.loadUrl("https://www.farmaciasanpablo.com.mx/es/MXN/Todas-las-Categor%C3%ADas/Medicamentos/c/06");


    }

}
