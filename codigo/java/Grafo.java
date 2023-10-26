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

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo() {
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

    // LE E PROCESSA O ARQUIVO

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

    private void processarLinha(String linha) {
        String[] partes = linha.split(":");
        if (partes.length >= 2) {
            String nomeVerticeOrigem = partes[0].trim();
            String[] conexoes = partes[1].trim().split(",");

            // Crie o vértice de origem e adicione ao grafo
            Vertice verticeOrigem = obterOuCriarVertice(nomeVerticeOrigem);

            for (String conexao : conexoes) {
                String[] dadosConexao = conexao.trim().split("\\s*\\(\\s*|\\s*\\)\\s*");

                if (dadosConexao.length == 2) {
                    String nomeVerticeDestino = dadosConexao[0].trim();
                    int distancia = Integer.parseInt(dadosConexao[1]);

                    // Crie o vértice de destino e a aresta correspondente
                    Vertice verticeDestino = obterOuCriarVertice(nomeVerticeDestino);
                    Aresta aresta = new Aresta(verticeOrigem, verticeDestino, distancia);

                    adicionarAresta(aresta);
                }
            }
        }
    }

    private Vertice obterOuCriarVertice(String nome) {
        // Verifica se o vértice já existe no grafo
        for (Vertice v : vertices) {
            if (v.getNome().equals(nome)) {
                return v;
            }
        }
        // Se não existir, cria um novo vértice e adiciona ao grafo
        Vertice novoVertice = new Vertice(vertices.size() + 1, nome);
        adicionarVertice(novoVertice);
        return novoVertice;
    }

    // --------------------------------------------

    // REQUISITO A E B

    public List<Vertice> cidadesQueAlcancamTodas() {
        List<Vertice> cidades = new ArrayList<>();
        for (Vertice origem : vertices) {
            Set<Vertice> visitados = new HashSet<>();
            bfs(origem, visitados);

            if (visitados.size() == vertices.size()) {
                cidades.add(origem);
            }
        }
        return cidades;
    }

    // Método para verificar se todas as cidades são acessíveis a partir de qualquer
    // outra cidade
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

    // Método de busca em largura para verificar se todas as cidades são acessíveis
    // a partir de qualquer outra cidade.
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

    // REQUISITO D

    // Método que permuta entre os vertices para calcular a menor distancia
    public List<Vertice> menorDistanciaPCV() {
        if (vertices.isEmpty()) {
            return new ArrayList<>();
        }
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
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<Vertice> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    private int calcularDistancia(List<Vertice> caminho) {
        if (caminho.isEmpty()) {
            return Integer.MAX_VALUE;
        }
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

    public Map<Vertice, Vertice> recomendarVisita() {
        Map<Vertice, Vertice> recomendacoes = new HashMap<>();

        for (Vertice cidade : vertices) {
            Aresta menorAresta = null;
            for (Aresta aresta : arestas) {
                if (aresta.getOrigem().equals(cidade) || aresta.getDestino().equals(cidade)) {
                    if (menorAresta == null || aresta.getPeso() < menorAresta.getPeso()) {
                        menorAresta = aresta;
                    }
                }
            }

            if (menorAresta != null) {
                Vertice cidadeRecomendada = menorAresta.getOrigem().equals(cidade) ? menorAresta.getDestino()
                        : menorAresta.getOrigem();
                recomendacoes.put(cidade, cidadeRecomendada);
            }
        }

        return recomendacoes;
    }

}
