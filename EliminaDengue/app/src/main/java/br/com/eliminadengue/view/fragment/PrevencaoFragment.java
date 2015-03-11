package br.com.eliminadengue.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Prevencao;
import br.com.eliminadengue.entity.FocoEntity;
import br.com.eliminadengue.entity.PrevencaoEntity;
import br.com.eliminadengue.view.adapter.PrevencaoAdapter;

public class PrevencaoFragment extends Fragment {
    private PrevencaoAdapter prevencaoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_prevencao, container, false);
        
        prevencaoAdapter = new PrevencaoAdapter(getActivity(), popularList(getActivity().getApplicationContext()));

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_prevencao);
        gridView.setAdapter(prevencaoAdapter);


        return rootView;
    }

    public void atualizaAdapter(){
        prevencaoAdapter.atualizaAdapter();
    }

    public List<Prevencao> popularList(Context ctx) {
        List<Prevencao> arr_prevencoes = new ArrayList<>();

        PrevencaoEntity pe = new PrevencaoEntity(ctx);
        FocoEntity fe = new FocoEntity(ctx);

        int count = fe.getFocoCount();
        for (int i = 1; i <= count; i++) {
            if (pe.getPrevencao(i).getFoco().getCodigo() != -1) {
                arr_prevencoes.add(pe.getPrevencao(i));
            }
        }


        return arr_prevencoes;
    }

}