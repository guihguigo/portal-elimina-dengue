/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Alves
 */

@XmlRootElement
public class Prevencao {
    
    private String nomePrevencao;
    
    private long idPrevencao;
   
    public Prevencao(){}
    
//    private Date dataPrevencaoAgendada = new Date(System.currentTimeMillis());
    
//    public Prevencao() {}
//    @XmlElement
//    public String getDataPrevencaoAgendada() {
//        return dataPrevencaoAgendada.toString();
//    }
    
    
    private int idFoco;
    
    public String getNomePrevencao() {
        return nomePrevencao;
    }

    public void setNomePrevencao(String nomePrevencao) {
        this.nomePrevencao = nomePrevencao;
    }

    public long getIdPrevencao() {
        return idPrevencao;
    }

    public int getIdFoco() {
        return idFoco;
    }

    public void setIdPrevencao(long idPrevencao) {
        this.idPrevencao = idPrevencao;
    }

    public void setIdFoco(int idFoco) {
        this.idFoco = idFoco;
    }


}
