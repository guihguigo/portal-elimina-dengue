package br.com.eliminadengue.bean;

/**
 * Created by Alexandre on 22/02/2015.
 */
public class Foco {
    private int codigo;
    private String nome;
    private String comoLimpar;
    private int icone;
    private int prazo;

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
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
}
