package com.example.gustavomendez.diappetes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;

public class HistorialAcademicoActivity extends AppCompatActivity {

   // private TextView mTextMessage;
    private ImageView imageHistorial, imageGlucosaA, imageGlucosaP, imageMedico, imageMapa;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            Fragment fragment = null;
            if(id == R.id.navigation_historial){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno, new HistorialFragment());
                fragmentTransaction.commit();
                return true;
            }else if(id==R.id.navigation_glucosaa){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno, new GlucosaAyunoFragment());
                fragmentTransaction.commit();
                return true;
            }else if(id == R.id.navigation_glucosap){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno, new GlucosaPostFragment());
                fragmentTransaction.commit();
                return true;

            }else if(id ==  R.id.navigation_mapa){

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno,new MapFragment());
                fragmentTransaction.commit();
                return true;

            }else if(id == R.id.navigation_medico){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno, new MedicosFragment());
                fragmentTransaction.commit();
                return true;

            }else{
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno, new TipsFragment());
                fragmentTransaction.commit();
                return true;
            }

            /*switch (item.getItemId()) {
                case R.id.navigation_historial:
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.uno, new TipsFragment());
                    fragmentTransaction.commit();

                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_glucosaa:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_glucosap:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_mapa:

                    return true;
                case R.id.navigation_medico:

                    return true;
                default:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction =  fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.uno, new TipsFragment());
                    fragmentTransaction.commit();

            }
            return false;*/

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_academico);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.uno, new HistorialFragment());
        fragmentTransaction.commit();

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
