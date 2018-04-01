package com.example.gustavomendez.diappetes;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlucosaAyunoFragment extends Fragment {
    private TextView calendarViewga;
    private EditText editTextgluA;
    private Button button3;
    private Context context;

    public GlucosaAyunoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glucosa_ayuno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        calendarViewga = (TextView) view.findViewById(R.id.textViewDia);
        editTextgluA = (EditText) view.findViewById(R.id.editTextGlucoA);
        button3 = (Button) view.findViewById(R.id.button4);
        Date d = new Date();
        calendarViewga.setText(d.toString());
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date d = new Date();
                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUserString = String.valueOf(currentUser.getUsername());
                ParseObject glucoseP = new ParseObject("Glucose");

                glucoseP.put("date", d);
                glucoseP.put("userId", currentUser.getObjectId());
                glucoseP.put("quantity", Double.parseDouble(editTextgluA.getText().toString()));
                glucoseP.saveInBackground();

                Toast.makeText(getActivity().getBaseContext(),"Glucosa Guardada", Toast.LENGTH_LONG).show();
            }
        });
        // imageView= (ImageView) view.findViewById(R.id.imageView2);
        //textView = (TextView) view.findViewById(R.id.textView3);

    }

    public void IngresarGlucosaAyuno(){


    }

}
