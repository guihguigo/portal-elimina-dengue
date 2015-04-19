package controller;

import android.content.Context;

import utils.LocationEndereco;


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


}
