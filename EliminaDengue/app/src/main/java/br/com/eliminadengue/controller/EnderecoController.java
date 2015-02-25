package br.com.eliminadengue.controller;

import android.content.Context;

import br.com.eliminadengue.utils.LocationEndereco;

/**
 * Created by Alexandre on 22/02/2015.
 */
public class EnderecoController {
    private LocationEndereco location;
    private double latitude, longitude;


    public EnderecoController(Context ctx) {
        location = new LocationEndereco(ctx);
    }

    public double getLatitude() {
        atualizaCoordenadas();
        return this.latitude;
    }

    public double getLongitude() {
        atualizaCoordenadas();
        return this.longitude;
    }


    private void atualizaCoordenadas() {
        location.getLocation();

        if (location.isLocalAtualizado) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

    }
	
	
	/*
		Thread Timer para atualização de dados no server
	ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate
                (new Runnable() {
					public void run(){
					}
				}, 0, 30L, TimeUnit.SECONDS);
				
				
				
				
				--CONVERTER PARA JSON
				  public String objectToJson(Object obj) {
        Gson jsonObj = new Gson();
        return jsonObj.toJson(obj);
    }


	-- Enviar JSON para Servidor
    public String HttpPostJson(String strJson, String path) {
        InputStream inputStream = null;
        try {
            if (new ConnectionHelper().verificaConexao(this.SERVER_ADDRESS)) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(this.SERVER_ADDRESS + path);
                StringEntity se = new StringEntity(strJson);
                httpPost.setEntity(se);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                HttpResponse httpResponse = httpclient.execute(httpPost);
                inputStream = httpResponse.getEntity().getContent();

                return new ConnectionHelper().convertInputStreamToString(inputStream);
            } else {
                return null;
            }
        } catch (HttpResponseException hre) {
            return null;
        } catch (IOException e) {
            return null;
        }


    }
	*/


}
