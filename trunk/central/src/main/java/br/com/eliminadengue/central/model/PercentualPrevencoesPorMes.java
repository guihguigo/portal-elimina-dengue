/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T2S
 */
@XmlRootElement(name = "prevencoes")
public class PercentualPrevencoesPorMes {
    private double percentualAtrasada;
    private double percentualEfetuada;
    private int mes;

    public void setPercentualAtrasada(double percentAtualrasada) {
        this.percentualAtrasada = percentAtualrasada;
    }

    public void setPercentualEfetuada(double percentualEfetuada) {
        this.percentualEfetuada = percentualEfetuada;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getPercentualAtrasada() {
        return percentualAtrasada;
    }

    public double getPercentualEfetuada() {
        return percentualEfetuada;
    }

    public int getMes() {
        return mes;
    }
}
