package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Endereco;
import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.model.Prevencao;
import java.net.URI;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
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
    public void salvarPrevencao() {
        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");

        Prevencao prevencao = new Prevencao(12345, foco, null, null, endereco);

        Prevencao prevencaoReponse = target.path("/prevencao").request().accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(prevencao, MediaType.APPLICATION_JSON), Prevencao.class);

        assertThat(prevencaoReponse, notNullValue());
    }
    
    @Test
    public void encontrarPrevencao() {
          Prevencao prevencao = target.path("prevencao")
                
                .request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .get(Prevencao.class);
        assertThat(prevencao, notNullValue());
    }
}
