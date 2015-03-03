package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Endereco;
import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.model.Prevencao;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Guilherme Alves
 */
public class PrevencaoRestTest {

    private static HttpServer server;
    private static WebTarget target;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/central/webresources")
            .port(8083)
            .build();

    @BeforeClass
    public static void setUpClass() throws Exception {
        //Configuração para levantar servidor em background
//        ResourceConfig rc = new ResourceConfig(PrevencaoRest.class);
//        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//
//        server.start();
        Client client = ClientBuilder.newClient();
        target = client.target(BASE_URI).register(MoxyJsonFeature.class);
    }

    @AfterClass
    public static void tearDownClass() {
//        server.shutdown();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void salvarTest() {
        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");

        Calendar hoje = Calendar.getInstance();

        Calendar prazo = Calendar.getInstance();
        prazo.add(Calendar.DAY_OF_MONTH, 5);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Prevencao prevencao = null;

        prevencao = new Prevencao(123, foco, hoje.getTime(), prazo.getTime(), endereco);

        prevencao = target.path("/prevencao")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(prevencao, MediaType.APPLICATION_JSON), Prevencao.class);

        assertThat(prevencao, notNullValue());
    }

    @Test
    public void encontrarTest() {
        Prevencao prevencao = target.path("prevencao/123456/1")
                .request().accept(MediaType.APPLICATION_JSON)
                .get(Prevencao.class);

        assertEquals(123456, prevencao.getCodigoCelular());
        assertEquals(1, prevencao.getFoco().getCodigo());
        assertEquals("Vasos (Flores e Plantas)", prevencao.getFoco().getNome());
    }

    @Test
    public void todasTest() {
        GenericType<List<Prevencao>> genericType = new GenericType<List<Prevencao>>() {
        };
        List<Prevencao> prevencoes = target.path("/prevencao")
                .request().accept(MediaType.APPLICATION_JSON)
                .get(genericType);

       assertTrue(prevencoes.size() == 4);
    }


    @Test
    public void atualizarTest() {
        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            Date dataCriacao = dateFormat.parse("27/02/2015");
            Date dataPrazo = dateFormat.parse("22/02/2015");
            Date dataEfetuada = dateFormat.parse("22/02/2015");
            
            Prevencao prevencao = new Prevencao(123456, foco, dataCriacao, dataPrazo, endereco);
            prevencao.setDataEfetuada(dataEfetuada);
            
            prevencao = target.path("/prevencao")
                    .request(MediaType.APPLICATION_JSON).
                    put(Entity.entity(prevencao, MediaType.APPLICATION_JSON), Prevencao.class);

            assertEquals(dataCriacao, prevencao.getDataCriacao());

        } catch (ParseException ex) {
            Logger.getLogger(PrevencaoRestTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void excluirTest() {
        Response response = target.path("/prevencao/123/123")
                .request()
                .delete();

        assertEquals(200, response.getStatus());
    }
}
