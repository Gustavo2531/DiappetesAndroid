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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlucosaPostFragment extends Fragment {

    private TextView calendarViewgp;
    private EditText editTextgluP;
    private Button button3;
    private Context context;
    public GlucosaPostFragment() {
        // Required empty public constructor
    }

    public void IngresarGlucosaPost(View view){
        ParseUser currentUser = ParseUser.getCurrentUser();
        String currentUserString = String.valueOf(currentUser.getUsername());
        ParseObject glucoseP = new ParseObject("GlucosePostrPrandial");
        //glucoseP.put("date", calendarViewgp.getMinDate());
        glucoseP.put("userId", currentUser.getObjectId());
        glucoseP.put("quantity", Double.parseDouble(editTextgluP.getText().toString()));
        glucoseP.saveInBackground();

        Toast.makeText(getActivity().getBaseContext(),"Glucosa Guardada", Toast.LENGTH_LONG).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_glucosa_post, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        calendarViewgp = (TextView) view.findViewById(R.id.textViewDiaG);
        editTextgluP = (EditText) view.findViewById(R.id.editTextGlucoP);

            button3 = (Button) view.findViewById(R.id.button2);
            Date d = new Date();
            calendarViewgp.setText(d.toString());
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date d = new Date();
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    String currentUserString = String.valueOf(currentUser.getUsername());
                    ParseObject glucoseP = new ParseObject("GlucosePostrPrandial");

                    glucoseP.put("date", d);
                    glucoseP.put("userId", currentUser.getObjectId());
                    glucoseP.put("quantity", Double.parseDouble(editTextgluP.getText().toString()));
                    glucoseP.saveInBackground();

                    Toast.makeText(getActivity().getBaseContext(),"Glucosa Guardada", Toast.LENGTH_LONG).show();
                }
            });
            // imageView= (ImageView) view.findViewById(R.id.imageView2);
            //textView = (TextView) view.findViewById(R.id.textView3);

        }

}
