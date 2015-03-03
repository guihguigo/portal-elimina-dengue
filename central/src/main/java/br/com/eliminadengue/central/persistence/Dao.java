package br.com.eliminadengue.central.persistence;

import java.util.List;

/**
 *
 * @author Guilherme Alves
 */
public interface Dao <T>{
    public void inserir(T obj);
    public void atualizar(T obj);
    public void excluir(int x, int y);
    public T encontrar(int x, int y);
    public List<T> todos();
    public void salvar(T obj);
}
