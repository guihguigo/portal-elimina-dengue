package service;

import android.content.Context;

import java.util.Date;

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
    //    this.prevencao = prevEntity.getProximaPrevencao();
    }

    private void verificaDtPrazo(Prevencao prevencao) {

        Prevencao prev = prevEntity.getProximaPrevencao();
        if (prev.getFoco().getCodigo() == this.prevencao.getFoco().getCodigo()) {
            this.prevencao.getFoco().setCodigo(-1);
            return;
        }

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


   /* public void atualizaNotificador(Prevencao prevencao) {
        if ((prevencao.getDataPrazo() != prevencao.getDataPrazo() && prevencao.getFoco().getCodigo()
                == this.prevencao.getFoco().getCodigo())
                || (prevencao.getFoco().getCodigo() != this.prevencao.getFoco().getCodigo())) {
            verificaDtPrazo(prevencao);
            if (this.prevencao.getFoco().getCodigo() != -1) {
                aedesNotif = new AedesNotificador("Prevenção a fazer", this.prevencao.getFoco().getNome(),
                        this.prevencao.getDataPrazo(), this.prevencao.getFoco().getIcone(), ctx);
                aedesNotif.criarNotificacao();
            }
        }
    }*/
   public void atualizaNotificador(Prevencao prevencao) {
//     this.prevencao = prevEntity.getProximaPrevencao();

       aedesNotif = new AedesNotificador(prevencao.getFoco().getCodigo(),"Prevenção a fazer",prevencao.getFoco().getNome(),
               prevencao.getDataPrazo(), prevencao.getFoco().getIcone(), ctx);
       aedesNotif.criarNotificacao();
   }

    public void removerNotificador(int idFoco) {
        aedesNotif = new AedesNotificador(idFoco,"Prevenção a fazer","",
                new Date(),0, ctx);
        aedesNotif.removerNotificacao();
    }



}
