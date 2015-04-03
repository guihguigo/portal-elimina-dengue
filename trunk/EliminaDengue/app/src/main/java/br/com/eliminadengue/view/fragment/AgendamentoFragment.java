package br.com.eliminadengue.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import br.com.eliminadengue.R;
import br.com.eliminadengue.view.adapter.AgendamentoAdapter;

public class AgendamentoFragment extends Fragment {

    private GridView gridView;
    private AgendamentoAdapter agendamentoAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_agendamento, container, false);

        agendamentoAdapter = new AgendamentoAdapter(getActivity());
        gridView = (GridView) rootView.findViewById(R.id.gridview_agendamento);
        gridView.setAdapter(agendamentoAdapter);


        return rootView;
    }


    public void atualizaAdapter() {
        if(agendamentoAdapter != null) {
            agendamentoAdapter.atualizaAdapter();
        }
    }

}
