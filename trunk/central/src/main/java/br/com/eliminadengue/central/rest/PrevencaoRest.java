package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.PercentualPrevencoesPorMesFactory;
import br.com.eliminadengue.central.model.PercentualPrevencoes;

import br.com.eliminadengue.central.model.Prevencao;
import static br.com.eliminadengue.central.persistence.EntidadeDao.PREVENCAO;
import br.com.eliminadengue.central.persistence.PrevencaoDao;
import br.com.eliminadengue.central.persistence.Perssiste;
import com.google.gson.Gson;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Guilherme Alves
 */
@Path("/prevencao")
public class PrevencaoRest {

    @Inject
    @Perssiste(entidadeDao = PREVENCAO)
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
    @Path("/percentualPrevencoes")
    @Produces({"application/json"})
    public List<PercentualPrevencoes> percentualPorMes() {
        List<Prevencao> prevencoes = prevencaoDao.todos();
        List<PercentualPrevencoes> percentualPrevencoes
                = new PercentualPrevencoesPorMesFactory().constroi(prevencoes);
        return percentualPrevencoes;
    }

    @GET
    @Path("percentaulPorFocoNoMes")
    @Produces("application/json")
    public List<PercentualPrevencoes> percentualFocoPorMes(@QueryParam("idFoco") Integer idFoco,
            @QueryParam("estado") String estado, @QueryParam("cidade") String cidade,
            @QueryParam("bairro") String bairro) {

        List<Prevencao> prevencoes = prevencaoDao.todos(idFoco, estado, cidade, bairro);
        List<PercentualPrevencoes> percentualPrevencoes = new PercentualPrevencoesPorMesFactory()
                .constroi(prevencoes);

        return percentualPrevencoes;
    }

    @GET
    @Path("regioes")
    @Produces("application/json")
    public String estados(@QueryParam("regiao") String regiao) {
        Set<String> locais = prevencaoDao.localizacoes(regiao);

        String locaisJson = new Gson().toJson(locais);
        return locaisJson;
    }

}
