package view.activity;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.github.amlcurran.showcaseview.ShowcaseView;

import view.adapter.AddFocoAdapter;

public class AddFocoActivity extends ListActivity {
    private final String PREFS = "PrimeiraUtilizacao";
    private ShowcaseView showcaseView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new AddFocoAdapter(this));
        this.getListView().setBackgroundColor(Color.parseColor("#E0E0E0"));
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
