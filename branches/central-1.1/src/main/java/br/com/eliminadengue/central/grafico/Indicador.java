package br.com.eliminadengue.central.grafico;

import br.com.eliminadengue.central.model.Prevencao;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface Indicador {
    public double calcula(int i, List<Prevencao> serie);
}
