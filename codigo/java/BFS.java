package codigo.java;
import java.util.*;

class BFS {
    private final Grafo grafo;

    public BFS(Grafo grafo) {
        this.grafo = grafo;
    }

    public boolean isConnected() {
        for (Map.Entry<String, Vertice> entry : grafo.getVertices().entrySet()) {
            Vertice vertice = entry.getValue();
            Set<Vertice> visited = new HashSet<>();
            bfs(vertice, visited);
            if (visited.size() < grafo.getVertices().size()) return false;
        }
        return true;
    }

    private void bfs(Vertice startVertice, Set<Vertice> visited) {
        Queue<Vertice> fila = new LinkedList<>();
        fila.add(startVertice);
        visited.add(startVertice);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            for (Aresta aresta : atual.getArestasVertice()) {
                Vertice vizinho = aresta.getDestino();
                if (!visited.contains(vizinho)) {
                    visited.add(vizinho);
                    fila.add(vizinho);
                }
            }
        }
    }
}
