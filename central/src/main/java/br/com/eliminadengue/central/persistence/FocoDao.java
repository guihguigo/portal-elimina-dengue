/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.persistence;

import br.com.eliminadengue.central.model.Foco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author T2S
 */
public class FocoDao {
    @Inject private Connection connection;
    
    public FocoDao() {
    }

    protected List<Foco> todosFocos() {
        List<Foco> focos = new ArrayList();
        String sql = "select * from FOCO";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Integer codigoFoco = result.getInt("cod_foco");
                String nome = result.getString("nom_foco");
                String comoLimpar = result.getString("com_limpar");
                Foco foco = new Foco(codigoFoco, nome, comoLimpar);
                focos.add(foco);
            }
            return focos;
        } catch (SQLException ex) {
            Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
