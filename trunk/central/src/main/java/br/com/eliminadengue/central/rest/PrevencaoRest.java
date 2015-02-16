/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import br.com.eliminadengue.central.perssistence.Dao;
import javax.inject.Inject;
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
   @Inject
   private Dao<Prevencao> prevencaoDao;
   
    @GET
    @Produces("application/xml")
    public Prevencao getIt(@QueryParam("nome") String nome) {        
        Prevencao prevencao = new Prevencao();
        prevencao.setNomePrevencao(nome);
        System.out.println("Chegou aqui!************");
        return prevencao;
    }
    
    @POST
    @Consumes("application/xml")
    public void salvar(Prevencao prevencao) {
        prevencaoDao.salvar(prevencao);
    }
}
