package com.joseadilson.resultado_loterias.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
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
    private TextView valorAcumulado;
    private TextView sorteiEspecial;

    private TextView local;
    private TextView cidadeEuf;

    private TextView ganhadores;
    private TextView ordemSorteio;

    private TextView ganhadoresSegundo;
    private TextView ordemSorteioSegundo;

    private TextView proximoData;
    private TextView proximoEstimativa;

    public DuplaSenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_dupla_sena, container, false);

        concurso                 = (TextView)view.findViewById(R.id.tvConcursoDuplaSena);
        data                     = (TextView)view.findViewById(R.id.tvDataDuplaSena);
        sorteiEspecial           = (TextView)view.findViewById(R.id.tvSorteiEspecialDuplaSena);

        local                    = (TextView)view.findViewById(R.id.tvLocalDuplaSena);
        cidadeEuf                = (TextView)view.findViewById(R.id.tvCidadeUFDuplaSena);

        ganhadores               = (TextView)view.findViewById(R.id.tvGanhadoresDuplaSena);
        ordemSorteio             = (TextView)view.findViewById(R.id.tvOrdemSorteioDuplaSena);

        ganhadoresSegundo        = (TextView)view.findViewById(R.id.tvGanhadoresSegundoDuplaSena);
        ordemSorteioSegundo      = (TextView)view.findViewById(R.id.tvOrdemSegundoSorteioDuplaSena);

        proximoData              = (TextView)view.findViewById(R.id.tvProximoDataDuplaSena);
        proximoEstimativa        = (TextView)view.findViewById(R.id.tvProximoEstimativaDuplaSena);



//        String urlDuplaSena = "https://api.vitortec.com/loterias/duplasena/v1.2/";
//        Ion.with(getActivity())
//                .load(urlDuplaSena)
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonObject result) {
//                        if (e == null) {
//
//                            JsonObject objectData             = result.get("data").getAsJsonObject();
//                            JsonObject objectRealizacao       = objectData.get("realizacao").getAsJsonObject();
//                            final JsonObject objectResultado  = objectData.get("resultado").getAsJsonObject();
//                            final JsonObject objectProximo    = objectData.get("proximoConcurso").getAsJsonObject();
//
//
//                            try {
//                                String mConcurso         = objectData.get("concurso").toString().replace( "\"" ,"");
//                                String mData             = objectData.get("data").toString().replace( "\"" ,"");
//                                String mAcumuladoSorEspe = objectData.get("acumuladoSorteioEspecial").toString().replace( "\"" ,"");
//
//                                concurso.setText("Consurso " +  mConcurso);
//                                data.setText(mData);
//                                sorteiEspecial.setText("Acumulado para Sorteio\nEspecial "+ "R$"+mAcumuladoSorEspe);
//
//                            } catch (Exception e1) { }
//
//
//                            try {
//                                String mLocal  = objectRealizacao.get("local").toString().replace( "\"" ,"");
//                                String mCidade = objectRealizacao.get("cidade").toString().replace( "\"" ,"");
//                                String mUF     = objectRealizacao.get("uf").toString().replace( "\"" ,"");
//
//                                local.setText(mLocal);
//                                cidadeEuf.setText("Realizado "+mCidade +"-"+mUF);
//
//                            }catch (Exception e2) {}
//
//                            //Primeiro Sorteio
//                            final JsonObject objectPrimeiroSorteio  = objectResultado.get("primeiroSorteio").getAsJsonObject();
//                            JsonArray jsonArrayPrimeiroSorteio      = objectPrimeiroSorteio.getAsJsonArray("ordemCrescente").getAsJsonArray();
//                            String mGanhadores = jsonArrayPrimeiroSorteio.toString();
//                            ganhadores.setText(mGanhadores.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
//
//                            ordemSorteio.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    JsonArray jsonArray1 = objectPrimeiroSorteio.getAsJsonArray("ordemSorteio").getAsJsonArray();
//                                    String mGanhadoresOrdemSorteio = jsonArray1.toString();
//                                    ganhadores.setText(mGanhadoresOrdemSorteio.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
//                                    ordemSorteio.setText("Ver números em ordem crescente");
//
//                                    ordemSorteio.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            JsonArray jsonArray = objectPrimeiroSorteio.getAsJsonArray("ordemCrescente").getAsJsonArray();
//                                            String mGanhadores = jsonArray.toString();
//                                            ganhadores.setText(mGanhadores.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
//                                            ordemSorteio.setText("Ver números na ordem do sorteio");
//                                        }
//                                    });
//                                }
//                            });
//                            //
//
//                            //Segundo Sorteio
//                            final JsonObject objectSegundoSorteio = objectResultado.get("segundoSorteio").getAsJsonObject();
//                            JsonArray jsonArraySegundoSorteio     = objectSegundoSorteio.getAsJsonArray("ordemCrescente").getAsJsonArray();
//                            String mGanhadoresSegundo = jsonArraySegundoSorteio.toString();
//                            ganhadoresSegundo.setText(mGanhadoresSegundo.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
//
//                            ordemSorteioSegundo.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    JsonArray jsonArray2 = objectSegundoSorteio.getAsJsonArray("ordemSorteio").getAsJsonArray();
//                                    String mGanhadoresSegundoOrdemSorteio = jsonArray2.toString();
//                                    ganhadoresSegundo.setText(mGanhadoresSegundoOrdemSorteio.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
//                                    ordemSorteioSegundo.setText("Ver números em ordem crescente");
//
//                                    ordemSorteioSegundo.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            JsonArray jsonArraySegundo = objectSegundoSorteio.getAsJsonArray("ordemCrescente").getAsJsonArray();
//                                            String mGanhadoresSegundo = jsonArraySegundo.toString();
//                                            ganhadoresSegundo.setText(mGanhadoresSegundo.replace("\"", "").replace("[", "").replace("]", "").replace(",", " - "));
//                                            ordemSorteioSegundo.setText("Ver números na ordem do sorteio");
//                                        }
//                                    });
//                                }
//                            });
//                            //
//
//                            try {
//
//                                String mProData       = objectProximo.get("data").toString().replace( "\"" ,"");
//                                String mProEstimativa = objectProximo.get("estimativa").toString().replace( "\"" ,"");
//
//                                proximoData.setText("Estimativa de prêmio do próximo\nconcurso "+mProData);
//                                proximoEstimativa.setText("R$"+mProEstimativa);
//
//                            } catch (Exception e3) { }
//                        }
//                    }
//                });


        return  view;
    }
}
