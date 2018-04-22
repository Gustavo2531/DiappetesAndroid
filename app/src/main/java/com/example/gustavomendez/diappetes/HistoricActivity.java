package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HistoricActivity extends Activity {

    private HistoricAdapter adapter;
    private  ListView list;
    int count =0;
    final private  ArrayList<String> gluA = new ArrayList<>();
    final private ArrayList<String> gluDA = new ArrayList<>();
    final private ArrayList<String> gluDP = new ArrayList<>();
    final private ArrayList<String> gluP = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        list = (ListView) findViewById(R.id.listViewHistoric);

        adapter = new HistoricAdapter(this, R.layout.historic_layout, new ArrayList<Historic>());
        load();



        list.setAdapter(adapter);


    }

    private void load(){

        final ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GlucosePostrPrandial");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList.size(); i++){

                        ParseObject h =scoreList.get(i);
                        gluDP.add(" "+h.get("date").toString());
                        gluP.add(" "+h.get("quantity").toString());
                        System.out.println(gluDP.get(i));


                    }
                    adapter.notifyDataSetChanged();
                    intermedio();
                    //data();
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Glucose");
        query1.whereEqualTo("userId", currentUser.getObjectId());
        query1.orderByDescending("createdAt");
        query1.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList2, ParseException e) {
                if (e == null) {
                    for(int i= 0; i<scoreList2.size(); i++){

                        ParseObject h2 =scoreList2.get(i);
                        gluDA.add(" "+h2.get("date").toString());
                        gluA.add(" "+h2.get("quantity").toString());


                    }
                    adapter.notifyDataSetChanged();
                   intermedio();
                    //data();
                    // calDia.setText(" "+calorias);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });




    }

    private void intermedio(){
        count=count+1;
        if(count==2){
            loadChange();
        }
    }

    private void loadChange(){
        if(gluP.size()>gluA.size()){
            for(int i=0; i<gluP.size(); i++){
                Historic m = new Historic();
                m.glucosaPDia= gluDP.get(i);
                m.glucosaPost =  gluP.get(i);
                System.out.println("Aquie que pox"+gluDP.get(i));
                if(i>=gluA.size()){
                    m.glucosaADia = " ";
                    m.glucosaAyuno = "   ";
                }else{
                    m.glucosaADia = gluDA.get(i);
                    m.glucosaAyuno = gluA.get(i);
                }

                adapter.add(m);
            }
            adapter.notifyDataSetChanged();
        }else if (gluP.size()<gluA.size()){
            for(int i=0; i<gluA.size(); i++){
                Historic m = new Historic();
                m.glucosaADia = gluDA.get(i);
                m.glucosaAyuno = gluA.get(i);
                System.out.println("Aquie que pox"+gluA.get(i));
                if(i>=gluP.size()){
                    m.glucosaPDia = " ";
                    m.glucosaPost = "   ";
                }else{
                    m.glucosaPDia= gluDP.get(i);
                    m.glucosaPost =  gluP.get(i);
                }

                adapter.add(m);
            }
            adapter.notifyDataSetChanged();
        }else{
            for(int i=0; i<gluA.size(); i++){
                System.out.println("Aquie que pox"+gluA.get(i));
                Historic m = new Historic();
                m.glucosaADia = gluDA.get(i);
                m.glucosaAyuno = gluA.get(i);
                m.glucosaPDia= gluDP.get(i);
                m.glucosaPost =  gluP.get(i);


                adapter.add(m);
            }
            adapter.notifyDataSetChanged();
        }
        list.setAdapter(adapter);
    }
}
