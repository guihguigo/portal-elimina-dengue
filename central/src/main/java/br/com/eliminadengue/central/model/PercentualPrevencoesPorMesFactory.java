/*
 * Classe reponsável por fazer o calculo do percentual de prevenções em dia e atrasadas
 * 
 */
package br.com.eliminadengue.central.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme Alves
 */
public class PercentualPrevencoesPorMesFactory extends PercentualPrevencoesFactory {

    /**
     * Constroi perceutal de prevenções por mês
     *
     * @param todasPrevencoes
     * @return PercentualPrevençõesPorMes
     */
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
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(o1.getDataPrazo());
                Integer mes1 = calendar.get(Calendar.MONTH);
                calendar.setTime(o2.getDataPrazo());
                Integer mes2 = calendar.get(Calendar.MONTH);

                return mes1.compareTo(mes2);
            }
        });

        //recupera o 1º mes da lita
        Date dataPrazo = todasPrevencoes.get(0).getDataPrazo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPrazo);

        int mesAtual = calendar.get(Calendar.MONTH);

        for (Prevencao prevencao : todasPrevencoes) {
            calendar.setTime(prevencao.getDataPrazo());
            int mes = calendar.get(Calendar.MONTH);

            if (mesAtual != mes) {
                criaGuardaPercentualPorMes(percentualPrevecoesPorMes, prevencoesPorMes, mesAtual, prevencao.getFoco().getNome());
                prevencoesPorMes = new ArrayList<>();
                mesAtual = mes;
            }

            prevencoesPorMes.add(prevencao);
        }

        //adiciona o último mês
        criaGuardaPercentualPorMes(percentualPrevecoesPorMes, prevencoesPorMes, mesAtual, prevencoesPorMes.get(0).getFoco().getNome());

        return percentualPrevecoesPorMes;
    }

    

}
