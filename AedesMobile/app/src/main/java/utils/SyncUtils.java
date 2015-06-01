package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.provider.Settings;

import java.util.Calendar;
import java.util.HashMap;

import bean.Endereco;
import bean.Prevencao;
import controller.EnderecoController;
import service.EnderecoService;
import uk.me.lewisdeane.ldialogs.CustomDialog;

/**
 * Created by Alexandre on 02/05/2015.
 */
public class SyncUtils {
    private EnderecoService enderecoService;
    private Endereco enderecoPrevencao;
    private Handler handler;
    private Context context;
    private Prevencao prevencao;
    private SharedPreferences syncPrefs, syncPrefsAgente;
    private final String SYNC_STATUS = "SyncStatus";


    public SyncUtils(Context context) {
        syncPrefs = context.getSharedPreferences(SYNC_STATUS, 0);
        syncPrefsAgente = context.getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);
        enderecoService = new EnderecoService(context);
        this.context = context;
    }

    public HashMap ConvertPrevencaoToCentral(Prevencao prevencao) {
        this.prevencao = prevencao;

        HashMap<String, String> prevCentral = new HashMap<>();

        atualizaEnderecoPrevencao(prevencao);
        verificaEndereco();

        prevCentral.put("COD_CELULAR", "'" + Settings.Secure.getString(this.context.getContentResolver(), Settings.Secure.ANDROID_ID) + "'");
        prevCentral.put("COD_FOCO", String.valueOf(prevencao.getFoco().getCodigo()));
        prevCentral.put("DAT_CRIACAO", "'" + prevencao.getDataCriacao() + "'");
        prevCentral.put("DAT_EFETUADA", "'" + prevencao.getDataEfetuada() + "'");
        prevCentral.put("END_BAIRRO", "'" + enderecoPrevencao.getBairro() + "'");
        prevCentral.put("END_CIDADE", "'" + enderecoPrevencao.getCidade() + "'");
        prevCentral.put("END_ESTADO", "'" + enderecoPrevencao.getEstado() + "'");
        prevCentral.put("DAT_PRAZO", "'" + prevencao.getDataPrazo() + "'");

        return prevCentral;

    }


    private void atualizaEnderecoPrevencao(final Prevencao prevencao) {
        this.enderecoPrevencao = new EnderecoController(context).getEndereco();

        if (this.enderecoPrevencao == null) {
            new EnderecoHelper().verificarGPSHabilitado(context);
            this.enderecoPrevencao = enderecoService
                    .getEndereco(prevencao.getLatitude(), prevencao.getLongitude());
        }
    }

    private void verificaEndereco() {
        if (this.enderecoPrevencao != null && new EnderecoController(context).getEndereco() == null) {
            if (this.enderecoPrevencao.getEstado() != null) {
                dialogConfirmaEndereco();
            }
        }

    }


    private void dialogConfirmaEndereco() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.context, "Confirmar endereço", "Sim");

        builder.content("Você mora em " + enderecoPrevencao.getBairro() + ", "
                + enderecoPrevencao.getCidade() + "-" + new EstadoHelper().getEstadoAbreviatura(enderecoPrevencao.getEstado()) + " ?");
        builder.negativeText("Não");
        builder.typeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(18);
        builder.buttonTextSize(20);
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");

        final CustomDialog customDialog = builder.build();

        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                new EnderecoController(context).salvarEndereco(enderecoPrevencao);
            }

            @Override
            public void onCancelClick() {
                //  atualizaEnderecoPrevencao(prevencao);
            }
        });

        customDialog.show();

    }

    public void atualizaSyncStatus(boolean status) {
        syncPrefs.getBoolean("sincronizado", status);
    }


    public boolean verificaSync() {
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {
            return !syncPrefs.getBoolean("sincronizado", false);
        }
        syncPrefs.edit().putBoolean("sincronizado", false).commit();
        return false;
    }

    public boolean verificaSyncAgente() {
        Calendar cal = Calendar.getInstance();
        String idUsuario = SharedPreferencesHelper.getString(syncPrefsAgente, "idUsuario");


        if (SharedPreferencesHelper.
                getBoolean(syncPrefsAgente, SharedPreferencesHelper.PREFIX_AGENTE_DIA + idUsuario + cal.get(Calendar.DAY_OF_WEEK))) {

            String horarioSync = SharedPreferencesHelper.
                    getString(syncPrefsAgente, SharedPreferencesHelper.AGENTE_HORARIO + idUsuario);

            if ((new DateUtils().StringToDate(horarioSync).getMonth() == cal.getTime().getMonth())) {
                return true;
            }
        }


        return false;
    }


}
