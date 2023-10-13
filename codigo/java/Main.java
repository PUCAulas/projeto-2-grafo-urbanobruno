package codigo.java;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        // Arquivo para o grafo
        grafo.carregarGrafoDeArquivo("arqTest1.txt");
        String requisito;

        do {
            System.out.println("Menu:");
            System.out.println("a. recomendação de visitação em todas as cidades e todas as estradas");
            System.out.println("b. exibir estrada de qualquer cidade para qualquer cidade");
            System.out.println(
                    "c. identificação de cidades que não é possível chegar via transporte terrestre");
            System.out.println(
                    "d. recomendação de rota para partir da rodoviária, percorrer todas as cidades conectadas e retornar à rodoviária, percorrendo a menor distância possível");
            System.out.println("x. sair");
            System.out.print("Escolha uma opção: ");
            requisito = scanner.nextLine();

            switch (requisito) {
                case "a":
                    List<String> resultadoBusca = grafo.buscaLargura();
                    // Imprimir
                    System.out.println("Resultado da busca em largura:");
                    for (String aresta : resultadoBusca) {
                        System.out.println(aresta);
                    }
                    break;

                // corrijir
                case "b":


                case "c":


                case "d":


                case "x":

                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (!requisito.equals("x"));

        scanner.close();

    }
}