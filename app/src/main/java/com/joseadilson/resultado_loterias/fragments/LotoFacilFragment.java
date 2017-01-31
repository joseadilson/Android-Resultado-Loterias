package com.joseadilson.resultado_loterias.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.joseadilson.resultado_loterias.R;
import com.joseadilson.resultado_loterias.extras.SlidingTabLayout;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LotoFacilFragment extends Fragment {

    private SlidingTabLayout mSlidingTabLayout;
    private TextView concurso;
    private TextView data;
    private TextView valorAcumulado;
    private TextView sorteiEspecial;

    private TextView local;
    private TextView cidadeEuf;

    private TextView ganhadores;

    private TextView ordemSorteio;

    private TextView proximoData;
    private TextView proximoEstimativa;

    public LotoFacilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_loto_facil, container, false);

        concurso       = (TextView)view.findViewById(R.id.tvConcursoLotoFacil);
        data           = (TextView)view.findViewById(R.id.tvDataLotoFacil);
        sorteiEspecial = (TextView)view.findViewById(R.id.tvSorteiEspecial);

        local     = (TextView)view.findViewById(R.id.tvLocal);
        cidadeEuf = (TextView)view.findViewById(R.id.tvCidadeUF);

        ganhadores = (TextView)view.findViewById(R.id.tvGanhadores);

        ordemSorteio = (TextView)view.findViewById(R.id.tvOrdemSorteio);

        proximoData      = (TextView)view.findViewById(R.id.tvProximoData);
        proximoEstimativa = (TextView)view.findViewById(R.id.tvProximoEstimativa);


        String urlLotoFacil = "https://api.vitortec.com/loterias/lotofacil/v1.2/";
        Ion.with(this)
                .load(urlLotoFacil)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e == null) {

                            JsonObject objectData             = result.get("data").getAsJsonObject();
                            JsonObject objectRealizacao       = objectData.get("realizacao").getAsJsonObject();
                            final JsonObject objectResultado  = objectData.get("resultado").getAsJsonObject();
                            final JsonObject objectProximo    = objectData.get("proximoConcurso").getAsJsonObject();

                            try {
                                String mConcurso         = objectData.get("concurso").toString().replace( "\"" ,"");
                                String mData             = objectData.get("data").toString().replace( "\"" ,"");
                                String mAcumuladoSorEspe = objectData.get("acumuladoSorteioEspecial").toString().replace( "\"" ,"");

                                concurso.setText("Consurso " +  mConcurso);
                                data.setText(mData);
                                sorteiEspecial.setText("Acumulado para Sorteio\nEspecial "+ "R$"+mAcumuladoSorEspe);

                            } catch (Exception e1) { }


                            try {
                                String mLocal  = objectRealizacao.get("local").toString().replace( "\"" ,"");
                                String mCidade = objectRealizacao.get("cidade").toString().replace( "\"" ,"");
                                String mUF     = objectRealizacao.get("uf").toString().replace( "\"" ,"");

                                local.setText(mLocal);
                                cidadeEuf.setText("Realizado "+mCidade +"-"+mUF);

                            }catch (Exception e2) {}

                            JsonArray jsonArray = objectResultado.getAsJsonArray("ordemCrescente").getAsJsonArray();
                            String mGanhadores = jsonArray.toString();
                            ganhadores.setText(mGanhadores.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));

                            ordemSorteio.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    JsonArray jsonArray1 = objectResultado.getAsJsonArray("ordemSorteio").getAsJsonArray();
                                    String mGanhadoresOrdemSorteio = jsonArray1.toString();
                                    ganhadores.setText(mGanhadoresOrdemSorteio.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
                                    ordemSorteio.setText("Ver números em orderm crescente");

                                    ordemSorteio.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            JsonArray jsonArray = objectResultado.getAsJsonArray("ordemCrescente").getAsJsonArray();
                                            String mGanhadores = jsonArray.toString();
                                            ganhadores.setText(mGanhadores.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
                                            ordemSorteio.setText("Ver números na ordem do sorteio");
                                        }
                                    });
                                }
                            });

                            try {

                                String mProData       = objectProximo.get("data").toString().replace( "\"" ,"");
                                String mProEstimativa = objectProximo.get("estimativa").toString().replace( "\"" ,"");

                                proximoData.setText("Estimativa de prêmio do próximo\nconcurso "+mProData);
                                proximoEstimativa.setText("R$"+mProEstimativa);

                            } catch (Exception e3) { }

                       }
                    }
                });

        return view;
    }


}
