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
    private double percentAtrasada;
    private double percentEfetuada;
    private int mes;

    public void setPercentAtrasada(double percentAtrasada) {
        this.percentAtrasada = percentAtrasada;
    }

    public void setPercentEfetuada(double percentEfetuada) {
        this.percentEfetuada = percentEfetuada;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getPercentAtrasada() {
        return percentAtrasada;
    }

    public double getPercentEfetuada() {
        return percentEfetuada;
    }

    public int getMes() {
        return mes;
    }
}
