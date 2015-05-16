package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Endereco;
import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.model.PercentualPrevencoesPorMesFactory;
import br.com.eliminadengue.central.model.PercentualPrevencao;
import br.com.eliminadengue.central.model.PercentualPrevencoesFactory;
import br.com.eliminadengue.central.model.PercentualPrevencoesTopFactory;

import br.com.eliminadengue.central.model.Prevencao;
import static br.com.eliminadengue.central.persistence.EntidadeDao.PREVENCAO;
import br.com.eliminadengue.central.persistence.PrevencaoDao;
import br.com.eliminadengue.central.persistence.Perssiste;
import com.google.gson.Gson;
import java.util.Comparator;
import java.util.Date;
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
 * Api rest para consumo e produção de informações sobre as prevenções agendadas
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
    public Prevencao salvar(@QueryParam("idCelular") String idCelular,
            @QueryParam("dataCriacao") Date dataCriacao,
            @QueryParam("dataPrazo") Date dataPrazo,
            @QueryParam("dataEfetuada") Date dataEfetuada,
            @QueryParam("idFoco") Integer idFoco,
            @QueryParam("bairro") String bairro,
            @QueryParam("cidade") String cidade, @QueryParam("estado") String estado) {
        
        
        Endereco endereco = new Endereco(bairro, cidade, estado);
        Foco foco = new Foco(idFoco);
        Prevencao prevencao = new Prevencao(9999, foco, dataCriacao, dataPrazo, endereco);
        
        prevencaoDao.salvar(prevencao);
        
        System.out.println("Prevencao salva!!!!");
        return null;
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
    public List<PercentualPrevencao> percentualPorMes() {
        List<Prevencao> prevencoes = prevencaoDao.todos();
        List<PercentualPrevencao> percentualPrevencoes
                = new PercentualPrevencoesPorMesFactory().constroi(prevencoes);
        return percentualPrevencoes;
    }

    @GET
    @Path("/percentualTopAtrasadas")
    @Produces("application/json")
    public List<PercentualPrevencao> percentualTopAtrasada(@QueryParam("estado") String estado,
            @QueryParam("cidade") String cidade, @QueryParam("bairro") String bairro) {

        List<PercentualPrevencao> percentualPrevencoes = null;
        List<Prevencao> prevencoes = prevencaoDao.todos(null, estado, cidade, bairro);
        PercentualPrevencoesFactory factory = new PercentualPrevencoesTopFactory(new Comparator<PercentualPrevencao>() {
            @Override
            public int compare(PercentualPrevencao o1, PercentualPrevencao o2) {
                if (o1.getPercentualAtrasada() < o2.getPercentualAtrasada()) {
                    return 1;
                } else if (o1.getPercentualAtrasada() > o2.getPercentualAtrasada()) {
                    return -1;
                } else {
                    return 0;
                }
            }

        });
        percentualPrevencoes = factory.constroi(prevencoes);

        return percentualPrevencoes;
    }

    @GET
    @Path("/percentualTopEmDia")
    @Produces("application/json")
    public List<PercentualPrevencao> percentualTopEmDia(@QueryParam("estado") String estado,
            @QueryParam("cidade") String cidade, @QueryParam("bairro") String bairro) {

        List<PercentualPrevencao> percentualPrevencoes = null;
        List<Prevencao> prevencoes = prevencaoDao.todos(null, estado, cidade, bairro);
        PercentualPrevencoesFactory factory = new PercentualPrevencoesTopFactory(new Comparator<PercentualPrevencao>() {
            @Override
            public int compare(PercentualPrevencao o1, PercentualPrevencao o2) {
                if (o1.getPercentualEmDia() < o2.getPercentualEmDia()) {
                    return 1;
                } else if (o1.getPercentualEmDia() > o2.getPercentualEmDia()) {
                    return -1;
                } else {
                    return 0;
                }
            }

        });
        percentualPrevencoes = factory.constroi(prevencoes);

        return percentualPrevencoes;
    }

    @GET
    @Path("/percentualPorMes")
    @Produces("application/json")
    public List<PercentualPrevencao> percentualPorMes(@QueryParam("estado") String estado,
            @QueryParam("cidade") String cidade, @QueryParam("bairro") String bairro) {

        List<PercentualPrevencao> percentualPrevencoes = null;
        List<Prevencao> prevencoes = prevencaoDao.todos(null, estado, cidade, bairro);
        PercentualPrevencoesFactory factory = new PercentualPrevencoesPorMesFactory();
        percentualPrevencoes = factory.constroi(prevencoes);

        return percentualPrevencoes;
    }

    @GET
    @Path("/percentualDoFocoPorMes")
    @Produces("application/json")
    public List<PercentualPrevencao> percentualDoFocoPorMes(@QueryParam("foco") Integer foco, @QueryParam("estado") String estado,
            @QueryParam("cidade") String cidade, @QueryParam("bairro") String bairro) {

        List<PercentualPrevencao> percentualPrevencoes = null;
        List<Prevencao> prevencoes = prevencaoDao.todos(foco, estado, cidade, bairro);
        PercentualPrevencoesFactory factory = new PercentualPrevencoesPorMesFactory();
        percentualPrevencoes = factory.constroi(prevencoes);

        return percentualPrevencoes;
    }

    @GET
    @Path("/regioes")
    @Produces("application/json")
    public String regioes(@QueryParam("regiao") String regiao) {
        Set<String> locais = prevencaoDao.localizacoes(regiao);

        String locaisJson = new Gson().toJson(locais);
        return locaisJson;
    }

}
