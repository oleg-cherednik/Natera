package com.natera.graph;

/**
 * Bidirected graph implementation
 *
 * @author Oleg Cherednik
 * @since 02.05.2020
 */
public class Digraph<T> extends BaseGraph<T> {

    @Override
    public void addEdge(int srcVertexId, int destVertexId/*, int weight*/) {
        super.addEdge(srcVertexId, destVertexId/*, weight*/);
    }

}
