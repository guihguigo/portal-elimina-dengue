package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import java.net.URI;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.After;
import org.junit.AfterClass;
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
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost")
            .port(7575)
            .build();

    @BeforeClass
    public static void setUpClass() throws Exception {
        //Configuração para levantar servidor em background
        ResourceConfig rc = new ResourceConfig(PrevencaoRest.class);
        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);

        server.start();
        Client client = ClientBuilder.newClient();
        target = client.target(BASE_URI);
        
        
        
        
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

//    @Test
//    public void salvarPrevencao() {
//        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
//        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");
//
//        Prevencao prevencao = new Prevencao(12345, foco, null, null, endereco);
//
//        Prevencao response = target.path("/prevencao").request()
//                .post(Entity.entity(prevencao, MediaType.APPLICATION_XML), 
//                        Prevencao.class);
//
//        assertThat(response, notNullValue());
//
//    }
    
    @Test
    public void encontrarPrevencao() {
          Prevencao prevencao = target.path("/prevencao")
                           
                .request(MediaType.APPLICATION_XML)
                .get(Prevencao.class);

        assertTrue(prevencao != null);
    }
}
