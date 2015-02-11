/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T2S
 */
@XmlRootElement
public class Prevencao implements Serializable{
    
    @XmlElement
    private String nomePrevencao;
    
    @XmlElement
    private long idPrevencao;
    
    private Date dataPrevencaoAgendada = new Date(System.currentTimeMillis());
    
    public Prevencao() {}
    @XmlElement
    public String getDataPrevencaoAgendada() {
        return dataPrevencaoAgendada.toString();
    }
    
    @XmlElement
    private int idFoco;
    
    public String getNomePrevencao() {
        return nomePrevencao;
    }

    public void setNomePrevencao(String nomePrevencao) {
        this.nomePrevencao = nomePrevencao;
    }

}
