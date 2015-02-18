/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.perssistence;

import br.com.eliminadengue.central.model.Endereco;
import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.model.Prevencao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author T2S
 */
public class PrevencaoDaoTest {

    private PrevencaoDao prevencaoDao;

    public PrevencaoDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        prevencaoDao = new PrevencaoDao();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void salvarTest() {
        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");

        Prevencao prevencao = new Prevencao(12345, foco, null, null, endereco);
    }
}
