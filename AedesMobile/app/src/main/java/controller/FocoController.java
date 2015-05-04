package controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import bean.Foco;
import bean.Prevencao;
import entity.FocoEntity;
import entity.PrevencaoEntity;
import entity.SyncTableEntity;
import service.AedesAlarmService;
import utils.DateUtils;


/**
 * Created by Alexandre on 22/02/2015.
 */
public class FocoController {

    private FocoEntity fe;
    PrevencaoEntity pe;
    private Context ctx;
    private AedesAlarmService alarm;


    public FocoController(Context ctx) {
        this.ctx = ctx;
        this.fe = new FocoEntity(ctx);
        this.pe = new PrevencaoEntity(ctx);
        this.alarm = new AedesAlarmService(ctx);
    }


    public Foco getFoco(String idFoco) {
        return fe.getFoco(Integer.parseInt(idFoco));
    }

    /**
     * @return Todos os focos cadastrados no banco
     * */
    public ArrayList<Foco> getAllFocos() {
        return fe.getAllFocos();
    }


    /**
     * @param dias Periodicidade do foco
     * @return String formatada para camada de visualização com as instruções de periodicidade para usuário
     */
    public String definePeriodicidade(int dias) {
        switch (dias) {
            case 0:
                return "Não há periodicidade";
            case 1:
                return "Recomendado limpar todos os dias";
            default:
                return "Limpar a cada " + dias + " dias";
        }
    }

    /**
     * @return Verdadeiro caso já esteja agendada, Falso caso contrário.
     */
    public boolean verificaAgendamento(int idFoco) {
        return pe.getPrevencao(idFoco).getFoco().getCodigo() != -1;
    }

    private void salvarAgendamento(Prevencao prevencao) {
        prevencao.setDataCriacao(new Date());

        pe.addPrevencao(prevencao);

        alarm.atualizaNotificador(prevencao);
    }

    /**
     * Método verifica se já existe uma prevenção cadastrada para o foco
     * Caso possua, este removerá, caso contrário irá incluir.
     * */
    public void atualizaAgendamento(Prevencao prevencao){
        if(verificaAgendamento(prevencao.getFoco().getCodigo())) {
            removerAgendamento(prevencao.getFoco().getCodigo());
            prevencao.setDataCriacao(null);
        }
        else {
            salvarAgendamento(prevencao);
        }



        new SyncTableEntity(this.ctx).addSync(prevencao);

    }

    private void removerAgendamento(int idFoco){
        pe.delPrevencao(idFoco);
    }

    public Date verificaDataPrazo(Date dtPrazo) {
        if (new DateUtils().validaDtPrazo(dtPrazo))
            return dtPrazo;
        else
            return null;

    }


}
