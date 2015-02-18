/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.perssistence;

import br.com.eliminadengue.central.model.Prevencao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Guilherme Alves
 */
@Named @Perssiste
public class PrevencaoDao implements Dao<Prevencao>{
    
    @Inject
    private Connection connection;
    
    public PrevencaoDao() {}
    @Override
    public void salvar(Prevencao obj) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into PREVENCAO(cod_celular, "
                    + "cod_foco, dat_criacao, dat_prazo, end_bairro, end_cidade, "
                    + "end_estado) values(?, ?, ?, ?, ?, ?, ?)");
            
            statement.setInt(1, obj.getCodigoCelular());
            statement.setInt(2, obj.getFoco().getCodigo());
            statement.setDate(3, new Date(obj.getDataCriacao().getTime()));
            statement.setDate(4, new Date(obj.getDataPrazo().getTime()));
            statement.setString(5, obj.getEndereco().getBairro());
            statement.setString(6, obj.getEndereco().getCidade());
            statement.setString(7, obj.getEndereco().getEstado());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
