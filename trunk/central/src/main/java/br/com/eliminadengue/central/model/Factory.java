/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import java.util.List;

/**
 *
 * @author T2S
 */
public interface Factory {
    public abstract List<PercentualPrevencoes> constroi(List<Prevencao> todasPrevencoes);
}
