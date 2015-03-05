package br.com.eliminadengue.central.grafico;

import br.com.eliminadengue.central.model.Prevencao;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Indicador que verifica quais prevencões estão no prazo
 * @author Guilherme Alves
 */
public class PrevencoesEfetuadas implements Indicador {
    
    /**
     * Faz a verificação de quais prevenções estão no prazo
     *
     * @param i - indice na qual será efetuada a verificação
     * @param serie - lista de prevencões
     * @return 1, se estiver no prazo, 0 caso não
     */
    @Override
    public double calcula(int i, List<Prevencao> serie) {
        Prevencao prevencao = serie.get(i);
        if (prevencao.getDataEfetuada() == null && prevencao.getDataPrazo().before(new Date())) 
            return 1;
        
        return 0;
    }

    @Override
    public String toString() {
        return "Prevenções efetuadas";
    }

}
