package view.fragment;

import android.app.ListFragment;
import android.os.Bundle;

import view.adapter.AddFocoAdapter;

public class AddFocoFragment extends ListFragment {
    private AddFocoAdapter addFocoAdapter= null;


    @Override
    public void onCreate(Bundle savedInstancePrevencao) {
        super.onCreate(savedInstancePrevencao);
        addFocoAdapter = new AddFocoAdapter(getActivity());
        setListAdapter(addFocoAdapter);
    }

}
