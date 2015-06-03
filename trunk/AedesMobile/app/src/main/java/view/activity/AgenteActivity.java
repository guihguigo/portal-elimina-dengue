package view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.aedes.R;
import uk.me.lewisdeane.ldialogs.CustomDialog;
import utils.ConfigAgenteHelper;
import utils.DateUtils;
import utils.ScreenSizeHelper;
import utils.SharedPreferencesHelper;
import view.adapter.AgenteAdapter;

public class AgenteActivity extends Activity {
    private AgenteAdapter agenteAdapter;
    private ListView lstAgente;
    private FloatingActionButton fab_add, fab_atualiza;
    private SharedPreferences prefsAgente;
    private ProgressDialog progressDialog;
    private Date dtSync;
    private String idUsuario;


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
        idUsuario = SharedPreferencesHelper.getString(prefsAgente, "idUsuario");



    }

    @Override
    protected void onResume() {
        super.onResume();
        prefsAgente = getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);
    }

    public void adicionarPrevencao(View view) {
        Intent i = new Intent(AgenteActivity.this, AgenteEnderecoActivity.class);
        startActivity(i);

        DialogLoading("Aguarde...", "Buscando dados de localização atual.");
    }

    @Override
    protected void onStop() {
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
            SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, "idUsuario", null);
            showMainActivity();
            finish();
            return true;
        } else if (id == R.id.ic_action_config) {
            showSettings();
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMainActivity() {
        Intent i = new Intent(AgenteActivity.this, AgendaActivity.class);
        startActivity(i);
    }

    private void showSettings() {
        MaterialDialogYesNoSync();

    }

    public void DialogLoading(String titulo, String corpo) {
        if (titulo != null) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.custom_progress_bar);
            progressDialog.setCancelable(true);
        } else {
            progressDialog.hide();
        }
    }

    private void MaterialDialogYesNoSync() {
        String titulo = "Sincronização Automática";
        String pergunta = null;
        StringBuilder sb = new StringBuilder();
        ConfigAgenteHelper config = new ConfigAgenteHelper(this, idUsuario);

        if (config.isConfigSetada()) {

            ArrayList<String> diasSync = config.getDiasSync();
            sb.append("Configurações atuais: \n");
            sb.append("Horário: " + config.getHora() + "\n\n");
            sb.append("Dia(s):\n");
            for (int i = 1; i <= diasSync.size(); i++) {
                sb.append(" " + diasSync.get(i - 1) + "\n");
            }
        }

        sb.append("\n\nDeseja editar as configurações atuais");

        pergunta = sb.toString();

        CustomDialog.Builder builder = new CustomDialog.Builder(this, titulo, "Sim");

        builder.content(pergunta);
        builder.negativeText("Não");
        // builder.typeface(Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(new ScreenSizeHelper(this).getFonteCorpo());
        builder.buttonTextSize(new ScreenSizeHelper(this).getFonteCorpo());
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");


        CustomDialog customDialog = builder.build();
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                setHoraSync();
            }

            @Override
            public void onCancelClick() {
                /*
                //Cancelar horário
                SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.AGENTE_HORARIO, null);

                // Cancelar todos os dias
                for (int i = 1; i <= 7; i++) {
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA + i, false);
                }*/
            }
        });
        customDialog.show();
    }


    //Modal Dias da Semana
    private void setDiasSync() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_dias_semana);

        Button btnSalvar = (Button) dialog.findViewById(R.id.btnSalvarSync);
        final CheckBox ckbSegunda,
                ckbTerca,
                ckbQuarta,
                ckbQuinta,
                ckbSexta,
                ckbSabado,
                ckbDomingo;
        ckbSegunda = (CheckBox) dialog.findViewById(R.id.ckbSegunda);
        ckbTerca = (CheckBox) dialog.findViewById(R.id.ckbTerca);
        ckbQuarta = (CheckBox) dialog.findViewById(R.id.ckbQuarta);
        ckbQuinta = (CheckBox) dialog.findViewById(R.id.ckbQuinta);
        ckbSexta = (CheckBox) dialog.findViewById(R.id.ckbSexta);
        ckbSabado = (CheckBox) dialog.findViewById(R.id.ckbSabado);
        ckbDomingo = (CheckBox) dialog.findViewById(R.id.ckbDomingo);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 2, ckbSegunda.isChecked());
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 3, ckbTerca.isChecked());
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 4, ckbQuarta.isChecked());
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 5, ckbQuinta.isChecked());
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 6, ckbSexta.isChecked());
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 7, ckbSabado.isChecked());
                    SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA+idUsuario + 1, ckbDomingo.isChecked());



                SharedPreferencesHelper.atualizarSharedPreferences(prefsAgente, SharedPreferencesHelper.AGENTE_HORARIO+idUsuario, new DateUtils().DateToString(dtSync));


                dialog.dismiss();
            }
        });
        dialog.show();
    }


    //Modal TimePicker
    private void setHoraSync() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_timepicker);
        final TimePicker tpDefineData = (TimePicker) dialog.findViewById(R.id.tpHorarioPrevencao);


        TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitleHrPrevencao);
        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);

        txtTitle.setText("Horário da Sincronização");

        tpDefineData.setIs24HourView(true);
        tpDefineData.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        tpDefineData.setCurrentMinute(Calendar.getInstance().get(Calendar.MINUTE) + 1);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                dtSync = new Date();
                dtSync.setHours(tpDefineData.getCurrentHour());
                dtSync.setMinutes(tpDefineData.getCurrentMinute());
                setDiasSync();
            }
        });
        dialog.show();
    }
}
