package com.example.gustavomendez.diappetes;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
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
public class SeguimientoFragment extends Fragment {

    private double calorias=0;
    private float calorias2=0;
    private float calorias1=0;
    private float calorias3=0;
    private float calorias4=0;
    private float calorias5=0;
    private float calorias6=0;
    private float calorias7=0;
    private float calorias8=0;
    Date hoy2= new Date();
    Date hoy3= new Date();
    Date hoy4= new Date();
    Date hoy5= new Date();
    Date hoy6= new Date();
    Date hoy7= new Date();
    Date hoy8= new Date();
    Date hoy;
    Date lTomorrow = new Date();
    private double caloriasS=0;
    private double caloriasM=0;
    private TextView calDia;
    private TextView calSem;
    private TextView calMes;
    private Button button1;
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
        button1 = (Button) view.findViewById(R.id.buttonSeguim);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(),   ChartActivity.class);
                intent.putExtra("cal1", calorias1);
                intent.putExtra("cal2",calorias2);
                intent.putExtra("cal3", calorias3);
                intent.putExtra("cal4", calorias4);
                intent.putExtra("cal5",calorias5);
                intent.putExtra("cal6", calorias6);
                intent.putExtra("cal7", calorias7);
                intent.putExtra("cal8",calorias8);




                startActivity(intent);
            }
            });


        //calendarViewgp = (CalendarView) view.findViewById(R.id.datePickerP);
        //editTextgluP = (EditText) view.findViewById(R.id.editTextGlucoP);

        // imageView= (ImageView) view.findViewById(R.id.imageView2);
        //textView = (TextView) view.findViewById(R.id.textView3);


        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //System.out.println(sdf.format(now.getTime()));
        cal.set(Calendar.HOUR_OF_DAY, 0);

        hoy = cal.getTime();
        lTomorrow.setDate(hoy.getDate() + 1);
     //   hoy.setDate(hoy.getDate()-1);

        getData();
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

    public void getData(){
        Date hoyP = new Date();
        hoyP.setDate(hoyP.getDate()-1);
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
                        calorias1=calorias1+Float.parseFloat( h.get("quantity").toString());
                        System.out.println(h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        // ParseUser currentUser = ParseUser.getCurrentUser();
        hoy2.setDate(hoy.getDate()-1);
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Food");
        query1.whereEqualTo("userId", currentUser.getObjectId());
        query1.orderByDescending("createdAt");
        query1.whereGreaterThanOrEqualTo("createdAt", hoy2);
        query1.whereLessThan("createdAt", hoy);
        query1.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias2=calorias2+Float.parseFloat( h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        hoy3.setDate(hoy.getDate()-2);
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Food");
        query2.whereEqualTo("userId", currentUser.getObjectId());
        query2.orderByDescending("createdAt");
        query2.whereGreaterThanOrEqualTo("createdAt", hoy3);
        query2.whereLessThan("createdAt", hoy2);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias3=calorias3+Float.parseFloat( h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        hoy4.setDate(hoy.getDate()-3);
        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Food");
        query3.whereEqualTo("userId", currentUser.getObjectId());
        query3.orderByDescending("createdAt");
        query3.whereGreaterThanOrEqualTo("createdAt", hoy4);
        query3.whereLessThan("createdAt", hoy3);
        query3.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias4=calorias4+Float.parseFloat( h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        hoy5.setDate(hoy.getDate()-4);
        ParseQuery<ParseObject> query4 = ParseQuery.getQuery("Food");
        query4.whereEqualTo("userId", currentUser.getObjectId());
        query4.orderByDescending("createdAt");
        query4.whereGreaterThanOrEqualTo("createdAt", hoy5);
        query4.whereLessThan("createdAt", hoy4);
        query4.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias5=calorias5+Float.parseFloat( h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        hoy6.setDate(hoy.getDate()-5);
        ParseQuery<ParseObject> query5 = ParseQuery.getQuery("Food");
        query5.whereEqualTo("userId", currentUser.getObjectId());
        query5.orderByDescending("createdAt");
        query5.whereGreaterThanOrEqualTo("createdAt", hoy6);
        query5.whereLessThan("createdAt", hoy5);
        query5.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias6=calorias6+Float.parseFloat( h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        hoy7.setDate(hoy.getDate()-6);
        ParseQuery<ParseObject> query6 = ParseQuery.getQuery("Food");
        query6.whereEqualTo("userId", currentUser.getObjectId());
        query6.orderByDescending("createdAt");
        query6.whereGreaterThanOrEqualTo("createdAt", hoy7);
        query6.whereLessThan("createdAt", hoy6);
        query6.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias7=calorias7+Float.parseFloat( h.get("quantity").toString());

                    }
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        hoy8.setDate(hoy.getDate()-7);
        ParseQuery<ParseObject> query7 = ParseQuery.getQuery("Food");
        query7.whereEqualTo("userId", currentUser.getObjectId());
        query7.orderByDescending("createdAt");
        query7.whereGreaterThanOrEqualTo("createdAt", hoy8);
        query7.whereLessThan("createdAt", hoy7);
        query7.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseObject h =scoreList.get(i);
                        calorias8=calorias8+Float.parseFloat( h.get("quantity").toString());

                    }
                    //data();
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
}
