package com.natera.graph;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base implementation of <tt>unidirected</tt> and <tt>bidirected</tt> graphs. Edges are stored as <tt>adjacency list</tt>. Every {@link Vertex} and
 * {@link Edge} have it's own unique id.
 *
 * @author Oleg Cherednik
 * @since 03.05.2020
 */
abstract class BaseGraph<T> {

    /** key - vertex's id */
    protected final Map<Integer, Vertex<T>> vertices = new HashMap<>();
    /** key - vertex's id and edge's id */
    protected final Map<Integer, Map<String, Edge>> adj = new HashMap<>();

    protected int nextVertexId;

    /**
     * Add new vertex tot he graph with given {@code val}.
     *
     * @param val any {@literal nullable} value
     * @return unique id of added vertex
     */
    public int addVertex(T val) {
        Vertex<T> vertex = new Vertex<>(nextVertexId++, val);
        vertices.put(vertex.id, vertex);
        adj.put(vertex.id, new HashMap<>());
        return vertex.id;
    }

    /**
     * Add one edge between two vertices with given id.
     *
     * @param srcVertexId  source vertex's id
     * @param destVertexId destination vertex's id
     * @throws {@link RuntimeException} when vertex's id is unknown or edge already exists
     */
    protected void addEdge(int srcVertexId, int destVertexId/*, int weight*/) {
        requireValidVertexId(srcVertexId);
        requireValidVertexId(destVertexId);
        requireUniqueEdge(srcVertexId, destVertexId);

        Edge edge = new Edge(srcVertexId, destVertexId/*, weight*/);
        adj.get(srcVertexId).put(edge.id, edge);
    }

    /**
     * Retrieve not {@literal null} list of all vertices between two given vertices. This path could not be optimal. If this path exists, then first
     * element of the retrieved list is source vertex's id ({@code srcVertexId}) and last one is destination vertex's id ({@code destVertexId}). If
     * path is not exists, then empty list is retrieved.
     *
     * @param srcVertexId  source vertex's id
     * @param destVertexId destination vertex's id
     * @return not {@link null} list of vertices's ids
     * @throws {@link RuntimeException} when vertex's id is unknown
     */
    public List<Integer> getPath(int srcVertexId, int destVertexId) {
        requireValidVertexId(srcVertexId);
        requireValidVertexId(destVertexId);

        Deque<Integer> stack = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        stack.add(srcVertexId);
        visited.add(srcVertexId);

        return dfs(srcVertexId, destVertexId, stack, visited) ? getPathFromStack(stack) : Collections.emptyList();
    }

    protected static List<Integer> getPathFromStack(Deque<Integer> stack) {
        List<Integer> path = new ArrayList<>(stack.size());

        while (!stack.isEmpty())
            path.add(stack.removeLast());

        return Collections.unmodifiableList(path);
    }

    protected boolean dfs(int srcVertexId, int destVertexId, Deque<Integer> stack, Set<Integer> visited) {
        if (srcVertexId == destVertexId)
            return true;

        for (Edge edge : adj.getOrDefault(srcVertexId, Collections.emptyMap()).values()) {
            if (visited.contains(edge.destVertexId))
                continue;

            stack.push(edge.destVertexId);
            visited.add(edge.destVertexId);

            if (dfs(edge.destVertexId, destVertexId, stack, visited))
                return true;

            stack.pop();
        }

        return false;
    }

    protected final void requireValidVertexId(int vertexId) {
        if (!vertices.containsKey(vertexId))
            throw new RuntimeException(String.format("vertex id '%d' does not exist", vertexId));
    }

    protected final void requireUniqueEdge(int srcVertexId, int destVertexId) {
        String edgeId = Edge.getId(srcVertexId, destVertexId);

        if (adj.getOrDefault(srcVertexId, Collections.emptyMap()).containsKey(edgeId))
            throw new RuntimeException(String.format("Edge '%d -> %d' duplication", srcVertexId, destVertexId));
    }

    @ToString
    @RequiredArgsConstructor
    protected static class Vertex<T> {

        private final int id;
        private final T val;

    }

    @ToString
    protected static class Edge {

        private final String id;
        private final int srcVertexId;
        private final int destVertexId;
        // private final int weight;

        public Edge(int srcVertexId, int destVertexId) {
            id = getId(srcVertexId, destVertexId);
            this.srcVertexId = srcVertexId;
            this.destVertexId = destVertexId;
        }

        public static String getId(int srcVertexId, int destVertexId) {
            return String.format("%d -> %d", srcVertexId, destVertexId);
        }

    }
}
