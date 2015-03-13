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
public abstract class PercentualPrevencoes {

    private double percentualAtrasada;
    private double percentualEfetuada;

    public void setPercentualAtrasada(double percentAtualrasada) {
        this.percentualAtrasada = percentAtualrasada;
    }

    public void setPercentualEfetuada(double percentualEfetuada) {
        this.percentualEfetuada = percentualEfetuada;
    }

    public double getPercentualAtrasada() {
        return percentualAtrasada;
    }

    public double getPercentualEfetuada() {
        return percentualEfetuada;
    }
}
