package com.example.gustavomendez.diappetes;


import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Response;

import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link ListFragment} subclass.
 */
public class AlimenFragment extends ListFragment{
    private RequestQueue mQueue;
    private AlimentosAdapter adapter;
    private Context context;
    public AlimenFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
         adapter = new AlimentosAdapter(getActivity(), R.layout.alimentos_f, new ArrayList<Alimentos>());
        mQueue= VolleySingleton.getInstance(getContext()).getRequestQueue();
            String h = "https://intense-river-47104.herokuapp.com/foods/json?query=%22%22";
            jsonMarvel(h, adapter);


        setListAdapter(adapter);
        //setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
            //public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               /** MarvelDude nd = marvelAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this,   DetailActivity.class);
                intent.putExtra("ndid", nd.id);
                startActivity(intent);
                //
            }
        });**/

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Alimentos m = adapter.getItem(position);
        Intent intent = new Intent( getActivity(),   DetailAlimenActivity.class);
        intent.putExtra("alimName", m.nombre);
        intent.putExtra("alimCal", m.calorias);
        startActivity(intent);
        //        Toast.makeText(context,m.nombre, Toast.LENGTH_LONG).show();

    }

    public void jsonMarvel(String url, final AlimentosAdapter adapter){
        final JsonArrayRequest request =  new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    //System.out.println("Entre");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Alimentos m = new Alimentos();
                        m.nombre = jsonObject.getString("Nombre");
                        m.calorias = (jsonObject.getDouble("Carbohidratos")*4)+(jsonObject.getDouble("Grasa")*9)+(jsonObject.getDouble("Proteina")*4);


                        adapter.add(m);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alimen, container, false);
    }

}
