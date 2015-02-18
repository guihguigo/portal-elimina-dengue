package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Endereco;
import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.model.Prevencao;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.jackson.JacksonFeature;
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
        target = client.target(BASE_URI);
        target.register(JacksonFeature.class);
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

        Prevencao prevencao = new Prevencao(12345, foco, null, null, endereco);

        Prevencao prevencaoReponse = target.path("/prevencao")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(prevencao, MediaType.APPLICATION_JSON), Prevencao.class);

        assertThat(prevencaoReponse, notNullValue());
    }

    @Test
    public void encontrarTest() {

        Prevencao prevencao = target.path("prevencao/123/123")
                .request().accept(MediaType.APPLICATION_JSON)
                .get(Prevencao.class);

        assertThat(prevencao, notNullValue());
    }

    @Test
    public void todasTest() {
        List<Prevencao> prevencoes = target.path("/prevencao")
                .request().accept(MediaType.APPLICATION_JSON)
                .get(ArrayList.class);

        assertThat(prevencoes, notNullValue());
    }

    @Test
    public void atualizarTest() {
        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");

        Prevencao prevencao = new Prevencao(12345, foco, null, null, endereco);

        Prevencao prevencaoResponse = target.path("/prevencao")
                .request().
                put(Entity.entity(prevencao, MediaType.WILDCARD_TYPE), Prevencao.class);
        
        assertThat(prevencaoResponse, notNullValue());
    }
    
    @Test
    public void excluirTest() {
        Response response = target.path("/prevencao/123/123")
                .request()
                .delete();
        
        assertEquals(200, response.getStatus());
    }

}
