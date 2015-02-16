/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.perssistence;

import java.util.List;

/**
 *
 * @author Guilherme Alves
 */
public interface Dao <T>{
    public void salvar(T obj);
    public void atualizar(T obj);
    public void excluir(int x, int y);
    public T encontrar(int x, int y);
    public List<T> todos();
}
