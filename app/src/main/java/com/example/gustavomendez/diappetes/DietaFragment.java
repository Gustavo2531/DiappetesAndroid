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
public class DietaFragment extends Fragment {
    private TextView desayunoUno, desayunoDos, desayunoTres;
    private TextView comidaUno, comidaDos, comidaTres;
    private TextView cenaUno, cenaDos, cenaTres;


    public DietaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dieta, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        desayunoUno = (TextView) view.findViewById(R.id.desayunoUno);
        desayunoDos = (TextView) view.findViewById(R.id.desayunoDos);
        desayunoTres = (TextView) view.findViewById(R.id.desayuno3);

        comidaUno = (TextView) view.findViewById(R.id.comidaUno);
        comidaDos= (TextView) view.findViewById(R.id.comidaDos);
        comidaTres = (TextView) view.findViewById(R.id.comidaTres);

        cenaUno = (TextView) view.findViewById(R.id.cenaUno);
        cenaDos= (TextView) view.findViewById(R.id.cenaDos);
        cenaTres = (TextView) view.findViewById(R.id.cenaTres);

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dieta");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("RBSE", "The getFirst request failed.");
                } else {
                    cenaUno.setText(""+object.get("firstDinner").toString());
                    cenaDos.setText(""+object.get("secondDinner").toString());
                    cenaTres.setText(""+object.get("thirdDinner").toString());
                    comidaUno.setText(""+object.get("firstMeal").toString());
                    comidaDos.setText(""+object.get("secondMeal").toString());
                    comidaTres.setText(""+object.get("thirdMeal").toString());
                    desayunoUno.setText(""+object.get("firstBreakfast").toString());
                    desayunoDos.setText(""+object.get("secondBreakFast").toString());
                    desayunoTres.setText(""+object.get("thirdBreakFast").toString());

                    // got the most recently modified object... do something with it here
                }
            }
        });

    }

}
