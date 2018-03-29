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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlucosaPostFragment extends Fragment {

    private CalendarView calendarViewgp;
    private EditText editTextgluP;
    private Context context;
    public GlucosaPostFragment() {
        // Required empty public constructor
    }

    public void IngresarGlu(View view){
        ParseUser currentUser = ParseUser.getCurrentUser();
        String currentUserString = String.valueOf(currentUser.getUsername());
        ParseObject glucoseP = new ParseObject("GlucosePostrPrandial");
        glucoseP.put("date", calendarViewgp.getDate());
        glucoseP.put("userId", currentUserString);
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

        calendarViewgp = (CalendarView) view.findViewById(R.id.datePickerP);
        editTextgluP = (EditText) view.findViewById(R.id.editTextGlucoP);

       // imageView= (ImageView) view.findViewById(R.id.imageView2);
        //textView = (TextView) view.findViewById(R.id.textView3);

    }

}
