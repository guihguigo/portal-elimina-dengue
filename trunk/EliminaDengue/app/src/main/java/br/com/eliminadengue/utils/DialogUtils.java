package br.com.eliminadengue.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Alexandre on 22/03/2015.
 */
public class DialogUtils {
    private Context ctx;

    public DialogUtils(Context ctx) {
        this.ctx = ctx;
    }

    public void MsgAlertDialogOk(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(mensagem)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void MsgToast(String mensagem){
        Toast.makeText(ctx, mensagem, Toast.LENGTH_LONG).show();
    }
}
