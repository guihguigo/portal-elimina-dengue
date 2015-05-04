package utils;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings;

import java.util.HashMap;

import bean.Endereco;
import bean.Prevencao;
import service.EnderecoService;

/**
 * Created by Alexandre on 02/05/2015.
 */
public class SyncUtils {
    private EnderecoService enderecoService;
    private Endereco enderecoPrevencao;
    private Handler handler;
    private Context context;

    public SyncUtils(Context context) {
        enderecoService = new EnderecoService(context);
        this.context = context;
    }

    public HashMap ConvertPrevencaoToCentral(Prevencao prevencao) {
        HashMap<String, String> prevCentral = new HashMap<>();
        atualizaEnderecoPrevencao(prevencao);

        prevCentral.put("COD_CELULAR","'" +  Settings.Secure.getString(this.context.getContentResolver(), Settings.Secure.ANDROID_ID) + "'");
        prevCentral.put("COD_FOCO",String.valueOf(prevencao.getFoco().getCodigo()));
        prevCentral.put("DAT_CRIACAO", "'" + prevencao.getDataCriacao() + "'");
        prevCentral.put("DAT_EFETUADA", "'" + prevencao.getDataEfetuada() + "'");
        prevCentral.put("END_BAIRRO", "'" + enderecoPrevencao.getBairro() + "'");
        prevCentral.put("END_CIDADE", "'" + enderecoPrevencao.getCidade() + "'");
        prevCentral.put("END_ESTADO", "'" + enderecoPrevencao.getEstado() + "'");
        prevCentral.put("DAT_PRAZO", "'" + prevencao.getDataPrazo() + "'");

        return prevCentral;

    }


    private void atualizaEnderecoPrevencao(final Prevencao prevencao) {

        this.enderecoPrevencao = enderecoService
                .getEndereco(prevencao.getLatitude(), prevencao.getLongitude());


    }


}
