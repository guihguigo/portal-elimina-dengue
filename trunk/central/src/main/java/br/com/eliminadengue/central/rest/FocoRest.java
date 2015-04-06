/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.persistence.Dao;
import static br.com.eliminadengue.central.persistence.EntidadeDao.FOCO;
import br.com.eliminadengue.central.persistence.FocoDao;
import br.com.eliminadengue.central.persistence.Perssiste;
import java.util.Set;
import java.util.TreeSet;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * REST Web Service
 *
 * @author Guilherme
 */
@Path("foco")
@RequestScoped
public class FocoRest {

    @Context private UriInfo context;
    
    @Inject @Perssiste (entidadeDao = FOCO)
    private FocoDao focoDao;
    
    @GET
    @Produces("application/json")
    public Set<Foco> todos() {
        
        return focoDao.todos();
    }
}
