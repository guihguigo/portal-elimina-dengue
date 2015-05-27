package view.activity;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import utils.SharedPreferencesHelper;
import view.adapter.AddFocoAdapter;

public class AddFocoActivity extends ListActivity {
    SharedPreferences prefsAgente;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new AddFocoAdapter(this));
        this.getListView().setBackgroundColor(Color.parseColor("#E0E0E0"));
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Deslogar Agente ao entrar na tela de Adicionar Focos, usuário comum.
        prefsAgente = getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);
        SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, "idUsuario", null);
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
