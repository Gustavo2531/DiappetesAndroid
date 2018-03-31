package com.example.gustavomendez.diappetes;



import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link ListFragment} subclass.
 */
public class MedicosFragment extends ListFragment {

    private MedicosAdapter adapter;
    public MedicosFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        adapter = new MedicosAdapter(getActivity(), R.layout.medicos_f, new ArrayList<Medicos>());
        ParseQuery<ParseUser> query = ParseQuery.getUserQuery();
        query.whereEqualTo("isDoctor", true);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> scoreList, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Error: " + scoreList.size());
                    for(int i= 0; i<scoreList.size(); i++){
                        ParseUser h =scoreList.get(i);
                        Medicos m = new Medicos();
                        m.nombre = h.get("name").toString();
                        m.apellido = h.get("apellido").toString();
                        m.medicoId = h.getObjectId().toString();

                        Log.d("score", "Error: " + m.nombre);
                        adapter.add(m);




                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        setListAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicos, container, false);
    }

}
