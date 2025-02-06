package at.fhooe.sail.aud.aufgabe03;

import at.fhooe.sail.aud.aufgabe01.Graph;

import java.util.Arrays;

public class Roadmap extends Graph {

    // Creates a new roadmap for up to maxStations stations.
    public Roadmap(int maxStations) {
        super(maxStations);
    }

    // Returns (or outputs) the shortest paths from the station "from" to all reachable destinations.
    public void printShortestDistances(int from) {
        int n = getNumberOfVertices();
        if (from < 0 || from >= n) {
            throw new IllegalArgumentException("Invalid station index: " + from);
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[from] = 0;

        int[][] adjacencyMatrix = getAdjacencyMatrix();

        for (int i = 0; i < n; i++) {
            int u = -1;
            int minDist = Integer.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && dist[v] < minDist) {
                    u = v;
                    minDist = dist[v];
                }
            }

            if (u == -1) {
                break;
            }

            visited[u] = true;

            for (int w = 0; w < n; w++) {
                if (adjacencyMatrix[u][w] >= 0 && !visited[w]) {
                    int newDist = dist[u] + adjacencyMatrix[u][w];
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                    }
                }
            }
        }

        System.out.println("from " + getVertices()[from]);
        for (int i = 0; i < n; i++) {
            if (dist[i] < Integer.MAX_VALUE) {
                System.out.println("  to " + getVertices()[i] + ": " + dist[i] + "km");
            }
        }
    }
}

