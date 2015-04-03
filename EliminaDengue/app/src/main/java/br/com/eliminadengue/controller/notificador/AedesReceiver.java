package br.com.eliminadengue.controller.notificador;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import br.com.eliminadengue.R;
import br.com.eliminadengue.view.MainActivity;

/**
 * Created by Alexandre on 22/03/2015.
 */
public class AedesReceiver extends BroadcastReceiver {
    private String ticker;
    private String titulo;
    private String mensagem;
    private int icone;


    public AedesReceiver() {
        this.ticker = "Aedes";
        this.titulo = "Titulo";
        this.mensagem = "Mensagem";
        this.icone = R.mipmap.ic_launcher;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.titulo = intent.getStringExtra("titulo");
        this.mensagem = intent.getStringExtra("mensagem");
        this.icone = intent.getIntExtra("icone", icone);

        gerarNotificacao(context, new Intent(context, MainActivity.class));
    }


    public void gerarNotificacao(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(mensagem);
        builder.setSmallIcon(icone);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icone));
        builder.setContentIntent(p);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);

        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        } catch (Exception e) {
        }
    }
}
