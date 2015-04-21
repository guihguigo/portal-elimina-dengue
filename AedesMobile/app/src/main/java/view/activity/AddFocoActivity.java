package view.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import view.adapter.AddFocoAdapter;

public class AddFocoActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new AddFocoAdapter(this));
    }
}
