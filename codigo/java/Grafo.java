import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grafo {
    Map<String, Vertice> vertices;
    List<Aresta> arestas;

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

        return origem.verticesAdjacentes.contains(destino);
    }
}