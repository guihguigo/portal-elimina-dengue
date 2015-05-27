package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import bean.Endereco;
import br.com.aedes.R;
import entity.PrevencaoAgenteEntity;
import service.EnderecoService;
import utils.DialogUtils;
import utils.EnderecoHelper;

public class AgenteEnderecoActivity extends ActionBarActivity {
    private Endereco enderecoPrevencao;
    private Spinner spEstado;
    private TextView txtCidade, txtBairro, txtRua, txtNumero;



    private static List<String> estado = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agente_endereco);
        spEstado = (Spinner) findViewById(R.id.spEstado);
        txtCidade = (TextView) findViewById(R.id.txtCidade);
        txtBairro = (TextView) findViewById(R.id.txtBairro);
        txtRua = (TextView) findViewById(R.id.txtRua);
        txtNumero = (TextView) findViewById(R.id.txtNumero);

        preencherArrayEstado();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, R.layout.spinner_item, estado);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spEstado.setAdapter(dataAdapter);
        getEndereco();
        new EnderecoHelper().verificarGPSHabilitado(this);
    }

    private void preencherArrayEstado(){
        estado = Arrays.asList("Goiás", "Mato Grosso", "Mato Grosso do Sul",
                "Distrito Federal", "Amazonas", "Acre", "Rondônia", "Roraima",
                "Amapá", "Tocantins", "Pará", "Maranhão", "Piauí", "Ceará",
                "Rio Grande do Norte", "Paraíba", "Pernambuco", "Sergipe",
                "Alagoas", "Bahia", "São Paulo", "Minas Gerais",
                "Rio de Janeiro", "Espirito Santo", "Paraná", "Santa Catarina", "Rio Grande do Sul");
        Collections.sort(estado);
    }

    @Override
    protected void onResume(){
        super.onResume();
        getEndereco();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agente_endereco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void atualizarActivity(View view){
        YoYo.with(Techniques.RotateIn).duration(500L).playOn(view);
        getEndereco();
        new EnderecoHelper().verificarGPSHabilitado(this);
    }

    public void salvarActivity(View view){
        if(camposPreenchidos()){
            Intent i = new Intent(AgenteEnderecoActivity.this, AgenteAddFocoActivity.class);
            i.putExtra(PrevencaoAgenteEntity.ESTADO,spEstado.getSelectedItem().toString());
            i.putExtra(PrevencaoAgenteEntity.CIDADE,txtCidade.getText().toString());
            i.putExtra(PrevencaoAgenteEntity.BAIRRO,txtBairro.getText().toString());
            i.putExtra(PrevencaoAgenteEntity.RUA,txtRua.getText().toString());
            i.putExtra(PrevencaoAgenteEntity.NUMERO,txtNumero.getText().toString());
            i.putExtra(PrevencaoAgenteEntity.LATITUDE,String.valueOf(enderecoPrevencao.getLatitude()));
            i.putExtra(PrevencaoAgenteEntity.LONGITUDE,String.valueOf(enderecoPrevencao.getLongitude()));
            startActivity(i);
        }else{
            new DialogUtils(this).MaterialDialogOk("Aedes",
                    "Preencha todos os campos antes de prosseguir.");
        }
    }

    private void getEndereco() {
      EnderecoService enderecoService= new EnderecoService(this);
        enderecoPrevencao = enderecoService.getEndereco(0, 0);


        if(enderecoPrevencao.getEstado() != null){
            spEstado.setSelection(estado.indexOf(enderecoPrevencao.getEstado()));
            txtBairro.setText(enderecoPrevencao.getBairro());
            txtCidade.setText(enderecoPrevencao.getCidade());
            txtRua.setText(enderecoPrevencao.getRua());
            txtNumero.setText(enderecoPrevencao.getNumero());
        }
    }

    private boolean camposPreenchidos(){

        if(txtCidade.getText().toString().equals("")){
            return false;
        }

        if(txtBairro.getText().toString().equals("")){
            return false;
        }

        if(txtRua.getText().toString().equals("")){
            return false;
        }

        if(txtNumero.getText().toString().equals("")){
            return false;
        }

        return true;
    }
}
