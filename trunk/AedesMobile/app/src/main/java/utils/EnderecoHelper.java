package utils;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import uk.me.lewisdeane.ldialogs.CustomDialog;

/**
 * Created by Alexandre on 25/05/2015.
 */
public class EnderecoHelper {

    public void verificarGPSHabilitado(final Context mContext) {
        String provider = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (provider.equals("")) {
            CustomDialog.Builder builder = new CustomDialog.Builder(mContext, "Localização", "Sim");
            builder.content("O GPS do dispositivo está desabilitado. " +
                    "Deseja habilitá-lo para que o app possa atualizar sua localização automaticamente?");
            builder.negativeText("Não");
            builder.contentTextSize(15);
            builder.buttonTextSize(16);
            builder.contentColor("#363835");
            builder.positiveColor("#1976D2");
            builder.negativeColor("#D95555");
            CustomDialog customDialog = builder.build();
            customDialog.setClickListener(new CustomDialog.ClickListener() {
                @Override
                public void onConfirmClick() {
                    mContext.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }

                @Override
                public void onCancelClick() {

                }
            });

            customDialog.show();

        }

    }
}
