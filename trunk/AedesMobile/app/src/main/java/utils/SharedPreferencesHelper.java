package utils;

import android.content.SharedPreferences;

/**
 * Created by Alexandre on 23/05/2015.
 */
public final class SharedPreferencesHelper {
    public static final String PREFS = "PrimeiraUtilizacao";
    public static final String AGENTE = "UsuarioAgente";
    /**
     * Necessário concatenar String para identificar o dia setado para sincronização.
     */
    public static final String PREFIX_AGENTE_DIA = "pref_dia_";
    public static final String AGENTE_HORARIO = "hrPrevencao";

    public static void atualizarSharedPreferences(SharedPreferences prefs,  String attr, boolean opcao) {
        prefs.edit().putBoolean(attr, opcao).commit();
    }

    public static void atualizarSharedPreferences(SharedPreferences prefs,  String attr, String valor) {
        prefs.edit().putString(attr, valor).commit();
    }

    public static boolean getBoolean(SharedPreferences prefs, String attr) {
        if (prefs.getBoolean(attr, true)) {
            return true;
        }
        return false;
    }

    public static boolean getBoolean(SharedPreferences prefs, String attr, boolean defaultValue) {
        if (prefs.getBoolean(attr, defaultValue)) {
            return true;
        }
        return false;
    }

    public static String getString(SharedPreferences prefs, String attr){
        return prefs.getString(attr, null);
    }

}
