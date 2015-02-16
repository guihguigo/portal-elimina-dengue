/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import br.com.eliminadengue.central.perssistence.Dao;
import br.com.eliminadengue.central.perssistence.PrevencaoDao;
import br.com.eliminadengue.central.perssistence.Perssiste;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Guilherme Alves
 */

@Path("prevencao")
public class PrevencaoRest {

    @Inject @Perssiste
    private PrevencaoDao prevencaoDao;
    
    @GET
    @Produces("application/xml")
    public String encontrar(@QueryParam("codigoCelular") int codigoCelular, 
            @QueryParam("codigoFoco") int codigoFoco) {
        prevencaoDao.encontrar(codigoCelular, codigoFoco);
        return "Achou!";
    }
    
    @POST
    @Consumes("application/xml")
    public void salvar(@QueryParam("nome") String nome) {
//        prevencaoDao.salvar();
    }
}
