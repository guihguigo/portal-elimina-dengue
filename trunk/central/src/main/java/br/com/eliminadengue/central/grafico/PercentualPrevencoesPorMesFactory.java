/*
 * Classe reponsável por fazer o calculo do percentual de prevenções em dia e atrasadas
 * 
 */
package br.com.eliminadengue.central.grafico;

import br.com.eliminadengue.central.model.Factory;
import br.com.eliminadengue.central.model.PercentualPrevencoes;
import br.com.eliminadengue.central.model.PercentualPrevencoesPorMes;
import br.com.eliminadengue.central.model.Prevencao;
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
public class PercentualPrevencoesPorMesFactory implements Factory {

    /**
     * Constroi perceutal de prevenções por mês
     *
     * @param todasPrevencoes
     * @return PercentualPrevençõesPorMes
     */
    @Override
    public List<PercentualPrevencoes> constroi(List<Prevencao> todasPrevencoes) {
        //Ordena a lista por data em ordem crescente
        Collections.sort(todasPrevencoes, new Comparator<Prevencao>() {
            @Override
            public int compare(Prevencao o1, Prevencao o2) {
                return o1.getDataPrazo().compareTo(o2.getDataPrazo());
            }
        });

        List<Prevencao> prevencoesPorMes = new ArrayList<>();
        List<PercentualPrevencoes> percentualPrevecoesPorMes = new ArrayList<>();
        //recupera o 1º mes da lita
        Date dataPrazo = todasPrevencoes.get(0).getDataPrazo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPrazo);

        int mesAtual = calendar.get(Calendar.MONTH);

        for (Prevencao prevencao : todasPrevencoes) {
            calendar.setTime(prevencao.getDataPrazo());
            int mes = calendar.get(Calendar.MONTH);

            if (mesAtual != mes) {
                criaGuardaPercentualPorMes(percentualPrevecoesPorMes, prevencoesPorMes, mesAtual);
                prevencoesPorMes = new ArrayList<>();
                mesAtual = mes;
            }

            prevencoesPorMes.add(prevencao);
        }

        //adiciona o último mês
        criaGuardaPercentualPorMes(percentualPrevecoesPorMes, prevencoesPorMes, mesAtual);

        return percentualPrevecoesPorMes;
    }

    private void criaGuardaPercentualPorMes(List<PercentualPrevencoes> percentualPrevecoes,
            List<Prevencao> prevencoesMes, int mesAtual) {

        PercentualPrevencoesPorMes percentualPrevencoesPorMes = calculaPercentual(mesAtual, prevencoesMes);
        percentualPrevecoes.add(percentualPrevencoesPorMes);

    }

    private PercentualPrevencoesPorMes calculaPercentual(int mesAtual, List<Prevencao> prevencoes) {
        double efetuadas = 0;
        double atrasadas = 0;

        for (int i = 0; i < prevencoes.size(); i++) {
            if (prevencoes.get(i).estaAtrasada()) {
                atrasadas++;
            } else {
                efetuadas++;
            }
        }

        efetuadas = (efetuadas * 100) / prevencoes.size();
        atrasadas = (atrasadas * 100) / prevencoes.size();

        PercentualPrevencoesPorMes percentual = new PercentualPrevencoesPorMes();
        percentual.setMes(mesAtual);
        percentual.setPercentualEfetuada(efetuadas);
        percentual.setPercentualAtrasada(atrasadas);

        return percentual;
    }

}
