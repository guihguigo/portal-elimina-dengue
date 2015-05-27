package service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bean.Prevencao;
import entity.SyncTableEntity;
import utils.ConnectionHelper;
import utils.SyncUtils;

/**
 * Created by Alexandre on 02/05/2015.
 */
public class CentralSyncService {
    private final String CENTRAL_ROOT_PATH = "http://54.94.249.193:8083/central/webresources/";
    private final String CENTRAL_PREVENCAO_REQUEST_PATH = "prevencao";
    private SyncTableEntity syncEntity;
    private Context context;
    private Handler handler;


    public CentralSyncService(Context context) {
        this.context = context;
        syncEntity = new SyncTableEntity(context);

        if (new SyncUtils(context).verificaSync()) {
            atualizaCentral();
        }

    }

    public String objectToJson(Object obj) {
        Gson jsonObj = new Gson();
        return jsonObj.toJson(obj);
    }

    private void atualizaCentral() {
        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate
                (new Runnable() {


                    public void run() {
                        handler = new Handler(Looper.getMainLooper());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    if (new ConnectionHelper().internetHabilitada(context)) {
                                        final HashMap<String, String> prev = getSyncPendente();
                                        if (prev != null) {
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    int respostaServer = enviaJsonCentral(objectToJson(prev));
                                                    if (respostaServer == 200) {
                                                        syncEntity.removeFirstSync();
                                                    }
                                                }
                                            }.start();

                                        } else {
                                            new SyncUtils(context).atualizaSyncStatus(true);
                                        }
                                    }
                                } catch (Exception ex) {
                                    Log.d("CentralSyncService", ex.getMessage());
                                }
                            }
                        });


                    }
                }, 0, 3600L, TimeUnit.SECONDS);

    }

    private HashMap<String, String> getSyncPendente() {
        Prevencao prevencao = syncEntity.getFirstPrevencao();


        if (prevencao.getFoco().getCodigo() != -1)
            return new SyncUtils(context).ConvertPrevencaoToCentral(prevencao);
        else
            return null;
    }


    public int enviaJsonCentral(String strJson) {
        InputStream inputStream = null;
        try {
            if (new ConnectionHelper().verificaConexao(this.CENTRAL_ROOT_PATH)) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(this.CENTRAL_ROOT_PATH + CENTRAL_PREVENCAO_REQUEST_PATH);
                StringEntity se = new StringEntity(strJson);
                httpPost.setEntity(se);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                HttpResponse httpResponse = httpclient.execute(httpPost);

                //inputStream = httpResponse.getEntity().getContent();

                return httpResponse.getStatusLine().getStatusCode();
            } else {
                return 0;
            }
        } catch (HttpResponseException hre) {
            return 0;
        } catch (IOException e) {
            return 0;
        }


    }
}
