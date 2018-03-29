package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

public class DetailAlimenActivity extends Activity {
    private Spinner spinner;
    private EditText editTextAlim;
    private TextView textViewNomAlim;
    private  Double alimCal;
    private  String alimName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alimen);
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextAlim = (EditText) findViewById(R.id.editTextNameAlim);
        textViewNomAlim = (TextView) findViewById(R.id.textViewNameAlimD);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        alimName = (String)getIntent().getSerializableExtra("alimName");
        alimCal = (Double)getIntent().getSerializableExtra("alimCal");
        textViewNomAlim.setText(alimName);
        spinner.setAdapter(adapter);

        //spinner.getOnItemSelectedListener();
    }

    public void IngresarAlim(View view){
        int x = spinner.getSelectedItemPosition();
        alimCal = alimCal * Double.parseDouble(editTextAlim.getText().toString());
        switch (x){
            case 0:
               alimCal=alimCal*10;
                break;
            case 1:
                alimCal=alimCal;
                break;
            case 2:
                alimCal=alimCal/10;
                break;
            case 3:
                alimCal=alimCal/100;
                break;
        }
        ParseUser currentUser = ParseUser.getCurrentUser();
        String currentUserString = String.valueOf(currentUser.getUsername());
        Date date = new Date();
        ParseObject glucoseP = new ParseObject("Food");
        glucoseP.put("food", alimName);
        glucoseP.put("userId", currentUserString);
        glucoseP.put("date", date);
        glucoseP.put("quantity", alimCal);
        glucoseP.saveInBackground();

    }
}
