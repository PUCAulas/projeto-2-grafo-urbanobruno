package codigo.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import java.io.*;
import java.util.*;

import java.util.LinkedList;
import java.util.Iterator;

class Grafo {

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
                }
            }
        }
    }

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

