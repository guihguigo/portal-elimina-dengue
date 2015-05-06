package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alexandre on 02/05/2015.
 */
public final class ConnectionHelper {

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean internetHabilitada(Context ctx) {
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public boolean verificaConexao(String urlServer) throws IOException {
        URL url = new URL(urlServer);
        URLConnection conn = url.openConnection();
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;

            httpConn.setConnectTimeout(5000);
            httpConn.setReadTimeout(5000);
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            int response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            Log.d("CONEXAO", ex.getMessage());
            return false;
        }


    }

}