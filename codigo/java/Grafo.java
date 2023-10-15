package codigo.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
<<<<<<< HEAD
=======
import java.util.HashSet;
>>>>>>> 3363b64b07c339b147b4cc65592562f6f3f6d3ef
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


import java.io.*;
import java.util.*;

import java.util.LinkedList;
import java.util.Iterator;

class Grafo {
<<<<<<< HEAD

    // Número de vértices
    private int numeroDeVertices;
    private LinkedList<Integer> adj[];

    // Construtor
    Grafo(int v) {
        numeroDeVertices = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void adicionarAresta(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int s) {
        boolean visitados[] = new boolean[numeroDeVertices];

        LinkedList<Integer> fila = new LinkedList<Integer>();

        visitados[s] = true;
        fila.add(s);

        while (fila.size() != 0) {

            s = fila.poll();
            System.out.print(s + " ");

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visitados[n]) {
                    visitados[n] = true;
                    fila.add(n);
=======
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
>>>>>>> 3363b64b07c339b147b4cc65592562f6f3f6d3ef
                }
            }
        }
    }

<<<<<<< HEAD
    public static void main(String args[]) {
        Grafo g = new Grafo(4);
        g.adicionarAresta(0, 1);
        g.adicionarAresta(0, 2);
        g.adicionarAresta(1, 2);
        g.adicionarAresta(2, 0);
        g.adicionarAresta(2, 3);
        g.adicionarAresta(3, 3);

        System.out.println(
            "Seguindo a primeira travessia em largura "
            + "(Começando no vértice 2)");

        g.BFS(2);
    }
}

=======
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

    // Método que permuta entre os vertices para calcular a menor distancia
    public List<Vertice> menorDistanciaPCV() {
        List<List<Vertice>> todasPermutacoes = permutar(vertices);
        List<Vertice> melhorCaminho = null;
        int menorDistancia = Integer.MAX_VALUE;
    
        for (List<Vertice> caminho : todasPermutacoes) {
            int distanciaAtual = calcularDistancia(caminho);
            if (distanciaAtual < menorDistancia) {
                menorDistancia = distanciaAtual;
                melhorCaminho = caminho;
            }
        }
        return melhorCaminho;
    }
    
    private List<List<Vertice>> permutar(List<Vertice> original) {
        // Retorna todas as permutações possíveis da lista original de vértices
        if (original.size() == 0) { 
            List<List<Vertice>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Vertice firstElement = original.remove(0);
        List<List<Vertice>> returnValue = new ArrayList<>();
        List<List<Vertice>> permutations = permutar(original);
        for (List<Vertice> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<Vertice> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
    
    private int calcularDistancia(List<Vertice> caminho) {
        int distancia = 0;
        for (int i = 0; i < caminho.size() - 1; i++) {
            Vertice atual = caminho.get(i);
            Vertice proximo = caminho.get(i + 1);
            for (Aresta a : arestas) {
                if ((a.getOrigem().equals(atual) && a.getDestino().equals(proximo)) ||
                    (a.getOrigem().equals(proximo) && a.getDestino().equals(atual))) {
                    distancia += a.getPeso();
                    break;
                }
            }
        }
        // Retornar à primeira cidade
        Vertice inicio = caminho.get(0);
        Vertice fim = caminho.get(caminho.size() - 1);
        for (Aresta a : arestas) {
            if ((a.getOrigem().equals(inicio) && a.getDestino().equals(fim)) ||
                (a.getOrigem().equals(fim) && a.getDestino().equals(inicio))) {
                distancia += a.getPeso();
                break;
            }
        }
        return distancia;
    }

    // Método para verificar se todas as cidades são acessíveis a partir de qualquer outra cidade
    public List<Vertice> cidadesInalcançaveis() {
        List<Vertice> cidadesInalcançaveis = new ArrayList<>();

        for (Vertice origem : vertices) {
            Set<Vertice> visitados = new HashSet<>();
            bfs(origem, visitados);

            if (visitados.size() != vertices.size()) {
                // Não é possível alcançar todas as cidades a partir desta origem
                cidadesInalcançaveis.add(origem);
            }
        }

        return cidadesInalcançaveis;
    }

    // Método de busca em largura para verificar se todas as cidades são acessíveis a partir de qualquer outra cidade.
    private void bfs(Vertice origem, Set<Vertice> visitados) {
        Queue<Vertice> fila = new LinkedList<>();
        fila.add(origem);
        visitados.add(origem);

        while (!fila.isEmpty()) {
            Vertice vertice = fila.poll();

            for (Aresta aresta : arestas) {
                if (aresta.getOrigem().equals(vertice) && !visitados.contains(aresta.getDestino())) {
                    visitados.add(aresta.getDestino());
                    fila.add(aresta.getDestino());
                }
            }
        }
    }

}
>>>>>>> 3363b64b07c339b147b4cc65592562f6f3f6d3ef
