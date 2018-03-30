package com.example.gustavomendez.diappetes;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlucosaAyunoFragment extends Fragment {
    private CalendarView calendarViewga;
    private EditText editTextgluA;
    private Context context;

    public GlucosaAyunoFragment() {
        // Required empty public constructor
    }

    public void IngresarGlucosaAyuno(View view){
        ParseUser currentUser = ParseUser.getCurrentUser();
        String currentUserString = String.valueOf(currentUser.getUsername());
        ParseObject glucoseP = new ParseObject("Glucose");
        glucoseP.put("date", calendarViewga.getDate());
        glucoseP.put("userId", currentUser.getObjectId());
        glucoseP.put("quantity", Double.parseDouble(editTextgluA.getText().toString()));
        glucoseP.saveInBackground();

        Toast.makeText(context,"Glucosa Guardada", Toast.LENGTH_LONG).show();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glucosa_ayuno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        calendarViewga = (CalendarView) view.findViewById(R.id.datePicker);
        editTextgluA = (EditText) view.findViewById(R.id.editTextGlucoA);

        // imageView= (ImageView) view.findViewById(R.id.imageView2);
        //textView = (TextView) view.findViewById(R.id.textView3);

    }

}
