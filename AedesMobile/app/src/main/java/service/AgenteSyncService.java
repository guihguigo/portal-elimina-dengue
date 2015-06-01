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
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import entity.PrevencaoAgenteEntity;
import service.connection.ConexaoConfig;
import utils.ConnectionHelper;
import utils.SyncUtils;

/**
 * Created by Alexandre on 31/05/2015.
 */
public class AgenteSyncService {
    private final String CENTRAL_PREVENCAO_REQUEST_PATH = "/agentePrevencao";
    private PrevencaoAgenteEntity syncEntity;
    private Context context;
    private Handler handler;
    private String idUsuario;


    public AgenteSyncService(Context context, String idUsuario) {
        this.context = context;
        this.idUsuario = idUsuario;
        syncEntity = new PrevencaoAgenteEntity(context);


        if (new SyncUtils(context).verificaSyncAgente()) {
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
                                                        syncEntity.delPrevencaoAgente(Integer.parseInt(prev.get(PrevencaoAgenteEntity.ID_SYNC)));
                                                    }
                                                }
                                            }.start();
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
        return syncEntity.getFirstPrevencaoAgente(idUsuario);
    }


    public int enviaJsonCentral(String strJson) {
        try {
            if (new ConnectionHelper().verificaConexao(ConexaoConfig.URL_CENTRAL)) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(ConexaoConfig.URL_CENTRAL + CENTRAL_PREVENCAO_REQUEST_PATH);
                StringEntity se = new StringEntity(strJson);
                httpPost.setEntity(se);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                HttpResponse httpResponse = httpclient.execute(httpPost);

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
