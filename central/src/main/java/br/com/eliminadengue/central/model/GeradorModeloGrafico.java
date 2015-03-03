package br.com.eliminadengue.central.model;

import java.util.List;
import javax.inject.Inject;

/**
 * Api plota no gráfico a porcentagem com base em cada indicador
 * Os indicadores são atribuidos e variar em tempo de execução
 * Classe inicializada com uma serie de prevenções fatiadas por tipo
 * Os atributos comeco e fim delimita a quantidade de lista contendo as prevenções
 * @author Guilherme Alves
 */
public class GeradorModeloGrafico {

    private final SerieTemporal serie;
    private final int comeco;
    private final int fim;

    @Inject private ModeloGrafico modeloGrafico;
    @Inject private Indicador indicador;

    public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim) {
        this.serie = serie;
        this.comeco = comeco;
        this.fim = fim;
    }
    
    /**
     * Calcula a porcentagem a ser plotada no gráfico com base no indicador
     */
    public void plotaIndicador() {
        for (int i = comeco; i <= fim; i++) {
            List<Prevencao> prevencoes = serie.get(i);
            double valor = indicador.calcula(i, prevencoes);
            modeloGrafico.set(serie.getNome(i), valor);
        }
    }
    
}
