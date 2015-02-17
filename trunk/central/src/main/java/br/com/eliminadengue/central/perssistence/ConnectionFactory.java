/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.perssistence;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.sql.DataSource;


/**
 *
 * @author Guilherme Alves
 */

@Named
public class ConnectionFactory {
    @Resource(name = "jdbc/central")
    private DataSource dataSource;
    
    @Produces
    public Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Conexão criada.");
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
