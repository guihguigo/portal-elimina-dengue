package controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import entity.PrevencaoAgenteEntity;
import utils.DateUtils;

/**
 * Created by Alexandre on 24/05/2015.
 */
public class AgentePrevencaoController {
    private Context mContext;
    private PrevencaoAgenteEntity agenteEntity;
    private String idUsuario;

    public AgentePrevencaoController(Context mContext, String idUsuario) {
        this.mContext = mContext;
        agenteEntity = new PrevencaoAgenteEntity(mContext);
        this.idUsuario = idUsuario;
    }

    public ArrayList<HashMap<String, String>> getPrevencoes() {
        return agenteEntity.getAllPrevencoesAgente(idUsuario);
    }

    public void removerAllAgentePrevencao(HashMap<String, String> prevAgente) {
        this.agenteEntity.delPrevencaoAgente(prevAgente.get(PrevencaoAgenteEntity.ID_USUARIO),
                prevAgente.get(PrevencaoAgenteEntity.RUA),
                prevAgente.get(PrevencaoAgenteEntity.NUMERO));
    }

    public boolean verificaTituloMes(ArrayList<HashMap<String, String>> arr_prev, int i) {
        if (i > 0) {
            if (arr_prev.get(i - 1).get(agenteEntity.NUMERO) != "-1") {
                if (!(new DateUtils().StringToDate(arr_prev.get(i).get(agenteEntity.DATA_CRIACAO)).getMonth() == new DateUtils().StringToDate(arr_prev.get(i - 1).get(agenteEntity.DATA_CRIACAO)).getMonth())) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }
}
