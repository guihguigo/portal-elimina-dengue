package br.com.eliminadengue.central.grafico;

import br.com.eliminadengue.central.model.MatrizPrenvencao;
import br.com.eliminadengue.central.model.Prevencao;
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

    private final MatrizPrenvencao serie;
    private final int comeco;
    private final int fim;

    private ModeloGrafico modeloGrafico;
    private Indicador indicador;

    public GeradorModeloGrafico(MatrizPrenvencao serie, int comeco, int fim) {
        this.serie = serie;
        this.comeco = comeco;
        this.fim = fim;
        
        modeloGrafico = new GraficoBarra();
        indicador = new MediaSimples(indicador);
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
