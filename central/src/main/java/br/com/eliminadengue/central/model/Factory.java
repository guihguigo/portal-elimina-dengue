/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import java.util.List;

/**
 * Factory genérico para construir percentuais de prevenções agendadas
 * @author T2S
 */
public interface Factory {
    public abstract List<PercentualPrevencao> constroi(List<Prevencao> todasPrevencoes);
}
