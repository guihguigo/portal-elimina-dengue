package br.com.eliminadengue.central.model;

import java.util.List;

/**
 *
 * @author Guilherme Alves
 */
public abstract class PercentualPrevencoesFactory implements Factory{
    public void criaGuardaPercentualPorMes(List<PercentualPrevencoes> percentualPrevecoes,
            List<Prevencao> prevencoesMes, int mesAtual, String nomeMes) {

        PercentualPrevencoesPorMes percentualPrevencoesPorMes = calculaPercentual(mesAtual, nomeMes, prevencoesMes);
        percentualPrevecoes.add(percentualPrevencoesPorMes);

    }

    private PercentualPrevencoesPorMes calculaPercentual(int mesAtual, String nomeFoco, List<Prevencao> prevencoes) {
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
        percentual.setNomeFoco(nomeFoco);

        return percentual;
    }
}
