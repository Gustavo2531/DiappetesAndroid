package com.example.gustavomendez.diappetes;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {

    private TextView glucoANum;
    private TextView glucoPNum;

    public HistorialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historial, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        glucoANum = (TextView) view.findViewById(R.id.gluANumText);
        glucoPNum = (TextView) view.findViewById(R.id.glucosaPostANum);
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GlucosePostrPrandial");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {

                if (object == null) {
                    Log.d("RBSE", "The getFirst request failed.");
                } else {

                    glucoPNum.setText(""+object.get("quantity").toString());

                }
            }
        });
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Glucose");
        query2.whereEqualTo("userId", currentUser.getObjectId());
        query2.orderByDescending("createdAt");
        query2.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {

                if (object == null) {
                    Log.d("RBSE", "The getFirst request failed.");
                } else {

                    glucoANum.setText(""+object.get("quantity").toString());

                }
            }
        });

    }

}

