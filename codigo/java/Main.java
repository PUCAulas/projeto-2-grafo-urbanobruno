package codigo.java;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        // Selecionar arquivo
        grafo.carregarGrafoDeArquivo("files/arqTest1.txt");
        String requisito;

        do {
            System.out.println("Menu:");
            System.out.println("a. verificar se existe estrada de qualquer cidade para qualquer cidade");
            System.out.println("b. verificar quais cidades não são acessíveis via transporte terrestre");
            System.out.println(
                    "c. recomendação de visitação em todas as cidades e todas as estradas");
            System.out.println(
                    "d. recomendação de rota para partir da rodoviária, percorrer todas as cidades conectadas e retornar à rodoviária, percorrendo a menor distância possível");
            System.out.println("x. sair");
            System.out.print("Escolha uma opção: ");
            requisito = scanner.nextLine();

            switch (requisito) {
                case "a":
                    List<Vertice> cidadesQueAlcancam = grafo.cidadesQueAlcancamTodas();

                    System.out.println("Cidades que conseguem chegar a todas as outras cidades:");
                    for (Vertice cidade : cidadesQueAlcancam) {
                        System.out.println(cidade.getNome());
                    }
                    System.out.println();

                    break;

                case "b":
                    List<Vertice> cidadesInalcançaveis = grafo.cidadesInalcançaveis();
                    if (cidadesInalcançaveis.isEmpty()) {
                        System.out.println("Todas as cidades são acessíveis a partir de qualquer outra cidade.");
                    } else {
                        System.out.println("Cidades inalcançáveis a partir de algumas outras cidades:");
                        for (Vertice cidade : cidadesInalcançaveis) {
                            System.out.println(cidade.getNome());
                        }
                    }
                    System.out.println();

                    break;

                case "c":
                    Map<Vertice, Vertice> recomendacoes = grafo.recomendarVisita();
                    System.out.println("Recomendações de visitação:");
                    for (Map.Entry<Vertice, Vertice> entrada : recomendacoes.entrySet()) {
                        System.out.println(entrada.getKey().getNome() + " -> " + entrada.getValue().getNome());
                    }
                    System.out.println();
                    break;

                case "d":
                    List<Vertice> rota = grafo.menorDistanciaPCV();
                    if (rota.isEmpty()) {
                        System.out.println("Não foi possível calcular a rota ");
                    }
                    System.out.println("Melhor rota:");
                    for (int i = 0; i < rota.size(); i++) {
                        System.out.print(rota.get(i).getNome());
                        if (i < rota.size() - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    System.out.println();
                case "x":

                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (!requisito.equals("x"));

        scanner.close();

    }
}