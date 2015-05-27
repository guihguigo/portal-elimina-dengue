package utils;

import android.content.SharedPreferences;

/**
 * Created by Alexandre on 23/05/2015.
 */
public final class SharedPreferencesHelper {
    public static final String PREFS = "PrimeiraUtilizacao";
    public static final String AGENTE = "UsuarioAgente";

    public static void atualizarSharedPreferences(SharedPreferences prefs,  String attr, boolean opcao) {
        prefs.edit().putBoolean(attr, opcao).commit();
    }

    public static void atualizarSharedPreferences(SharedPreferences prefs,  String attr, String valor) {
        prefs.edit().putString(attr, valor).commit();
    }

    public static boolean primeiroUso(SharedPreferences prefs, String attr) {
        if (prefs.getBoolean(attr, true)) {
            return true;
        }
        return false;
    }

    public static String getString(SharedPreferences prefs, String attr){
        return prefs.getString(attr, null);
    }

}
