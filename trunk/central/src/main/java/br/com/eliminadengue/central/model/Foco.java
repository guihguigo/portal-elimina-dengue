package br.com.eliminadengue.central.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Alves
 */
@XmlRootElement
public class Foco implements Serializable{
    private int codigo;
    private String nome;
    private String comoLispar;

    public Foco(int codigo, String nome, String comoLispar) {
        this.codigo = codigo;
        this.nome = nome;
        this.comoLispar = comoLispar;
    }
    
    public Foco() {}
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComoLispar() {
        return comoLispar;
    }

    public void setComoLispar(String comoLispar) {
        this.comoLispar = comoLispar;
    }
    
    
}
