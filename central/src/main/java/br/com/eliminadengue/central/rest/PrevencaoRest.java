/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import br.com.eliminadengue.central.perssistence.PrevencaoDao;
import br.com.eliminadengue.central.perssistence.Perssiste;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Guilherme Alves
 */


@Path("/prevencao")
public class PrevencaoRest {

    @Inject @Perssiste
    private PrevencaoDao prevencaoDao;
    
    @GET
    @Path("{codigoCelular}/{codigoFoco}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response encontrar(@PathParam("codigoCelular") Integer codigoCelular, 
            @PathParam("codigoFoco") Integer codigoFoco) {
        prevencaoDao.encontrar(codigoCelular, codigoFoco);
       
        return Response.ok(new Prevencao()).build();
    }
    
    @GET
    @Produces("application/json")
    public List<Prevencao> todos() {
        prevencaoDao.todos();
        ArrayList<Prevencao> prevencoes = new ArrayList<>();
        prevencoes.add(new Prevencao());
        prevencoes.add(new Prevencao());
        prevencoes.add(new Prevencao());
        prevencoes.add(new Prevencao());
        
        return prevencoes;
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response salvar(Prevencao prevencao) {
        prevencaoDao.salvar(prevencao);
        
        return Response.ok(new Prevencao()).build();
    }
}
