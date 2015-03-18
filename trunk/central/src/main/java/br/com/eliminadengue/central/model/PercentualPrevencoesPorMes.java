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
@XmlRootElement(name = "prevencoes")
public class PercentualPrevencoesPorMes implements PercentualPrevencoes {
    public final String[] MESES = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO",
            "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};
    
    private double percentualEfetuadas;
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
    public void setPercentualEfetuada(double percentualEfetuada) {
        this.percentualEfetuadas = percentualEfetuada;
    }

    @Override
    public double getPercentualAtrasada() {
        return this.percentualAtrasada;
    }

    @Override
    public double getPercentualEfetuada() {
        return this.percentualEfetuadas;
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
