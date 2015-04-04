package br.com.eliminadengue.service;


/**
 * Created by Alexandre on 04/04/2015.
 */


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.google.gson.Gson;
import org.json.JSONObject;

import br.com.eliminadengue.bean.Endereco;
import br.com.eliminadengue.controller.EnderecoController;
import br.com.eliminadengue.service.connection.ConexaoConfig;
import br.com.eliminadengue.utils.HttpUtils;

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
        enderecoController = new EnderecoController(this.ctx);

        double latitude;
        double longitude;

        latitude = enderecoController.getLatitude();
        longitude = enderecoController.getLongitude();
        getJsonPostMaps(latitude, longitude);



        return endereco;
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
