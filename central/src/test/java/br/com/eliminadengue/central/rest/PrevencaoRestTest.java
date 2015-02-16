package br.com.eliminadengue.central.rest;

import br.com.eliminadengue.central.model.Prevencao;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme Alves
 */
public class PrevencaoRestTest{

    private static HttpServer server;
    private static WebTarget target;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost")
            .port(7575)
            .build();

    @BeforeClass
    public static void setUpClass() throws Exception {
        ResourceConfig rc = new ResourceConfig(PrevencaoRest.class);
        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);

        server.start();
        Client client = ClientBuilder.newClient();
        target = client.target(BASE_URI); 
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        //server.shutdown();
    }

    @Test
    public void getIt() {
        Prevencao prevencao =  target.path("/prevencao")
                .queryParam("nome", "ralos")
                .request()
                .get(Prevencao.class);
        
        
        
//        final Response response = target.path("prevencao")
//                .queryParam("nome", "ralos").request()
//                .accept(MediaType.APPLICATION_JSON).get();

        

        assertEquals("ralos", prevencao.getNomePrevencao());
    }
    
    @Test
    public void salvarPrevencao() {
        Prevencao prevencao = new Prevencao();
        prevencao.setIdFoco(1);
    }
}
