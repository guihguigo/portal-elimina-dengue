package controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bean.Prevencao;
import entity.PrevencaoEntity;
import entity.SyncTableEntity;
import service.AedesAlarmService;
import service.EnderecoService;


/**
 * Created by Alexandre on 07/03/2015.
 */
public class PrevencaoController {
    private Context ctx;
    private PrevencaoEntity pe;
    private AedesAlarmService alarmService;
    private EnderecoService enderecoService;
    private EnderecoController enderecoController;

    public PrevencaoController(Context ctx) {
        this.ctx = ctx;
        this.pe = new PrevencaoEntity(ctx);
        this.enderecoService = new EnderecoService(ctx);
        this.alarmService = new AedesAlarmService(ctx);
    }


    public void atualizaPrevencao(Prevencao prevencao) {
        enderecoController = new EnderecoController(this.ctx);
        prevencao.setLatitude(enderecoController.getLatitude());
        prevencao.setLongitude(enderecoController.getLongitude());
        pe.updatePrevencao(prevencao);
        atualizaNotificador(prevencao);

        new SyncTableEntity(this.ctx).addSync(prevencao);

    }

    public void efetuarPrevencao(Prevencao prevencao) {
        new SyncTableEntity(this.ctx).addSync(prevencao);

        Calendar c = Calendar.getInstance();
        c.setTime(prevencao.getDataPrazo());
        c.add(Calendar.DATE, prevencao.getFoco().getPrazo());
        prevencao.setDataEfetuada(null);
        prevencao.setDataPrazo(atualizarDtPrevencao(prevencao));
        atualizaPrevencao(prevencao);
    }

    /**
     * @deprecated
     */
    public Date setDataPrevencaoAgendamento(int hrPrevencao, int mnPrevencao) {
        Calendar dtPrazo = Calendar.getInstance();
        dtPrazo.set(Calendar.HOUR_OF_DAY, hrPrevencao);
        dtPrazo.set(Calendar.MINUTE, mnPrevencao);


        if (dtPrazo.getTime().before(new Date())) {
            dtPrazo.add(Calendar.DATE, 1);
        }
        return dtPrazo.getTime();
    }

    /**
     *
     *@return Data atualizada para setar no DatePicker como default para próxima prevenção
     */
    public Date atualizarDtPrevencao(Prevencao prevencao){
        Calendar c = Calendar.getInstance();

        if(prevencao.getFoco().getPrazo() > 0)
            c.add(Calendar.DATE, prevencao.getFoco().getPrazo());
        else
            c.add(Calendar.DATE, 1);


        return c.getTime();

    }

    public void salvaPrevencao(Prevencao prevencao) {
        // Adiciona código do celular
        // prevencao.setCodigoCelular(Settings.Secure.getString(this.ctx.getContentResolver(), Settings.Secure.ANDROID_ID));
        enderecoController = new EnderecoController(this.ctx);
        prevencao.setLatitude(enderecoController.getLatitude());
        prevencao.setLongitude(enderecoController.getLongitude());
        pe.addPrevencao(prevencao);
        atualizaNotificador(prevencao);
        //enderecoService.getEndereco();
    }

    public void atualizaNotificador(Prevencao prevencao) {
        alarmService.atualizaNotificador(prevencao);
    }

    public ArrayList<Prevencao> getPrevencoes() {
        return pe.getAllPrevencoes();
    }

    public boolean verificaTituloMes(ArrayList<Prevencao> arr_prev, int i) {
        if (i > 0) {
            if (arr_prev.get(i - 1).getDataCriacao() != null) {
                if (!(arr_prev.get(i).getDataPrazo().getMonth() == arr_prev.get(i - 1).getDataPrazo().getMonth())) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }


    /**
     * @return Retorna a situação da prevenção quanto ao seu respectivo prazo analisando o nível
     * a partir da periodicidade do foco informada
     * <p/>
     * Situações Possíveis:
     * <p/>
     * 1 - Em dia
     * 2 - Próximo a prevenção
     * 3 - Atrasada
     */
    public int getSituacaoPrevencao(Date dtPrazo, double periodicidade) {
        Calendar dataAtual = Calendar.getInstance();
        Date dtAtual = dataAtual.getTime();
        long lngDtAtual = dtAtual.getTime();
        long lngDtPrazo = dtPrazo.getTime();
        long diffTime = lngDtPrazo - lngDtAtual;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);

        if (periodicidade < 1) { // Caso o foco enviado não possua periodicidade pré-estabelecida
            // este seta como default sua periodicidade para 1 semana como limite
            periodicidade = 7;
        }

        if (diffTime < 0) {
            return 3;
        }

        if (diffDays < (periodicidade / 3)) {
            return 2;
        }

        return 1;
    }


}
