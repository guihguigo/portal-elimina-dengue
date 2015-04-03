package br.com.eliminadengue.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.eliminadengue.bean.Prevencao;
import br.com.eliminadengue.controller.notificador.AedesAlarmService;
import br.com.eliminadengue.entity.PrevencaoEntity;

/**
 * Created by Alexandre on 07/03/2015.
 */
public class PrevencaoController {
    private Context ctx;
    private PrevencaoEntity pe;
    private AedesAlarmService alarmService;

    public PrevencaoController(Context ctx) {
        this.ctx = ctx;
        this.pe = new PrevencaoEntity(ctx);
        this.alarmService = new AedesAlarmService(ctx);
    }

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
        pe.addPrevencao(prevencao);
        atualizaNotificador(prevencao);
    }

    public void atualizaNotificador(Prevencao prevencao) {
        alarmService.atualizaNotificador(prevencao);
    }

    public ArrayList<Prevencao> getPrevencoes() {
        return pe.getAllPrevencoes();

    }


}
