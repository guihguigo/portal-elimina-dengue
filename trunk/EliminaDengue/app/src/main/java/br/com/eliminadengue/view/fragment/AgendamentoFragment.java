package br.com.eliminadengue.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.entity.FocoEntity;
import br.com.eliminadengue.view.adapter.AgendamentoAdapter;

public class AgendamentoFragment extends android.support.v4.app.ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View rootView = inflater.inflate(R.layout.fragment_agendamento, container, false);


        setListAdapter(new AgendamentoAdapter(getActivity(),popularList()));

        return super.onCreateView(inflater,container,savedInstanceState);
    }




    private List<Foco> popularList(){
        List<Foco> arr_focos = new ArrayList<>();
        FocoEntity fe = new FocoEntity(getActivity().getApplicationContext());

       int i = 1;
        while (fe.getFoco(i).getCodigo() != -1) {
            arr_focos.add(fe.getFoco(i++));
        }


        return arr_focos;
    }

}
