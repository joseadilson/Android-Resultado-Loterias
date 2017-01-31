package com.joseadilson.resultado_loterias.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.joseadilson.resultado_loterias.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuplaSenaFragment extends Fragment {
    private TextView concurso;
    private TextView data;

    public DuplaSenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_dupla_sena, container, false);

        concurso = (TextView)view.findViewById(R.id.tvConcursoDuplaSena);
        data     = (TextView)view.findViewById(R.id.tvDataDuplaSena);

        String urlDuplaSena = "https://api.vitortec.com/loterias/duplasena/v1.2/";
        Ion.with(this)
                .load(urlDuplaSena)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e == null) {

                            JsonObject objectData = result.get("data").getAsJsonObject();

                            try {
                                concurso.setText("Consurso "+ objectData.get("concurso").toString());
                                data.setText("Data "+ objectData.get("data").toString());
                            } catch (Exception e1) {

                            }
                        }
                    }
                });


        return  view;
    }

}
