package utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexandre on 31/05/2015.
 */
public class ConfigAgenteHelper {
    private DateUtils dtUtils;
    private String idUsuario;
    SharedPreferences prefsSync;

    public ConfigAgenteHelper(Context mContext, String idUsuario) {
        prefsSync = mContext.getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);
        dtUtils = new DateUtils();
        this.idUsuario = idUsuario;

    }

    public boolean isConfigSetada() {
        if (getDiasSync().size() > 0) {
            return true;
        }

        return false;
    }

    public ArrayList<String> getDiasSync() {
        ArrayList<String> diasSync = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            if ((SharedPreferencesHelper.getBoolean(prefsSync, SharedPreferencesHelper.PREFIX_AGENTE_DIA + idUsuario + i, false))) {
                diasSync.add(dtUtils.convertDiaSemana(i));
            }
        }

        return diasSync;
    }

    public String getHora() {
        Date dtSync = dtUtils.StringToDate(SharedPreferencesHelper.getString(prefsSync, SharedPreferencesHelper.AGENTE_HORARIO + idUsuario));
        return dtUtils.DateViewFormattedHora(dtSync);
    }

}
