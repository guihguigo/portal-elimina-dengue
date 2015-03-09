/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;


/**
 *
 * @author Guilherme Alves
 */

public class ConnectionFactory {
    @Resource(name = "jdbc/central-mysql")
    private DataSource dataSource;
    private Connection connection;
    
    @RequestScoped
    @Produces
    public Connection criar() {
        if (connection == null) 
            return getConnection();
        else 
            return connection;
    }
    
    private Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Conexão criada.");
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
