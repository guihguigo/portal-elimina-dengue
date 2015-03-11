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
import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.entity.FocoEntity;
import br.com.eliminadengue.entity.PrevencaoEntity;
import br.com.eliminadengue.view.adapter.AgendamentoAdapter;

public class AgendamentoFragment extends Fragment {

    private GridView gridView;
    private AgendamentoAdapter agendamentoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_agendamento, container, false);

        agendamentoAdapter = new AgendamentoAdapter(getActivity(), popularList(getActivity().getApplicationContext()));
        gridView = (GridView) rootView.findViewById(R.id.gridview_agendamento);
        gridView.setAdapter(agendamentoAdapter);


        return rootView;
    }

    public List<Foco> popularList(Context ctx){
        List<Foco> arr_focos = new ArrayList<>();

        PrevencaoEntity pe = new PrevencaoEntity(ctx);
        FocoEntity fe = new FocoEntity(ctx);

        int i = 0;
        while (fe.getFoco(++i).getCodigo() != -1) {
            if(pe.getPrevencao(i).getFoco().getCodigo() == -1) {
                arr_focos.add(fe.getFoco(i));
            }
        }

        return arr_focos;
    }


    public void atualizaAdapter(){
        agendamentoAdapter.atualizaAdapter();
    }

}
