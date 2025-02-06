package at.fhooe.sail.aud.aufgabe01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph implements MyVertex{
    private int maxVertices;
    private List<MyVertex> vertices;
    private List<Edge> edges;

    // Creates a new graph for a maximum of maxVertices nodes.
    public Graph(int maxVertices) {
        this.maxVertices = maxVertices;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    private static class Edge {
        int v1, v2, weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    // Returns the number of inserted nodes.
    public int getNumberOfVertices() {
        return vertices.size();
    }

    // Returns an array of length getNumVertices() with all inserted nodes.
    public MyVertex[] getVertices() {
        return vertices.toArray(new MyVertex[0]);
    }

    // Inserts new node v into the graph and returns its index in the node array.
    // Null elements are not allowed (IllegalArgumentException) and an IndexOutOfBoundsException
    // is thrown if the maximum number of nodes allowed is exceeded.
    public int insertVertex(MyVertex v) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (v == null) {
            throw new IllegalArgumentException("Null elements are not allowed");
        }
        if (vertices.size() >= maxVertices) {
            throw new IndexOutOfBoundsException("Maximum number of nodes exceeded");
        }
        vertices.add(v);
        return vertices.size() - 1;
    }

    // Returns the weight of the edge between indices v1 and v2 if it exists,
    // otherwise -1. In case of unknown node indices an IllegalArgumentException is thrown.
    public int getEdgeWeight(int v1, int v2) throws IllegalArgumentException {
        if (v1 < 0 || v2 < 0 || v1 >= vertices.size() || v2 >= vertices.size()){
            throw new IllegalArgumentException("Invalid vertex index");
        }
        for (Edge edge : edges) {
            if ((edge.v1 == v1 && edge.v2 == v2) || (edge.v1 == v2 && edge.v2 == v1)) {
                return edge.weight;
            }
        }
        return -1;
    }

    // Inserts an undirected edge with weight weight between the nodes with indices v1
    // and v2. The method returns false if the edge already exists, otherwise true.
    // In case of unknown node indices an IllegalArgumentException is thrown.
    public boolean insertEdge (int v1, int v2, int weight) throws IllegalArgumentException {
        if (v1 < 0 || v2 < 0 || v1 >= vertices.size() || v2 >= vertices.size())
            throw new IllegalArgumentException("Invalid vertex index");

        for (Edge edge : edges) {
            if ((edge.v1 == v1 && edge.v2 == v2) || (edge.v1 == v2 && edge.v2 == v1)) {
                return false; // Edge already exists
            }
        }

        edges.add(new Edge(v1, v2, weight));
        return true;
    }

    // Returns an NxN adjacency matrix for this graph, where N = getNumVertices().
    // The matrix contains the weights of the edges (>=0).
    public int[][] getAdjacencyMatrix() {
        int size = vertices.size();
        int[][] matrix = new int[size][size];

        for (int[] row : matrix) {
            Arrays.fill(row, -1);
        }

        for (Edge edge : edges) {
            matrix[edge.v1][edge.v2] = edge.weight;
            matrix[edge.v2][edge.v1] = edge.weight; // Since undirected
        }
        return matrix;
    }

    // Returns an array of nodes which are adjacent to the node with index v.
    // If the node index v is unknown, an IllegalArgumentException is thrown.
    public MyVertex[] getAdjacentVertices(int v) throws IllegalArgumentException {
        if (v < 0 || v >= vertices.size())
            throw new IllegalArgumentException("Invalid vertex index");

        List<MyVertex> adjacent = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.v1 == v) adjacent.add(vertices.get(edge.v2));
            if (edge.v2 == v) adjacent.add(vertices.get(edge.v1));
        }
        return adjacent.toArray(new MyVertex[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph with ").append(getNumberOfVertices())
                .append(" vertices (max: ").append(this.maxVertices).append(")\n");

        for (int i = 0; i < vertices.size(); i++) {
            sb.append("  Vertex ").append(i)
                    .append(": ").append(vertices.get(i).toString())
                    .append("\n");
        }

        sb.append("Edges:\n");
        for (Edge e : edges) {
            sb.append("  (").append(e.v1)
                    .append(") --").append(e.weight).append("-- (")
                    .append(e.v2).append(")\n");
        }

        return sb.toString();
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    ///////
    ///////    Here starts part two of the exercise
    ///////
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    public boolean isConnected() {
        if (vertices.isEmpty()) return false;
        boolean[] visited = new boolean[vertices.size()];
        dfs(0, visited);
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    public int getNumberOfComponents() {
        boolean[] visited = new boolean[vertices.size()];
        int components = 0;
        for (int i = 0; i < vertices.size(); i++) {
            if (!visited[i]) {
                dfs(i, visited);
                components++;
            }
        }
        return components;
    }

    public void printComponents() {
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            if (!visited[i]) {
                List<MyVertex> component = new ArrayList<>();
                dfsCollect(i, visited, component);
                System.out.println(component);
            }
        }
    }

    public boolean isCyclic() {
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            if (!visited[i]) {
                if (dfsCycle(i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (Edge edge : edges) {
            if (edge.v1 == v && !visited[edge.v2]) {
                dfs(edge.v2, visited);
            } else if (edge.v2 == v && !visited[edge.v1]) {
                dfs(edge.v1, visited);
            }
        }
    }

    private void dfsCollect(int v, boolean[] visited, List<MyVertex> component) {
        visited[v] = true;
        component.add(vertices.get(v));
        for (Edge edge : edges) {
            if (edge.v1 == v && !visited[edge.v2]) {
                dfsCollect(edge.v2, visited, component);
            } else if (edge.v2 == v && !visited[edge.v1]) {
                dfsCollect(edge.v1, visited, component);
            }
        }
    }

    private boolean dfsCycle(int v, int parent, boolean[] visited) {
        visited[v] = true;
        for (Edge edge : edges) {
            if (edge.v1 == v) {
                int neighbor = edge.v2;
                if (!visited[neighbor]) {
                    if (dfsCycle(neighbor, v, visited)) {
                        return true;
                    }
                }
                else if (neighbor != parent) {
                    return true;
                }
            }
            else if (edge.v2 == v) {
                int neighbor = edge.v1;
                if (!visited[neighbor]) {
                    if (dfsCycle(neighbor, v, visited)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }


}