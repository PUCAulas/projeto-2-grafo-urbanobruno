public class Aresta {
    private int peso;
    private Vertice origem;
    private Vertice destino;
    private String idAresta;

    Aresta(int peso, Vertice origem, Vertice destino) {
        this.peso = peso;
        this.origem = origem;
        this.destino = destino;
        this.idAresta = gerarIdAresta(origem, destino);
    }

    String gerarIdAresta(Vertice origem, Vertice destino) {
        return origem.id + "->" + destino.id;
    }

    public int getPeso() {
        return peso;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public String getIdAresta() {
        return idAresta;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public void setIdAresta(String idAresta) {
        this.idAresta = idAresta;
    }

}