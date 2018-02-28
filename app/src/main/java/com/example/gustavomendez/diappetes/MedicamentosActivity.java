package com.example.gustavomendez.diappetes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MedicamentosActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            Fragment fragment = null;
            if(id == R.id.navigation_medicion){
                /*FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.uno, new TipsFragment());
                fragmentTransaction.commit();
                return true;*/
            }else if(id==R.id.navigation_facebook){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.medicaFrame, new FacebookFragment());
                fragmentTransaction.commit();
                // mTextMessage.setText(R.string.title_dashboard);
                return true;
            }else if(id == R.id.navigation_compras){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.medicaFrame, new ComprasFragment());
                fragmentTransaction.commit();
                //mTextMessage.setText(R.string.title_notifications);
                return true;

            }else{
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.medicaFrame, new ComprasFragment());
                fragmentTransaction.commit();
                //mTextMessage.setText(R.string.title_notifications);
                return true;
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
