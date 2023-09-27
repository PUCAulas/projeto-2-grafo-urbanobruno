import java.util.*;

class Vertice {
    private String id;
    private List<Vertice> verticesAdjacentes;

    Vertice(String id) {
        this.id = id;
        this.verticesAdjacentes = new ArrayList<>();
    }

    void addVerticeAdjacente(Vertice v) {
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

}