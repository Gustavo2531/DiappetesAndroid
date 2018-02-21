package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHistorial(View view){
        Intent it = new Intent( MainActivity.this, HistorialAcademicoActivity.class);
        startActivity(it);
    }

    public void clickMedicamentos(View view){
        Intent it = new Intent( MainActivity.this, MedicamentosActivity.class);
        startActivity(it);
    }
}