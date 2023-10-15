package codigo.java;
import java.util.HashMap;
import java.util.Map;

public class Cidade {
    private int id;
    private String nome;
    private Map<Cidade, Estrada> vizinhos;

    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.vizinhos = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Map<Cidade, Estrada> getVizinhos() {
        return vizinhos;
    }

    public void adicionarVizinho(Cidade cidadeVizinha, Estrada estrada) {
        vizinhos.put(cidadeVizinha, estrada);
    }
}
