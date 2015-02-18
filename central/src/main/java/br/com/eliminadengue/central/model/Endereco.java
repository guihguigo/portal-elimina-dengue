/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import java.io.Serializable;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Alves
 */

@XmlRootElement
public class Endereco implements Serializable{
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String bairro, String cidade, String estado) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }
    
    public Endereco() {}

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" + "bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + '}';
    }
    
    
}
