package br.com.eliminadengue.central.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Alves
 */
@XmlRootElement
public class Foco implements Serializable {

    private int codigo;
    private String nome;
    private String comoLimpar;

    public Foco(int codigo, String nome, String comoLimpar) {
        this.codigo = codigo;
        this.nome = nome;
        this.comoLimpar = comoLimpar;
    }

    public Foco() {
    }

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

    public String getComoLimpar() {
        return comoLimpar;
    }

    public void setComoLimpar(String comoLimpar) {
        this.comoLimpar = comoLimpar;
    }

    @Override
    public String toString() {
        return "Foco{" + "codigo=" + codigo + ", nome=" + nome + ", comoLispar=" + comoLimpar + '}';
    }

}
