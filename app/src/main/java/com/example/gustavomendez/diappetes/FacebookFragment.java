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
public class FacebookFragment extends Fragment {

    private WebView webView4;
    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facebook, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        webView4 = (WebView) view.findViewById(R.id.webView4);
        webView4.loadUrl("https://www.facebook.com");

    }

}
