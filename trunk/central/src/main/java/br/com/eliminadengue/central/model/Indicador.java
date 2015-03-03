package br.com.eliminadengue.central.model;

import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface Indicador {
    public double calcula(int i, List<Prevencao> serie);
}
