/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Guilherme
 */

@Path("prevencao")
public class PrevencaoRest {
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Prevencao getIt(@QueryParam("nome") String nome) {        
        Prevencao prevencao = new Prevencao();
        prevencao.setNomePrevencao(nome);
        return prevencao;
    }
}
