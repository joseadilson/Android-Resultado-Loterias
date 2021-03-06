package com.joseadilson.resultado_loterias.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.joseadilson.resultado_loterias.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuinaFragment extends Fragment {

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


    public QuinaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quina, container, false);

        concurso          = (TextView)view.findViewById(R.id.tvConcursoQuina);
        data              = (TextView)view.findViewById(R.id.tvDataQuina);
        sorteiEspecial    = (TextView)view.findViewById(R.id.tvSorteiEspecialQuina);

        local             = (TextView)view.findViewById(R.id.tvLocalQuina);
        cidadeEuf         = (TextView)view.findViewById(R.id.tvCidadeUFQuina);

        ganhadores        = (TextView)view.findViewById(R.id.tvGanhadoresQuina);

        ordemSorteio      = (TextView)view.findViewById(R.id.tvOrdemSorteioQuina);

        proximoData       = (TextView)view.findViewById(R.id.tvProximoDataQuina);
        proximoEstimativa = (TextView)view.findViewById(R.id.tvProximoEstimativaQuina);

        String urlLotoFacil = "https://api.vitortec.com/loterias/quina/v1.2/";
        Ion.with(getActivity())
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
