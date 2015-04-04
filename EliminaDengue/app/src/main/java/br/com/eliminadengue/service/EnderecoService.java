package br.com.eliminadengue.service;



/**
 * Created by Alexandre on 04/04/2015.
 */


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
        enderecoController = new EnderecoController(ctx);
    }

    public Endereco getEndereco() {
        new Thread() {
            double latitude;
            double longitude;

            @Override
            public void run() {
                latitude = enderecoController.getLatitude();
                longitude = enderecoController.getLongitude();
                httpUtils = new HttpUtils(ConexaoConfig.URL_MAPS);
                enviaRecebeJsonPost();
            }
        }.start();


        return endereco;
    }


    private JSONObject enviaRecebeJsonPost(double latitude, double longitude) {
        JSONObject jsonEndereco = null;
        String strJson = null;
        BasicNameValuePair valuePair = new BasicNameValuePair("latlng", latitude + "," + longitude);



        strJson = httpUtils.enviaJsonPost(strJson);

        try {
            jsonEndereco = new JSONObject(strJson);
        } catch (Exception ex) {
            Log.d("Erro ao buscar Endereço", ex.getMessage());
        }


        return jsonEndereco;
    }
}
