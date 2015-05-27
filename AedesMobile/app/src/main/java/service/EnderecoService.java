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

    private boolean bEnderecoAtualizado = false;


    public EnderecoService(Context ctx) {
        this.ctx = ctx;
        endereco = new Endereco();
    }


    public Endereco getEndereco(double lat, double lng) {
        bEnderecoAtualizado = false;

        if (lat != 0.0 && lng != 0.0) {
            atualizaEndereco(lat, lng);
        } else {
            atualizaEndereco();
        }

        while (!bEnderecoAtualizado) {
            Log.d("Aguardando sincronização", " EnderecoService");
        }

        return this.endereco;
    }

    private void atualizaEndereco(final double lat, final double lng) {
        enderecoController = new EnderecoController(this.ctx);


        new Runnable() {
            @Override
            public void run() {


                new Thread() {
                    @Override
                    public void run() {
                        JSONObject jsonEndereco;

                        jsonEndereco = getJsonPostMaps(lat, lng);


                        try {
                            if(jsonEndereco != null) {
                                JSONArray resultEndereco = jsonEndereco.getJSONArray("results");
                                resultEndereco = resultEndereco.getJSONObject(0).getJSONArray("address_components");
                                endereco.setBairro(resultEndereco.getJSONObject(2).getString("short_name"));
                                endereco.setCidade(resultEndereco.getJSONObject(3).getString("short_name"));
                                endereco.setEstado(resultEndereco.getJSONObject(5).getString("long_name"));
                                endereco.setRua(resultEndereco.getJSONObject(1).getString("long_name"));
                                endereco.setNumero(resultEndereco.getJSONObject(0).getString("long_name"));                            }

                        } catch (Exception ex) {
                            Log.d("Exception", ex.getMessage());
                        } finally {

                            endereco.setLatitude(lat);
                            endereco.setLongitude(lng);
                            bEnderecoAtualizado = true;
                        }

                    }
                }.start();
            }
        }.run();


    }

    private void atualizaEndereco() {
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
                            if(jsonEndereco != null) {
                                JSONArray resultEndereco = jsonEndereco.getJSONArray("results");
                                resultEndereco = resultEndereco.getJSONObject(0).getJSONArray("address_components");
                                endereco.setBairro(resultEndereco.getJSONObject(2).getString("short_name"));
                                endereco.setCidade(resultEndereco.getJSONObject(3).getString("short_name"));
                                endereco.setEstado(resultEndereco.getJSONObject(5).getString("long_name"));
                                endereco.setRua(resultEndereco.getJSONObject(1).getString("long_name"));
                                endereco.setNumero(resultEndereco.getJSONObject(0).getString("long_name"));
                            }
                        } catch (Exception ex) {
                            Log.d("Exception", ex.getMessage());
                            endereco = new Endereco();
                        } finally {
                            endereco.setLatitude(latitude);
                            endereco.setLongitude(longitude);
                            bEnderecoAtualizado = true;
                        }
                    }
                }.start();
            }
        }.run();


    }


    private JSONObject getJsonPostMaps(double latitude, double longitude) {
        Gson objGson = new Gson();
        JSONObject jsonEndereco = null;


        try {
            if (latitude != 0 && longitude != 0) {
                String strJson;
                httpUtils = new HttpUtils(ConexaoConfig.URL_MAPS + latitude + "," + longitude);
                strJson = httpUtils.enviaHttpGet();
                jsonEndereco = new JSONObject(strJson);
            }
        } catch (Exception ex) {
            Log.d("Erro ao buscar Endereço", ex.getMessage());
        }
        return jsonEndereco;
    }
}
