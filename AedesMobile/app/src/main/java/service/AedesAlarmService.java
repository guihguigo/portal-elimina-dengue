package service;

import android.content.Context;

import bean.Prevencao;
import controller.notificador.AedesNotificador;
import entity.PrevencaoEntity;


/**
 * Created by Alexandre on 23/03/2015.
 */
public class AedesAlarmService {
    private Context ctx;
    private PrevencaoEntity prevEntity;
    private Prevencao prevencao;
    private AedesNotificador aedesNotif;

    public AedesAlarmService(Context ctx) {
        this.ctx = ctx;
        this.prevEntity = new PrevencaoEntity(ctx);
        this.prevencao = prevEntity.getUltimaPrevencao();
    }

    private void verificaDtPrazo(Prevencao prevencao) {
        if (prevencao.getFoco().getCodigo() != -1) {
            if (this.prevencao.getFoco().getCodigo() != -1) {
                if (prevencao.getDataPrazo().before(this.prevencao.getDataPrazo())) {
                    this.prevencao = prevencao;
                }
            } else {
                this.prevencao = prevencao;
            }
        }
    }


    public void atualizaNotificador(Prevencao prevencao) {
        if (prevencao.getFoco().getCodigo() != this.prevencao.getFoco().getCodigo()) {
            verificaDtPrazo(prevencao);
            if (this.prevencao.getFoco().getCodigo() != -1) {
                aedesNotif = new AedesNotificador("Prevenção a fazer", this.prevencao.getFoco().getNome(),
                        this.prevencao.getDataPrazo(), this.prevencao.getFoco().getIcone(), ctx);
                aedesNotif.criarNotificacao();
            }
        }
    }
}
