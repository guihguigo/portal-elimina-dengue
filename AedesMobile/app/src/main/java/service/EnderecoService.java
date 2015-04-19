package service;


/**
 * Created by Alexandre on 04/04/2015.
 */


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Endereco;
import controller.EnderecoController;
import service.connection.ConexaoConfig;
import utils.HttpUtils;

/**
 * Classe criada para sincronização de endereço com Google Maps API
 */
public class EnderecoService {
    private Context ctx;
    private Endereco endereco;
    private EnderecoController enderecoController;
    private HttpUtils httpUtils;


    public EnderecoService(Context ctx) {
        this.ctx = ctx;
        endereco = new Endereco();
    }

    public Endereco getEndereco() {
        atualizaEndereco();
        return this.endereco;
    }

    public void atualizaEndereco() {
        enderecoController = new EnderecoController(this.ctx);


        new Runnable() {
            @Override
            public void run() {
                final double latitude = enderecoController.getLatitude();
                final double longitude = enderecoController.getLongitude();

                new Thread() {
                    @Override
                    public void run() {
                        JSONObject jsonEndereco;

                        jsonEndereco = getJsonPostMaps(latitude, longitude);

                        try {
                            JSONArray resultEndereco = jsonEndereco.getJSONArray("results");
                            resultEndereco = resultEndereco.getJSONObject(0).getJSONArray("address_components");
                            endereco.setBairro(resultEndereco.getJSONObject(2).getString("short_name"));
                            endereco.setCidade(resultEndereco.getJSONObject(3).getString("short_name"));
                            endereco.setEstado(resultEndereco.getJSONObject(5).getString("long_name"));
                        } catch (Exception ex) {
                            Log.d("Exception", ex.getMessage());
                        }
                    }
                }.start();
            }
        }.run();


    }


    private JSONObject getJsonPostMaps(double latitude, double longitude) {
        JSONObject jsonEndereco = null;
        Gson objGson = new Gson();
        String strJson = null;
        httpUtils = new HttpUtils(ConexaoConfig.URL_MAPS + latitude + "," + longitude);
        strJson = httpUtils.enviaHttpGet();
        try {
            jsonEndereco = new JSONObject(strJson);
        } catch (Exception ex) {
            Log.d("Erro ao buscar Endereço", ex.getMessage());
        }

        return jsonEndereco;
    }
}
