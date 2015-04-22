package view.fragment;

import android.app.ListFragment;
import android.os.Bundle;

import view.adapter.PrevencaoAdapter;

public class AgendaFragment extends ListFragment {
    private PrevencaoAdapter prevencaoAdapter = null;


    @Override
    public void onCreate(Bundle savedInstancePrevencao) {
        super.onCreate(savedInstancePrevencao);
        prevencaoAdapter = new PrevencaoAdapter(getActivity());
        setListAdapter(prevencaoAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        prevencaoAdapter = new PrevencaoAdapter(getActivity());
        setListAdapter(prevencaoAdapter);
    }


}




