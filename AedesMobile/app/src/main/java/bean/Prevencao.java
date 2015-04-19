package bean;

import java.util.Date;

/**
 * Created by Alexandre on 22/02/2015.
 */

public class Prevencao {

    private String codigoCelular;
    private Foco foco;
    private Date dataCriacao;
    private Date dataPrazo;
    private Date dataEfetuada;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private int sync;

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public String getCodigoCelular() {
        return codigoCelular;
    }

    public void setCodigoCelular(String codigoCelular) {
        this.codigoCelular = codigoCelular;
    }

    public Foco getFoco() {
        return foco;
    }

    public void setFoco(Foco foco) {
        this.foco = foco;
    }



    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }



    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public Date getDataEfetuada() {
        return dataEfetuada;
    }

    public void setDataEfetuada(Date dataEfetuada) {
        this.dataEfetuada = dataEfetuada;
    }

}
