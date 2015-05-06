package view.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.aedes.R;
import service.CentralSyncService;
import view.adapter.PrevencaoAdapter;

public class AgendaActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setListAdapter(new PrevencaoAdapter(this));
        this.getListView().setBackgroundColor(Color.parseColor("#E0E0E0"));


        CentralSyncService sync = new CentralSyncService(this);
       }


    @Override
    protected void onResume() {
        super.onResume();
        this.setListAdapter(new PrevencaoAdapter(this));
    }

    /*
     * Bloco com implementação genérica das ações dos botões da Linear Layout child
     * Visualizável após clique na row.
     */

    public void actionFeito(View v) {
        YoYo.with(Techniques.Pulse).playOn(v);
    }

    public void actionEditar(View v) {
        YoYo.with(Techniques.Pulse).playOn(v);
    }

    public void actionInfo(View v) {
        YoYo.with(Techniques.Pulse).playOn(v);
    }

    //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_prevencao:
                showAddFoco();
                return true;
            case R.id.action_ajuda:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Chamar activity com lista para gerenciamento de cadastro de Prevenções
     */
    private void showAddFoco() {
        Intent i = new Intent(AgendaActivity.this, AddFocoActivity.class);
        this.startActivity(i);

    }


    private void customizarActionBar(){
        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);

    }

}
