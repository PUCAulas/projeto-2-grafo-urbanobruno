package codigo.java;

import java.util.*;

class Vertice {
    private String id;
    private List<Vertice> verticesAdjacentes;
    private List<Aresta> arestasVertice;

    Vertice(String id) {
        this.id = id;
        this.verticesAdjacentes = new ArrayList<>();
    }

    public void addVerticeAdjacente(Vertice v) {
        this.verticesAdjacentes.add(v);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Vertice> getVerticesAdjacentes() {
        return verticesAdjacentes;
    }

    public void setVerticesAdjacentes(List<Vertice> verticesAdjacentes) {
        this.verticesAdjacentes = verticesAdjacentes;
    }

    public void adicionarAresta(Aresta aresta) {
        this.arestasVertice.add(aresta);
    }

}