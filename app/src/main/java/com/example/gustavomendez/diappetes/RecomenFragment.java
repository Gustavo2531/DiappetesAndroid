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

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecomenFragment extends Fragment {

    private TextView textViewRecomen;
    private double maxPost, maxCal, maxAyuno;
    private double caloriasS=0;
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
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            maxPost=Double.parseDouble(user.get("maxPost").toString());
            maxAyuno=Double.parseDouble(user.get("maxAyuno").toString());
            maxCal=Double.parseDouble(user.get("maxCal").toString());
            prueba();
        }

    }

    private void prueba(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GlucosePostrPrandial");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {

                if (object == null) {
                    Log.d("RBSE", "The getFirst request failed.");
                } else {

                   Double m = Double.parseDouble(object.get("quantity").toString());
                   if(m<maxPost) {
                       textViewRecomen.setText(textViewRecomen.getText()+"\n\nEstas abajo del limite de glucosa PostPandrial, tienes un descuento!! Felicidades!!");
                   }else{
                       textViewRecomen.setText(textViewRecomen.getText()+"\n\nEstas arriba del limite de glucosa PostPandrial, pero no te preocupes solo cuidate");

                   }

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

                    Double m = Double.parseDouble(object.get("quantity").toString());

                    if(m<maxAyuno) {
                        textViewRecomen.setText(textViewRecomen.getText() + "\n\nEstas abajo del limite de glucosa, tienes un descuento!! Felicidades!!");
                    }else{
                        textViewRecomen.setText(textViewRecomen.getText()+"\n\nEstas arriba del limite de glucosa , pero no te preocupes solo cuidate");

                    }
                }
            }
        });

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //System.out.println(sdf.format(now.getTime()));
        cal.set(Calendar.HOUR_OF_DAY, 0);

        Date hoy = cal.getTime();

        Date month = new Date();
        month.setDate(hoy.getDate() - 30);
        Date week = new Date();
        week.setDate(hoy.getDate() - 7);
        Date today = new Date();
        today.setDate(hoy.getDate()+1);

        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Food");
        query3.whereEqualTo("userId", currentUser.getObjectId());
        query3.orderByDescending("createdAt");
        query3.whereGreaterThanOrEqualTo("createdAt", week);
        query3.whereLessThan("createdAt", today);
        query3.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        caloriasS=caloriasS+Double.parseDouble( h.get("quantity").toString());

                    }
                    if(caloriasS<maxCal) {
                        textViewRecomen.setText(textViewRecomen.getText() + "\n\nEstas abajo del limite de calorias, tienes un descuento!! Felicidades!!");
                    }else{
                        textViewRecomen.setText(textViewRecomen.getText() + "\n\nEstas arriba del limite de calorias, pero no te preocupes confiamos en ti");

                    }
                    //calSem.setText(" "+caloriasS);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }


}
