package utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.easyandroidanimations.library.FoldAnimation;

import java.util.Calendar;
import java.util.Date;

import br.com.aedes.R;
import uk.me.lewisdeane.ldialogs.CustomDialog;

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

    public void MsgToast(String mensagem) {
        Toast.makeText(ctx, mensagem, Toast.LENGTH_LONG).show();
    }

    public boolean MsgAlertDialogYesNo(String pergunta) {
        final boolean[] bRespostaDialog = new boolean[1];
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        bRespostaDialog[0] = true;
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        bRespostaDialog[0] = false;
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ctx);
        builder.setMessage(pergunta).setNegativeButton("Não", dialogClickListener)
                .setPositiveButton("Sim", dialogClickListener).show();


        return bRespostaDialog[0];
    }

    @Deprecated
    public Date DateAlertDialog() {
        final Date dtResult = new Date();
        final Dialog dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_datepicker);
        final DatePicker dpDefineData = (DatePicker) dialog.findViewById(R.id.dpDiaPrevencao);
        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);

        //dpDefineData.set(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dtResult.setYear(dpDefineData.getYear() - 1900);
                dtResult.setMonth(dpDefineData.getMonth());
                dtResult.setDate(dpDefineData.getDayOfMonth());

                new FoldAnimation(v).animate();
            }
        });
        dialog.show();

        return dtResult;
    }

    @Deprecated
    public Date TimeAlertDialog() {
        final Date dtResult = new Date();

        final Dialog dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_timepicker);
        final TimePicker tpDefineData = (TimePicker) dialog.findViewById(R.id.tpHorarioPrevencao);
        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);
        tpDefineData.setIs24HourView(true);
        tpDefineData.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                dtResult.setHours(tpDefineData.getCurrentHour());
                dtResult.setMinutes(tpDefineData.getCurrentMinute());

                new FoldAnimation(v).animate();

            }
        });
        dialog.show();

        return dtResult;

    }


    public void MaterialDialogOk(String titulo, String corpo) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.ctx, titulo, "Ok");
        builder.content(corpo);
        builder.typeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(18);
        builder.buttonTextSize(20);
        builder.contentColor("#363835");
        builder.positiveColor("#1976D2");
        CustomDialog customDialog = builder.build();

        customDialog.show();
    }

    @Deprecated
    private void MaterialDialogYesNo(String titulo, String corpo) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.ctx, titulo, "Sim");

        builder.content(corpo);
        builder.negativeText("Não");
        builder.typeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(18);
        builder.buttonTextSize(20);
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");

        CustomDialog customDialog = builder.build();

        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
            }

            @Override
            public void onCancelClick() {
            }
        });

        customDialog.show();


    }
}
