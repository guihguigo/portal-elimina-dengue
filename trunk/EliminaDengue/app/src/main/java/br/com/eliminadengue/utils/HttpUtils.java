package br.com.eliminadengue.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.eliminadengue.service.connection.ConexaoConfig;

/**
 * Created by Alexandre on 04/04/2015.
 * <p/>
 * Classe responsável por quaisquer tipos de requisições externas
 */
public class HttpUtils {
    private static String URL = "";

    public HttpUtils(String URL) {
        this.URL = URL;
    }


    public String enviaJsonPost(Object obj) {
        String strJson = null;
        Gson jsonObj = new Gson();
        strJson = jsonObj.toJson(obj);

        return HttpPost(strJson);
    }

    private String HttpPost(String strJson) {
        InputStream inputStream = null;

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL);
            StringEntity se = new StringEntity(strJson);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();

            return convertInputStreamToString(inputStream);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            return null;
        }
    }

    public String enviaHttpGet(){

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);
        InputStream inputStream = null;

        HttpResponse response;
        try {
            response = client.execute(request);

            inputStream = response.getEntity().getContent();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                return convertInputStreamToString(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


}
