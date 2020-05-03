package com.natera.graph;

/**
 * @author Oleg Cherednik
 * @since 02.05.2020
 */
public class Graph<T> extends BaseGraph<T> {

    @Override
    public void addEdge(int oneVertexId, int twoVertexId/*, int weight*/) {
        super.addEdge(oneVertexId, twoVertexId/*, weight*/);
        super.addEdge(twoVertexId, oneVertexId/*, weight*/);
    }

}
