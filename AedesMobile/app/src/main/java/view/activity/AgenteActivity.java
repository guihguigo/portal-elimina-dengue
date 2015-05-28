package view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import br.com.aedes.R;
import utils.SharedPreferencesHelper;
import view.adapter.AgenteAdapter;

public class AgenteActivity extends Activity {
    private AgenteAdapter agenteAdapter;
    private ListView lstAgente;
    private FloatingActionButton fab_add, fab_atualiza;
    private SharedPreferences prefsAgente;
    private ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agente);
        agenteAdapter = new AgenteAdapter(this);
        progressDialog = new ProgressDialog(this);
        lstAgente = (ListView) findViewById(R.id.lstAgente);
        lstAgente.setAdapter(agenteAdapter);
        lstAgente.setBackgroundColor(Color.parseColor("#E0E0E0"));

        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        prefsAgente = getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);

    }

    @Override
    protected void onResume(){
        super.onResume();
        prefsAgente = getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);
    }

    public void adicionarPrevencao(View view){
        Intent i = new Intent(AgenteActivity.this, AgenteEnderecoActivity.class);
        startActivity(i);

        DialogLoading("Aguarde...", "Buscando dados de localização atual.");
    }

    @Override
    protected void onStop(){
        super.onStop();
        DialogLoading(null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.ic_action_deslogar) {
            SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente,"idUsuario", null);
            showMainActivity();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMainActivity(){
        Intent i = new Intent(AgenteActivity.this, AgendaActivity.class);
        startActivity(i);
    }

    public void DialogLoading(String titulo, String corpo) {
        if(titulo != null) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.custom_progress_bar);
            progressDialog.setCancelable(true);
        }else{
            progressDialog.hide();
        }
    }
}
