package br.com.eliminadengue.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.controller.FocoController;

public class ComoLimpar extends Activity {

    private ImageView iconFoco;
    private TextView txtNmFoco, txtComoLimpar;
    private FocoController fc;
    private Foco foco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_limpar);

        iconFoco = (ImageView) findViewById(R.id.imgFocoIcon);
        txtComoLimpar = (TextView) findViewById(R.id.txtComoLimpar);
        txtNmFoco = (TextView) findViewById(R.id.txtNmFoco);

        String idFoco = getIntent().getStringExtra("id_foco");
        fc = new FocoController(getApplicationContext());
        foco = fc.getFoco(idFoco);

        iconFoco.setImageResource(foco.getIcone());
        txtComoLimpar.setText(foco.getComoLimpar());
        txtNmFoco.setText(foco.getNome());

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
