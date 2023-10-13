package codigo.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    // Getters
    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    // Adicionar
    public void adicionarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void adicionarAresta(Aresta aresta) {
        arestas.add(aresta);
    }

    public void carregarGrafoDeArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                processarLinha(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que processa as linhas do arquivo
    private void processarLinha(String linha) {
        String[] partes = linha.split(":");
        if (partes.length >= 2) {
            String nomeVerticeOrigem = partes[0].trim();
            String[] conexoes = partes[1].trim().split(",");

            // Crie a vertice de origem e adicione ao grafo
            Vertice verticeOrigem = new Vertice(vertices.size() + 1, nomeVerticeOrigem);
            adicionarVertice(verticeOrigem);

            for (String conexao : conexoes) {
                String[] dadosConexao = conexao.trim().split("\\s*\\(\\s*|\\s*\\)\\s*");

                if (dadosConexao.length == 2) {
                    String nomeVerticeDestino = dadosConexao[0].trim();
                    int distancia = Integer.parseInt(dadosConexao[1]);

                    // Crie a vertice de destino e a arestsa correspondente
                    Vertice verticeDestino = new Vertice(vertices.size() + 1, nomeVerticeDestino);
                    Aresta aresta = new Aresta(verticeOrigem, verticeDestino, distancia);

                    adicionarVertice(verticeDestino);
                    adicionarAresta(aresta);
                }
            }
        }
    }

    // Método de busca em largura pra recomendação de visitação em todas as vertices
    // e todas as arestas (a)
    public List<String> buscaLargura() {
        Queue<Vertice> fila = new LinkedList<>();
        int t = 0; // Variável para atribuir valores L (tempo de visita)
        Map<Vertice, Integer> nivel = new HashMap<>(); // Mapeia cada Vertice para seu nível
        Map<Vertice, Vertice> pai = new HashMap<>(); // Mapeia cada Vertice para seu pai
        Map<Vertice, Integer> l = new HashMap<>(); // Mapeia cada Vertice para seu tempo de visita
        Set<Vertice> visitados = new HashSet<>(); // Ver Vertices já visitadas
        Set<String> verticesDestino = new HashSet<>(); // Ver se já está em resultado
        List<String> resultado = new ArrayList<>(); // Consertar resultado

        for (Vertice vertice : vertices) {
            if (!visitados.contains(vertice)) { // Ver se a vertice já foi visitada
                fila.add(vertice);
                nivel.put(vertice, 0);
                pai.put(vertice, null);

                while (!fila.isEmpty()) {
                    Vertice v = fila.poll();
                    int currentLevel = nivel.get(v);
                    l.put(v, t);
                    t++;
                    visitados.add(v); // Marca a vertice como visitada

                    for (Aresta aresta : arestas) {
                        if (aresta.getOrigem().equals(v)) {
                            Vertice w = aresta.getDestino();
                            if (!visitados.contains(w) && !fila.contains(w)) {
                                // Visitar aresta pai {v, w}
                                pai.put(w, v);
                                nivel.put(w, currentLevel + 1);
                                fila.add(w);
                                String cidade = v.getNome() + " -> " + w.getNome();
                                if (!verticesDestino.contains(w.getNome())) {
                                    resultado.add(cidade); // Adiciona o resultado à lista se o nome da vertice destino
                                    verticesDestino.add(w.getNome()); // Adiciona o nome da vertice destino ao resultado
                                }
                            }
                        }
                    }
                }
            }
        }

        return resultado;
    }

}