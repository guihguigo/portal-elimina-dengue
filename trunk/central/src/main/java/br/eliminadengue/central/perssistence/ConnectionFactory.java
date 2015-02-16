/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eliminadengue.central.perssistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.enterprise.inject.Produces;


/**
 *
 * @author T2S
 */

public class ConnectionFactory {

    @Produces
    public Connection getConnection() {
        System.out.println("Conexão criada.");
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "central", "central");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
