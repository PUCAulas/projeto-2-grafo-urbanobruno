package codigo.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import codigo.java.Grafo;
import codigo.java.Vertice;

public class GrafoTest {

    @Test
    public void testCidadesQueAlcancamTodas_arqTest1() {
        Grafo grafo = new Grafo();
        grafo.carregarGrafoDeArquivo("files/arqTest1.txt");
        List<Vertice> cidades = grafo.cidadesQueAlcancamTodas();
        assertTrue(cidades.isEmpty());
    }

    @Test
    public void testCidadesInalcançaveis_arqTest2() {
        Grafo grafo = new Grafo();
        grafo.carregarGrafoDeArquivo("files/arqTest2.txt");
        List<Vertice> cidades = grafo.cidadesInalcançaveis();
        assertTrue(cidades.isEmpty());
    }
}
