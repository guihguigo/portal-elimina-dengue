package br.com.eliminadengue.central.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por armazenar series de prevenções separadas por foco
 * As listão não são ordenadas
 * @author Guilherme
 */
public class SerieTemporal {

    private List<List<Prevencao>> serie;

    public SerieTemporal() {
        this.serie = new ArrayList<>();
    }
    
    /**
     * Método responsável por armazenar uma série de prevenção
     * @param seriePrevencao - recebe uma serie de prevenção
     * @exception RuntimeException, caso seja adicionado uma lista vázia
     */
    public void add(List<Prevencao> seriePrevencao) {
        if (seriePrevencao.isEmpty()) 
            throw new RuntimeException("Não é permitido adicionar uma lista de prevencões vazia.");
        
        serie.add(seriePrevencao);
    }
    
    /**
     * 
     * @param indice
     * @return Lista de prevencões na posição N
     */
    public List<Prevencao> get(int indice) {
        return this.serie.get(indice);
    }
    
    /**
     * Retorna o nome da serie que corresponde a lista de prevenção na posição N
     * @param indice
     * @return String - nome da série
     */
    public String getNome(int indice) {
        return serie.get(indice).get(0).getFoco().getNome();
    }
}
