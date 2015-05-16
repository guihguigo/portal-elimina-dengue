package br.com.eliminadengue.central.model;

import java.util.List;

/**
 * 
 * @author Guilherme Alves
 */
public abstract class PercentualPrevencoesFactory implements Factory{
    /**
     * Cria e guarda o percentual do grupo de prevenções.
     * @param percentualPrevecoes - armazena o percentual de cada grupo de prevenções
     * @param grupoPrevencoes - grupo de prevenções atual
     * @param mes - mês do grupo prevenções
     * @param nome - legenda para o grupo de prevenções
     */
    public void criaGuardaPercentualGrupoPrevencoes(List<PercentualPrevencao> percentualPrevecoes,
            List<Prevencao> grupoPrevencoes, int mes, String nome) {

        PercentualGrupoPrevencao percentualPrevencoesPorMes = calculaPercentual(mes, nome, grupoPrevencoes);
        percentualPrevecoes.add(percentualPrevencoesPorMes);

    }
    
    /**
     * Calcula o percentual de prevenções em dia e atrasadas.
     * @param mes
     * @param nome
     * @param prevencoes - lista de prevenções a ser calculada
     * @return PercentualGrupoPrevencao - devolve o percentual do grupo populada com o (%) em dia, (%) atrasada, nome e mês.
     */
    private PercentualGrupoPrevencao calculaPercentual(int mes, String nome, List<Prevencao> prevencoes) {
        double emDia = 0;
        double atrasadas = 0;

        for (Prevencao prevencao : prevencoes) {
            if (prevencao.estaAtrasada()) {
                atrasadas++;
            } else {
                emDia++;
            }
        }

        emDia = (emDia * 100) / prevencoes.size();
        atrasadas = (atrasadas * 100) / prevencoes.size();

        PercentualGrupoPrevencao percentual = new PercentualGrupoPrevencao();
        percentual.setMes(mes);
        percentual.setPercentualEmDia(emDia);
        percentual.setPercentualAtrasada(atrasadas);
        percentual.setNomeFoco(nome);

        return percentual;
    }
}
