package view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.HashMap;

import br.com.aedes.R;
import entity.PrevencaoAgenteEntity;
import utils.SharedPreferencesHelper;
import view.adapter.AgenteAddFocoAdapter;

public class AgenteAddFocoActivity extends Activity {
    private ListView lstFocos;
    private AgenteAddFocoAdapter agenteAddFocoAdapter;
    private HashMap<String,String> arrMetaData;
    private SharedPreferences prefsAgente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agente_add_foco);
        lstFocos = (ListView) findViewById(R.id.lstFocos);
        prefsAgente = getSharedPreferences(SharedPreferencesHelper.AGENTE,0);
        preencheArray();
        agenteAddFocoAdapter = new AgenteAddFocoAdapter(this, arrMetaData);
        lstFocos.setAdapter(agenteAddFocoAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agente_add_foco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void preencheArray(){
        Intent i = getIntent();
        arrMetaData = new HashMap<>();
        arrMetaData.put(PrevencaoAgenteEntity.ID_USUARIO,SharedPreferencesHelper.getString(prefsAgente,"idUsuario"));
        arrMetaData.put(PrevencaoAgenteEntity.ESTADO,i.getStringExtra(PrevencaoAgenteEntity.ESTADO));
        arrMetaData.put(PrevencaoAgenteEntity.CIDADE,i.getStringExtra(PrevencaoAgenteEntity.CIDADE));
        arrMetaData.put(PrevencaoAgenteEntity.BAIRRO,i.getStringExtra(PrevencaoAgenteEntity.BAIRRO));
        arrMetaData.put(PrevencaoAgenteEntity.RUA,i.getStringExtra(PrevencaoAgenteEntity.RUA));
        arrMetaData.put(PrevencaoAgenteEntity.NUMERO,i.getStringExtra(PrevencaoAgenteEntity.NUMERO));
        arrMetaData.put(PrevencaoAgenteEntity.LATITUDE,i.getStringExtra(PrevencaoAgenteEntity.LATITUDE));
        arrMetaData.put(PrevencaoAgenteEntity.LONGITUDE, i.getStringExtra(PrevencaoAgenteEntity.LONGITUDE));
    }

    public void salvarLista(View view){
        Intent i = new Intent(AgenteAddFocoActivity.this, AgenteActivity.class);
        startActivity(i);
        finish();
    }

}
