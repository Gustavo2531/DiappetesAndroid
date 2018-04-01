package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CitaActivity extends Activity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView, timeView, nomMed;
    private int year, month, day;
    private boolean r=false;
    private boolean l=false;
    private String medName, medId, medLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
        nomMed = (TextView) findViewById(R.id.textViewCitaNomMed);
        dateView = (TextView) findViewById(R.id.textView10);
        timeView = (TextView) findViewById(R.id.textView11);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        medName = (String)getIntent().getSerializableExtra("medName");
        medLastName = (String) getIntent().getSerializableExtra("medCal");
        medId =(String) getIntent().getSerializableExtra("medId");

        nomMed.setText("Dr./a "+medName+" "+medLastName);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }
    public void onButtonClicked(View v){
        r=true;
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        l=true;
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }
    public void doIt(View view){
        if(r==true&&l==true){
            String date=dateView.getText().toString();
            date.concat("T"+timeView.getText().toString()+":08.598Z");
        }else{
            Toast.makeText(getApplicationContext(), "Tienes que elegir el día y la hora",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {

        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

}
