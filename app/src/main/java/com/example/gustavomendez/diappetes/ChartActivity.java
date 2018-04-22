package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChartActivity extends Activity {
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    Date hoy;
    Date lTomorrow;
    Date hoy2= new Date();
    Date hoy3= new Date();
    Date hoy4= new Date();
    Date hoy5= new Date();
    Date hoy6= new Date();
    Date hoy7= new Date();
    Date hoy8= new Date();
    private float calorias=0;
    private float calorias2=0;
    private float calorias4=0;
    private float calorias3=0;
    private float calorias5=0;
    private float calorias6=0;
    private float calorias7=0;
    private float calorias8=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //System.out.println(sdf.format(now.getTime()));
        cal.set(Calendar.HOUR_OF_DAY, 0);

        hoy = cal.getTime();

        lTomorrow = new Date();
        lTomorrow.setDate(hoy.getDate() + 1);
        chart = (BarChart) findViewById(R.id.chart1);

       calorias= (Float) getIntent().getSerializableExtra("cal1");
        calorias2= (Float) getIntent().getSerializableExtra("cal2");
        calorias3= (Float) getIntent().getSerializableExtra("cal3");
        calorias4= (Float) getIntent().getSerializableExtra("cal4");
        calorias5= (Float) getIntent().getSerializableExtra("cal5");
        calorias6= (Float) getIntent().getSerializableExtra("cal6");
        calorias7= (Float) getIntent().getSerializableExtra("cal7");
        calorias8 =(Float) getIntent().getSerializableExtra("cal8");
       // getData();


        //hoy.set(hoy.getDate()-1);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        //AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();
        data();
        //Bardataset = new BarDataSet(BARENTRY, "Projects");

        // BARDATA = new BarData();

        // BarData(BarEntryLabels, Bardataset);


        //Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        //chart.setData(BARDATA);

        chart.animateY(3000);
    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add(""+hoy8.getDay());
        BarEntryLabels.add(""+hoy7.getDay());
        BarEntryLabels.add(""+hoy6.getDay());
        BarEntryLabels.add(""+hoy5.getDay());
        BarEntryLabels.add(""+hoy4.getDay());
        BarEntryLabels.add(""+hoy3.getDay());
        BarEntryLabels.add(""+hoy2.getDay());
        BarEntryLabels.add(""+hoy.getDay());


    }

    public ArrayList<BarEntry> yValues(){
        ArrayList<BarEntry> list = new ArrayList<>();
        list.add(new BarEntry(0,calorias8));
        list.add(new BarEntry (1,calorias7));
        list.add(new BarEntry (2,calorias6));
        list.add(new BarEntry (3,calorias5));
        list.add(new BarEntry (4,calorias4));
        list.add(new BarEntry (5,calorias3));
        list.add(new BarEntry (6,calorias2));
        list.add(new BarEntry (7,calorias));
        System.out.println("Estas son "+calorias);
        System.out.println(calorias2);


        return list;
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
                        calorias=calorias+Float.parseFloat( h.get("quantity").toString());
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
                    data();
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
    public void data(){
        //ArrayList<String> xData = xValues();
        ArrayList<BarEntry> yData = yValues();
        BarDataSet set = new BarDataSet(yData, "data One");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        //set.setCircleColor(Color.BLACK);
        //set.setLineWidth(2f);
        //set.setCircleRadius(3f);
        //set.setDrawCircleHole(true);
        set.setValueTextSize(10f);
       // set.setDrawFilled(true);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);


        BarData lineData = new BarData( dataSets);

        chart.setData(lineData);


    }

}
