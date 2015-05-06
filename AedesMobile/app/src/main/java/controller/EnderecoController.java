package controller;

import android.content.Context;

import bean.Endereco;
import entity.EnderecoEntity;
import service.LocationEndereco;


/**
 * Created by Alexandre on 22/02/2015.
 */
public class EnderecoController {
    private LocationEndereco location;
    private double latitude, longitude;
    private EnderecoEntity enderecoEntity;


    public EnderecoController(Context ctx) {
        location = new LocationEndereco(ctx);
        enderecoEntity = new EnderecoEntity(ctx);
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

    public Endereco getEndereco() {
        return enderecoEntity.getEndereco();
    }

    public void salvarEndereco(Endereco e) {
        if (getEndereco() != null)
            enderecoEntity.updateEndereco(e);
        else
            enderecoEntity.addEndereco(e);
    }


}
