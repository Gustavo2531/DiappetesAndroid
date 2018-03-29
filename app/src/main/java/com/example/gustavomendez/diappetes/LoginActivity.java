package com.example.gustavomendez.diappetes;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText editTextName;
    private EditText editTextPass;
    private EditText editTextNom;
    private EditText editTextLastName;
    private Button buttonLS;
    private boolean r = true;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this);

        editTextName = (EditText) findViewById(R.id.editTextNameL);
        editTextPass = (EditText) findViewById(R.id.editTextPassL);
        editTextNom = (EditText) findViewById(R.id.editTextName1L);
        editTextLastName = (EditText) findViewById(R.id.editTextName2L);
        buttonLS = (Button) findViewById(R.id.buttonRegS);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("name")){

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{

        }
    }

    public void CambioL(View view){
        if(r==true){
            editTextNom.setVisibility(View.VISIBLE);
            editTextLastName.setVisibility(View.VISIBLE);
            buttonLS.setText("Loggearte");
            r=false;

        }else{
            editTextNom.setVisibility(View.GONE);
            editTextLastName.setVisibility(View.GONE);
            buttonLS.setText("Registrarse");
            r=true;
        }


    }
    public void Login(View view) {
        String userName=editTextName.getText().toString();
        String pass =editTextPass.getText().toString();
        if(r==true) {
            ParseUser.logInInBackground(userName, pass, new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        String name = editTextName.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",name);
                        editor.commit();
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                        // Hooray! The user is logged in.
                    } else {
                        Toast.makeText(getApplicationContext(),"Contraseña o Usuario incorrecto", Toast.LENGTH_LONG).show();
                        // Signup failed. Look at the ParseException to see what happened.
                    }
                }
            });
        }else{
            ParseUser user = new ParseUser();
            user.setUsername(userName);
            user.setPassword(pass);
            user.put("isDoctor", false);
            user.put("name", editTextNom.getText().toString());
            user.put("apellido", editTextLastName.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        String name = editTextName.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",name);
                        editor.commit();
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                        // Hooray! Let them use the app now.
                    } else {
                        Toast.makeText(getApplicationContext(),"Usuario ya creado", Toast.LENGTH_LONG).show();

                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });

        }
    }
}
