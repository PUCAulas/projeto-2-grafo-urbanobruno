import java.util.*;

class Vertice {
    String id;
    List<Vertice> verticesAdjacentes;

    Vertice(String id) {
        this.id = id;
        this.verticesAdjacentes = new ArrayList <> ();
    }

    void addVerticeAdjacente(Vertice v) {
    this.verticesAdjacentes.add(v);
}
}