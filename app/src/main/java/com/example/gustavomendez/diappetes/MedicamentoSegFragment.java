package com.example.gustavomendez.diappetes;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentoSegFragment extends Fragment {

    private TextView tiempoUno, tiempoDos, tiempoTres;
    private TextView medicamentoUno, medicamentoDos, medicamentoTres;
    private TextView medicamentoCuatro, medicamentoCinco, tiempoCinco, tiempoCuatro;
    public MedicamentoSegFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicamento_seg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tiempoUno = (TextView) view.findViewById(R.id.textTiempoUno);
        tiempoDos = (TextView) view.findViewById(R.id.textTiempoDos);
        tiempoTres = (TextView) view.findViewById(R.id.textTiempoTres);

        tiempoCinco = (TextView) view.findViewById(R.id.textTiempoCinco);
        tiempoCuatro= (TextView) view.findViewById(R.id.textTiempoCuatro);
        medicamentoCuatro = (TextView) view.findViewById(R.id.textMedicamentoCuatro);

        medicamentoUno = (TextView) view.findViewById(R.id.textMedicamentoUno);
        medicamentoDos= (TextView) view.findViewById(R.id.textMedicamentoDos);
        medicamentoTres = (TextView) view.findViewById(R.id.textMedicamentoTres);
        medicamentoCinco = (TextView) view.findViewById(R.id.textMedicamentoCinco);

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Recetas");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {

                if (object == null) {
                    Log.d("RBSE", "The getFirst request failed.");
                } else {
                    System.out.println(object.get("Medicamento1").toString());
                    if(object.get("HoraMedicamento1").toString().equals("")){
                        tiempoUno.setText("");
                        medicamentoUno.setText(" ");

                    }else{
                        System.out.println("Entre");
                        tiempoUno.setText("Cada "+object.get("HoraMedicamento1").toString() + " Hora por " +object.get("DiaMedicamento1").toString() +" día/s");
                        medicamentoUno.setText(" "+object.get("Medicamento1").toString()  );
                    }
                    if(!object.get("HoraMedicamento2").toString().equals("")){
                        tiempoDos.setText("Cada "+object.get("HoraMedicamento2").toString() + " Hora por " +object.get("DiaMedicamento2").toString() +" día/s");
                        medicamentoDos.setText(" "+object.get("Medicamento2").toString()  );
                    }else{
                        tiempoDos.setText("");
                        medicamentoDos.setText(" ");
                    }
                    if(!object.get("HoraMedicamento3").toString().equals("")){
                        tiempoTres.setText("Cada "+object.get("HoraMedicamento3").toString() + " Hora por " +object.get("DiaMedicamento3").toString() +" día/s");
                        medicamentoTres.setText(" "+object.get("Medicamento3").toString()  );
                    }
                    else{
                        tiempoTres.setText("");
                        medicamentoTres.setText(" ");
                    }
                    if(!object.get("HoraMedicamento4").toString().equals("")){
                        tiempoCuatro.setText("Cada "+object.get("HoraMedicamento4").toString() + " Hora por " +object.get("DiaMedicamento4").toString() +" día/s");
                        medicamentoCuatro.setText(" "+object.get("Medicamento4").toString()  );
                    }
                    else{
                        tiempoCuatro.setText("");
                        medicamentoCuatro.setText(" ");
                    }
                    if(!object.get("HoraMedicamento5").toString().equals("")){
                        tiempoCinco.setText("Cada "+object.get("HoraMedicamento5").toString() + " Hora por " +object.get("DiaMedicamento5").toString() +" día/s");
                        medicamentoCinco.setText(" "+object.get("Medicamento5").toString()  );
                    }
                    else{
                        tiempoCinco.setText("");
                        medicamentoCinco.setText(" ");
                    }


                }
            }
        });

    }

}
