/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eliminadengue.central.model;

import br.com.eliminadengue.central.grafico.PercentualPrevencoesPorMesFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author T2S
 */
public class GeradorGraficoTest {

    public GeradorGraficoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void percentualPrevencoesPorMesFactoryTest() {
        Endereco endereco = new Endereco("Jardim Quietude", "Praia Grande", "São Paulo");
        Foco foco = new Foco(1, "Ralos", "Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.");

        Calendar hoje = Calendar.getInstance();

        Calendar prazo = Calendar.getInstance();
        prazo.set(Calendar.MONTH, 1);
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Prevencao prevencao1 = new Prevencao(1, foco, hoje.getTime(), prazo.getTime(), endereco);
        prazo.set(Calendar.MONTH, 4);
        Prevencao prevencao2 = new Prevencao(2, foco, hoje.getTime(), prazo.getTime(), endereco);
        prazo.set(Calendar.MONTH, 2);
        Prevencao prevencao3 = new Prevencao(3, foco, hoje.getTime(), prazo.getTime(), endereco);
        prevencao3.setDataEfetuada(hoje.getTime());
        prazo.set(Calendar.MONTH, 2);
        
        Prevencao prevencao4 = new Prevencao(4, foco, hoje.getTime(), prazo.getTime(), endereco);
        prevencao4.setDataEfetuada(hoje.getTime());
        
        List<Prevencao> prevencoes = new ArrayList<>();
        prevencoes.add(prevencao1);
        prevencoes.add(prevencao2);
        prevencoes.add(prevencao3);
        prevencoes.add(prevencao4);
        
        PercentualPrevencoesPorMesFactory factory = new PercentualPrevencoesPorMesFactory();
        List<PercentualPrevencoes> percentualPrevencoesPorMes = factory.constroi(prevencoes);
        
        
        for (PercentualPrevencoes percentual : percentualPrevencoesPorMes) {
            System.out.println("Mês: " + ((PercentualPrevencoesPorMes) percentual).getNomeMes() );
            System.out.println(percentual.getPercentualEfetuada() );
            System.out.println(percentual.getPercentualAtrasada());
        }

    }
}
