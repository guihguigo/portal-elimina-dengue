package controller.notificador;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

import java.util.Calendar;

/**
 * Created by Alexandre on 22/03/2015.
 */
public class AedesNotificador {

    private Context ctx;
    private AedesReceiver aedesRcv;
    private String titulo;
    private String mensagem;
    private int icone;
    private Calendar dtPrazo;

    public AedesNotificador(String tituloNotificacao, String textoNotificacao, Date dtPrazo, int icone, Context ctx) {
        this.ctx = ctx;
        this.titulo = tituloNotificacao;
        this.mensagem = textoNotificacao;
        this.icone = icone;
        this.dtPrazo = Calendar.getInstance();
        this.dtPrazo.setTime(dtPrazo);

    }

    public void criarNotificacao() {

        Intent intent = new Intent("ALARME_DISPARADO");
        intent.putExtra("titulo", titulo);
        intent.putExtra("mensagem", mensagem);
        intent.putExtra("icone", icone);

        PendingIntent p = PendingIntent.getBroadcast(ctx, 0, intent, 0);
        AlarmManager alarme = (AlarmManager) ctx.getSystemService(ctx.ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, dtPrazo.getTimeInMillis(), p);
    }


}
