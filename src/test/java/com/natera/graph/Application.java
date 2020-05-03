package com.natera.graph;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 02.05.2020
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Application {

    public static void main(String... args) throws Exception {
        undirectedGraph();
        directedGraph();
    }

    private static void undirectedGraph() {
        Graph<Integer> graph = new Graph<>();

        for (int i = 0; i <= 12; i++)
            graph.addVertex(i);

        graph.addEdge(0, 6);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
        graph.addEdge(11, 12);

        List<Integer> path1 = graph.getPath(0, 3);
        List<Integer> path2 = graph.getPath(0, 12);

        int a = 0;
        a++;
    }

    private static void directedGraph() {
        Digraph<Integer> graph = new Digraph<>();

        for (int i = 0; i <= 12; i++)
            graph.addVertex(i);

        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 2);
        graph.addEdge(4, 3);
        graph.addEdge(4, 2);
        graph.addEdge(5, 4);
        graph.addEdge(6, 9);
        graph.addEdge(6, 4);
        graph.addEdge(6, 0);
        graph.addEdge(7, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);
        graph.addEdge(8, 9);
        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        graph.addEdge(10, 12);
        graph.addEdge(11, 4);
        graph.addEdge(11, 12);
        graph.addEdge(12, 9);

        List<Integer> path1 = graph.getPath(10, 1);
        List<Integer> path2 = graph.getPath(0, 12);

        int a = 0;
        a++;
    }
}
