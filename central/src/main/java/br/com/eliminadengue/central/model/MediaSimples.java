package br.com.eliminadengue.central.model;

import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author T2S
 */
public class MediaSimples implements Indicador{
    @Inject private Indicador delegate;

    public MediaSimples(Indicador delegate) {
        this.delegate = delegate;
    }
    
    /**
     * 
     * @param total - quantidade de prevenções armazenadas
     * @param serie - lista de prevenções
     * @return porcentagem de prevenções com base no indicador injetado
     */
    @Override
    public double calcula(int total, List<Prevencao> serie) {
        double parcial = 0;
        for (int i = 0; i < total; i++) {
            parcial =+ delegate.calcula(i, serie);
        }
        
        return (parcial * 100) / total;
    }
    
}
