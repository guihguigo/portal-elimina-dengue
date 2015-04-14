package br.com.eliminadengue.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.eliminadengue.bean.Prevencao;
import br.com.eliminadengue.entity.PrevencaoEntity;
import br.com.eliminadengue.service.AedesAlarmService;
import br.com.eliminadengue.service.EnderecoService;

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

    /**
     * @deprecated
     */
    public Date setDataPrevencaoAgendamento(int hrPrevencao, int mnPrevencao) {
        Calendar dtPrazo = Calendar.getInstance();
        dtPrazo.set(Calendar.HOUR_OF_DAY, hrPrevencao);
        dtPrazo.set(Calendar.MINUTE, mnPrevencao);

        // Verifica se o horário enviado é menor que a data atual
        // Sendo assim este só agenda a prevenção para o dia seguinte
        if (dtPrazo.getTime().before(new Date())) {
            dtPrazo.add(Calendar.DATE, 1);
        }
        return dtPrazo.getTime();
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
