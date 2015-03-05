package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import br.com.eliminadengue.central.model.MatrizPrenvencao;
import br.com.eliminadengue.central.persistence.PrevencaoDao;
import br.com.eliminadengue.central.persistence.Perssiste;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Guilherme Alves
 */
@Path("/prevencao")
public class PrevencaoRest {

    @Inject
    @Perssiste
    private PrevencaoDao prevencaoDao;

    @GET
    @Path("{codigoCelular}/{codigoFoco}")
    @Consumes("application/json")
    @Produces("application/json")
    public Prevencao encontrar(@PathParam("codigoCelular") Integer codigoCelular,
            @PathParam("codigoFoco") Integer codigoFoco) {
        Prevencao prevencao = prevencaoDao.encontrar(codigoCelular, codigoFoco);

        return prevencao;
    }

    @GET
    @Produces("application/json")
    public List<Prevencao> todos() {
        List<Prevencao> prevencoes = prevencaoDao.todos();

        return prevencoes;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Prevencao salvar(Prevencao prevencao) {
        prevencaoDao.salvar(prevencao);

        return prevencao;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Prevencao atualizar(Prevencao prevencao) {
        prevencaoDao.salvar(prevencao);

        return prevencao;
    }

    @DELETE
    @Path("{codigoCelular}/{codigoFoco}")
    public Response excluir(@PathParam("codigoCelular") Integer codigoCelular,
            @PathParam("codigoFoco") Integer codigoFoco) {

        return Response.ok().build();
    }
    
    @GET
    @Path("/matriz")
    public Response matriz() {
       MatrizPrenvencao matriz = prevencaoDao.matrizFocos();
       
        System.out.println(matriz.getNome(0));
        
        return Response.ok().build();
    }
}
