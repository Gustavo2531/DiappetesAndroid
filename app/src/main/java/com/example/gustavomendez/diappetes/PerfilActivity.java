package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class PerfilActivity extends Activity {
    private EditText maxCal;
    private EditText maxAyuno;
    private EditText maxPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        maxCal=(EditText) findViewById(R.id.editTextMaxCal);
        maxAyuno=(EditText) findViewById(R.id.editTextMaxAyu);
        maxPost=(EditText) findViewById(R.id.editTextMaxPost);



    }

    public void Agregar(View view){
    if(!maxCal.getText().equals("")&& !maxAyuno.getText().equals("") && !maxPost.getText().equals("")) {

        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            user.put("maxCal", maxCal.getText().toString());
            user.put("maxAyuno", maxAyuno.getText().toString());
            user.put("maxPost", maxPost.getText().toString());
            Toast.makeText(this,"Maximos Guardados", Toast.LENGTH_LONG).show();
            user.saveInBackground();
        }
    }
        Toast.makeText(this,"Agrega todos los maximos", Toast.LENGTH_LONG).show();

    }
}
