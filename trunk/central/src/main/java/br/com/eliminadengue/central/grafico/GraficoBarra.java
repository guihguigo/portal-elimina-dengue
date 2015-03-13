package br.com.eliminadengue.central.grafico;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T2S
 */
public class GraficoBarra implements ModeloGrafico{
    private Map<String, Double>barra = new HashMap<>();

    public GraficoBarra() {}
    
    @Override
    public void set(String chave, Double valor) {
        barra.put(chave, valor);
    }
}
