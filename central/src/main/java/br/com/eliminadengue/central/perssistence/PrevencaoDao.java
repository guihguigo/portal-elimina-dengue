/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.perssistence;

import br.com.eliminadengue.central.model.Prevencao;
import java.sql.Connection;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Guilherme Alves
 */
public class PrevencaoDao implements Dao<Prevencao>{
    
    @Inject
    private Connection connection;
    
    @Override
    public void salvar(Prevencao obj) {
        System.out.println("Salvar: " + obj.toString());
    }

    @Override
    public void atualizar(Prevencao obj) {
        System.out.println("Atualizar: " + obj.toString());
    }

    @Override
    public void excluir(int idCelular, int idFoco) {
         System.out.println("Excluir: " + idCelular + ", " + idFoco);
    }

    @Override
    public Prevencao encontrar(int idCelular, int idFoco) {
        System.out.println("Encontrar: " + idCelular + ", " + idFoco);
        return null;
    }

    @Override
    public List<Prevencao> todos() {
        System.out.println("Listar todos");
        return null;
    }
    
}
