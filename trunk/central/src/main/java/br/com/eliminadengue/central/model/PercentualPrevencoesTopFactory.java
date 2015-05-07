/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author T2S
 */
public class PercentualPrevencoesTopFactory extends PercentualPrevencoesFactory{
    
    private Comparator<PercentualPrevencoes> ordenador;
    
    public PercentualPrevencoesTopFactory(Comparator<PercentualPrevencoes> ordenador) {
        this.ordenador = ordenador;
    }
    
    
    @Override
    public List<PercentualPrevencoes> constroi(List<Prevencao> todasPrevencoes) {
        List<Prevencao> prevencoesPorMes = new ArrayList<>();
        List<PercentualPrevencoes> percentualPrevecoesPorMes = new ArrayList<>();
        
        if (todasPrevencoes.isEmpty())
            return percentualPrevecoesPorMes;
        
        //Ordena a lista por data em ordem crescente
        Collections.sort(todasPrevencoes, new Comparator<Prevencao>() {
            @Override
            public int compare(Prevencao o1, Prevencao o2) {
                return o1.getFoco().getNome().compareTo(o2.getFoco().getNome());
            }
        });
        String nomeAtual = todasPrevencoes.get(0).getFoco().getNome();
        Calendar calendar = Calendar.getInstance();
        int mes = 0;
                
        for (Prevencao prevencao : todasPrevencoes) {
            
            String nome = prevencao.getFoco().getNome();
            calendar.setTime(prevencao.getDataPrazo());
            mes = calendar.get(Calendar.MONTH);
            if (!nomeAtual.equals(nome)) {
                
                
                criaGuardaPercentualPorMes(percentualPrevecoesPorMes, prevencoesPorMes, mes,nomeAtual);
                prevencoesPorMes = new ArrayList<>();
                nomeAtual = prevencao.getFoco().getNome();
            }

            prevencoesPorMes.add(prevencao);
        }
        
        
        //adiciona o último mês
        criaGuardaPercentualPorMes(percentualPrevecoesPorMes, prevencoesPorMes, mes, nomeAtual);
        Collections.sort(percentualPrevecoesPorMes, ordenador);
        return percentualPrevecoesPorMes;

    }
    
}
