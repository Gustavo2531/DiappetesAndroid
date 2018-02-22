package com.example.gustavomendez.diappetes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {

    private WebView webView2;

    public TipsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false);

        //webView.getSettings().setJavaScriptEnabled(true);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        webView2 = (WebView) view.findViewById(R.id.webView2);
        webView2.loadUrl("http://fundacioncarlosslim.org/?s=diabetes");


    }

}
