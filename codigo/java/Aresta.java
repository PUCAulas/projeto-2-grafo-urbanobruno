public class Aresta {
    int peso;
    Vertice origem;
    Vertice destino;
    String idAresta;

    Aresta(int peso, Vertice origem, Vertice destino) {
        this.peso = peso;
        this.origem = origem;
        this.destino = destino;
        this.idAresta = gerarIdAresta(origem, destino);
    }

    String gerarIdAresta(Vertice origem, Vertice destino) {
        return origem.id + "->" + destino.id;
    }
}