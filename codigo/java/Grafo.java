package codigo.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grafo {
    private Map<String, Vertice> vertices;
    private List<Aresta> arestas;

    Grafo() {
        this.vertices = new HashMap<>();
        this.arestas = new ArrayList<>();
    }

    void addVertice(String id) {
        this.vertices.put(id, new Vertice(id));
    }

    void addAresta(int peso, String origemId, String destinoId) {
        Vertice origem = this.vertices.get(origemId);
        Vertice destino = this.vertices.get(destinoId);

        if (origem == null || destino == null) {
            System.out.println("Vertice de origem ou destino não existe.");
            return;
        }

        Aresta aresta = new Aresta(peso, origem, destino);
        this.arestas.add(aresta);

        origem.addVerticeAdjacente(destino);
    }

    boolean existeEstrada(String origemId, String destinoId) {
        Vertice origem = this.vertices.get(origemId);
        Vertice destino = this.vertices.get(destinoId);

        if (origem == null || destino == null)
            return false;

        return origem.getVerticesAdjacentes().contains(destino);
    }

    public Map<String, Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(Map<String, Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

}