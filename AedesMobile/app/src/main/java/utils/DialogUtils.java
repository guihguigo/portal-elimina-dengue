package utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
}
