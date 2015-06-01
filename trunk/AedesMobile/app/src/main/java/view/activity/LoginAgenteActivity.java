package view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.aedes.R;
import utils.DialogUtils;
import utils.SharedPreferencesHelper;

public class LoginAgenteActivity extends Activity {
    EditText txtUsuario, txtSenha;
    SharedPreferences prefsAgente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agente);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        prefsAgente = getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);

        if(SharedPreferencesHelper.getString(prefsAgente,"idUsuario")!= null){
            showAgenteActivity();
        }
    }

    public void logar(View view) {
        YoYo.with(Techniques.Pulse).playOn(view);
        SharedPreferencesHelper.
                atualizarSharedPreferences(prefsAgente, "idUsuario", txtUsuario.getText().toString());

        if (validarCampos()) {
            showAgenteActivity();
        }else{
            new DialogUtils(this).MaterialDialogOk("Login", "Favor preencher usuário e senha.");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login_agente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAgendaActivity();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAgendaActivity() {
        Intent intent = new Intent(LoginAgenteActivity.this, AgendaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private boolean validarCampos() {
        if (txtUsuario.getText().toString().trim().equals("")) {
            return false;
        }
        if (txtSenha.getText().toString().trim().equals("")) {
            return false;
        }

        return true;
    }

    private void showAgenteActivity() {
        Intent i = new Intent(LoginAgenteActivity.this, AgenteActivity.class);
        startActivity(i);
        finish();
    }
}
