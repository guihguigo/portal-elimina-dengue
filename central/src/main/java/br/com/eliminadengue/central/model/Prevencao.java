/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Alves
 */

@XmlRootElement
public class Prevencao implements Serializable{
    private int codigoCelular;
    private Foco foco;
    private Date dataCriacao;
    private Date dataPrazo;
    private Date dataEfetuada;
    private Endereco endereco;

    public Prevencao(int codigoCelular, Foco foco, Date dataCriacao, Date dataPrazo, Endereco endereco) {
        this.codigoCelular = codigoCelular;
        this.foco = foco;
        this.dataCriacao = dataCriacao;
        this.dataPrazo = dataPrazo;
        this.endereco = endereco;
    }

    public Prevencao() {}

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public int getCodigoCelular() {
        return codigoCelular;
    }

    public void setCodigoCelular(int codigoCelular) {
        this.codigoCelular = codigoCelular;
    }

    public Foco getFoco() {
        return foco;
    }

    public void setFoco(Foco foco) {
        this.foco = foco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public Date getDataEfetuada() {
        return dataEfetuada;
    }

    public void setDataEfetuada(Date dataEfetuada) {
        this.dataEfetuada = dataEfetuada;
    }

    @Override
    public String toString() {
        return "Prevencao{" + "codigoCelular=" + codigoCelular + ", foco=" + foco + ", dataCriacao=" + dataCriacao + ", dataPrazo=" + dataPrazo + ", dataEfetuada=" + dataEfetuada + '}';
    }

    
}
    
 