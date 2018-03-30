package com.example.gustavomendez.diappetes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeguimientoFragment extends Fragment {

    private double calorias=0;
    private double caloriasS=0;
    private double caloriasM=0;
    private TextView calDia;
    private TextView calSem;
    private TextView calMes;
    public SeguimientoFragment() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seguimiento, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        calDia =(TextView)view.findViewById(R.id.caloriasNumTextDia);
        calSem =(TextView) view.findViewById(R.id.caloriasNumTextSem);
        calMes = (TextView) view.findViewById(R.id.caloriasNumTextMes);
        //calendarViewgp = (CalendarView) view.findViewById(R.id.datePickerP);
        //editTextgluP = (EditText) view.findViewById(R.id.editTextGlucoP);

        // imageView= (ImageView) view.findViewById(R.id.imageView2);
        //textView = (TextView) view.findViewById(R.id.textView3);

        Date hoy = new Date();
        Date lTomorrow = new Date();
        lTomorrow.setDate(hoy.getDate() + 1);
        hoy.setDate(hoy.getDate()-1);
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.whereGreaterThanOrEqualTo("createdAt", hoy);
        query.whereLessThan("createdAt", lTomorrow);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias=calorias+Double.parseDouble( h.get("quantity").toString());

                    }
                    calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        Date week = new Date();
        week.setDate(hoy.getDate() - 7);
        Date today = new Date();
        today.setDate(hoy.getDate()+1);
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Food");
        query2.whereEqualTo("userId", currentUser.getObjectId());
        query2.orderByDescending("createdAt");
        query2.whereGreaterThanOrEqualTo("createdAt", week);
        query2.whereLessThan("createdAt", today);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        caloriasS=caloriasS+Double.parseDouble( h.get("quantity").toString());

                    }
                    calSem.setText(" "+caloriasS);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        Date month = new Date();
        month.setDate(hoy.getDate() - 30);
        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Food");
        query3.whereEqualTo("userId", currentUser.getObjectId());
        query3.orderByDescending("createdAt");
        query3.whereGreaterThanOrEqualTo("createdAt", month);
        query3.whereLessThan("createdAt", today);
        query3.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        caloriasM=caloriasM+Double.parseDouble( h.get("quantity").toString());

                    }
                    calMes.setText(" "+caloriasM);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
}
