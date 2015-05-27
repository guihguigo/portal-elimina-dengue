package controller;

import android.content.Context;

import java.util.HashMap;

import entity.PrevencaoAgenteEntity;

/**
 * Created by Alexandre on 25/05/2015.
 */
public class AgenteFocoController {
    private Context mContext;
    private PrevencaoAgenteEntity prevAgente;

    public AgenteFocoController(Context mContext) {
        this.mContext = mContext;
        this.prevAgente = new PrevencaoAgenteEntity(mContext);
    }

    public HashMap<String,String> getAgentePrevencao(HashMap<String,String> prevAgente){
       return this.prevAgente.getPrevencaoAgente(Integer.parseInt(prevAgente.get(PrevencaoAgenteEntity.ID_FOCO)),
                prevAgente.get(PrevencaoAgenteEntity.ID_USUARIO),
                prevAgente.get(PrevencaoAgenteEntity.RUA),
                prevAgente.get(PrevencaoAgenteEntity.NUMERO));
    }

    public void salvarAgentePrevencao(HashMap<String,String> prevAgente){
        this.prevAgente.addPrevencaoAgente(prevAgente);
    }

    public void removerAgentePrevencao(HashMap<String,String> prevAgente){
        this.prevAgente.delPrevencaoAgente(Integer.parseInt(prevAgente.get(PrevencaoAgenteEntity.ID_FOCO)),
                prevAgente.get(PrevencaoAgenteEntity.ID_USUARIO),
                prevAgente.get(PrevencaoAgenteEntity.RUA),
                prevAgente.get(PrevencaoAgenteEntity.NUMERO));
    }
}
