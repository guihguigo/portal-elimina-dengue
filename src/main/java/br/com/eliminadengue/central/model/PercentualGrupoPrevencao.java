/*
 * Define a maneira pela qual a informação trafegará via json
 */
package br.com.eliminadengue.central.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Alves
 */
@XmlRootElement(name = "prevencoes")
public class PercentualGrupoPrevencao implements PercentualPrevencao {
    public final String[] MESES = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO",
            "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};
    
    private double percentualEmDia;
    private double percentualAtrasada;
    private int mes;
    private String nomeFoco;
    private String nomeMes;

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getMes() {
        return mes;
    }

    @Override
    public String toString() {
        return "Percentual de prevenções por mês";
    }

    @Override
    public void setPercentualAtrasada(double percentualAtrasada) {
        this.percentualAtrasada = percentualAtrasada;
    }

    @Override
    public void setPercentualEmDia(double percentualEmDia) {
        this.percentualEmDia = percentualEmDia;
    }

    @Override
    public double getPercentualAtrasada() {
        return this.percentualAtrasada;
    }

    @Override
    public double getPercentualEmDia() {
        return this.percentualEmDia;
    }

    @Override
    public String getNomeMes() {
        return MESES[mes];
    }

    @Override
    public void setNomeMes(String nomeMes) {
        this.nomeMes = nomeMes;

    }

    @Override
    public String getNomeFoco() {
        return this.nomeFoco;
    }

    @Override
    public void setNomeFoco(String nomeFoco) {
        this.nomeFoco = nomeFoco;
    }

}
