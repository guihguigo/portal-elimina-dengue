package br.com.eliminadengue.central.grafico;

import br.com.eliminadengue.central.model.PercentualPrevencoes;
import br.com.eliminadengue.central.model.Prevencao;
import java.util.List;

/**
 * Api plota no gráfico a porcentagem com base em cada indicador
 * Os indicadores são atribuidos e variar em tempo de execução
 * Classe inicializada com uma serie de prevenções fatiadas por tipo
 * Os atributos comeco e fim delimita a quantidade de lista contendo as prevenções
 * @author Guilherme Alves
 */
public class GeradorModeloGrafico {

    private ModeloGrafico modeloGrafico;
    private Indicador indicador;

    public GeradorModeloGrafico(List<PercentualPrevencoes> lsita, ModeloGrafico modeloGrafico) {
   
        
        modeloGrafico = new GraficoBarra();
    }
    
    /**
     * Calcula a porcentagem a ser plotada no gráfico com base no indicador
     */
    public void plotaIndicador() {
     
    }
    
}
