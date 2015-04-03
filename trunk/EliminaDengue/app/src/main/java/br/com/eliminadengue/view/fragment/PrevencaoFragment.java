package br.com.eliminadengue.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import br.com.eliminadengue.R;
import br.com.eliminadengue.view.adapter.PrevencaoAdapter;

public class PrevencaoFragment extends Fragment {
    private PrevencaoAdapter prevencaoAdapter = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_prevencao, container, false);
        
        prevencaoAdapter = new PrevencaoAdapter(getActivity());

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_prevencao);
        gridView.setAdapter(prevencaoAdapter);
        return rootView;
    }

    public void atualizaAdapter(){
        if(prevencaoAdapter != null) {
            prevencaoAdapter.atualizaAdapter();
        }
    }


}