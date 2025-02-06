package at.fhooe.sail.aud.aufgabe01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;

class GraphTest {

    private Graph graph;
    private MyVertex vA;
    private MyVertex vB;
    private MyVertex vC;
    private MyVertex vD;

    // A simple dummy class that implements MyVertex for testing.
    static class DummyVertex implements MyVertex {
        private final String name;

        DummyVertex(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DummyVertex{" + "name='" + name + '\'' + '}';
        }
    }

    @BeforeEach
    void setUp() {
        graph = new Graph(5);

        vA = new DummyVertex("A");
        vB = new DummyVertex("B");
        vC = new DummyVertex("C");
        vD = new DummyVertex("D");
    }

    @Test
    void testGraphInitialization() {
        assertEquals(0, graph.getNumberOfVertices());
        assertEquals(0, graph.getVertices().length);
    }

    @Test
    void testInsertVertexNormal() {
        int indexA = graph.insertVertex(vA);
        int indexB = graph.insertVertex(vB);

        assertEquals(0, indexA);
        assertEquals(1, indexB);
        assertEquals(2, graph.getNumberOfVertices());

        MyVertex[] vertices = graph.getVertices();
        assertEquals(vA, vertices[0]);
        assertEquals(vB, vertices[1]);
    }

    @Test
    void testInsertVertexNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> graph.insertVertex(null));
    }

    @Test
    void testInsertVertexExceedsMax() {
        // Graph max capacity is 5, we try to insert 6
        for (int i = 0; i < 5; i++) {
            graph.insertVertex(new DummyVertex("V" + i));
        }

        assertThrows(IndexOutOfBoundsException.class,
                () -> graph.insertVertex(new DummyVertex("Exceed")));
    }

    @Test
    void testInsertEdgeNormal() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);

        boolean inserted = graph.insertEdge(idxA, idxB, 10);
        assertTrue(inserted);

        assertEquals(10, graph.getEdgeWeight(idxA, idxB));
        assertEquals(10, graph.getEdgeWeight(idxB, idxA));
    }

    @Test
    void testInsertEdgeDuplicateShouldReturnFalse() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);

        boolean firstInsert = graph.insertEdge(idxA, idxB, 5);
        boolean secondInsert = graph.insertEdge(idxA, idxB, 5);

        assertTrue(firstInsert);
        assertFalse(secondInsert);
    }

    @Test
    void testInsertEdgeInvalidIndex() {
        int idxA = graph.insertVertex(vA);

        assertThrows(IllegalArgumentException.class,
                () -> graph.insertEdge(idxA, 1, 10));
    }

    @Test
    void testGetEdgeWeightNonExistentEdge() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);

        assertEquals(-1, graph.getEdgeWeight(idxA, idxB));
    }

    @Test
    void testGetEdgeWeightInvalidIndices() {
        int idxA = graph.insertVertex(vA);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getEdgeWeight(idxA, -1));

        assertThrows(IllegalArgumentException.class,
                () -> graph.getEdgeWeight(idxA, 10));
    }

    @Test
    void testGetAdjacencyMatrix() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);

        graph.insertEdge(idxA, idxB, 5);
        graph.insertEdge(idxB, idxC, 7);

        int[][] matrix = graph.getAdjacencyMatrix();

        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);

        assertEquals(5, matrix[idxA][idxB]);
        assertEquals(5, matrix[idxB][idxA]);

        assertEquals(7, matrix[idxB][idxC]);
        assertEquals(7, matrix[idxC][idxB]);

        assertEquals(-1, matrix[idxA][idxC]);
        assertEquals(-1, matrix[idxC][idxA]);
    }

    @Test
    void testGetAdjacentVertices() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);

        graph.insertEdge(idxA, idxB, 1);
        graph.insertEdge(idxA, idxC, 2);

        MyVertex[] adjacentToA = graph.getAdjacentVertices(idxA);
        Arrays.sort(adjacentToA, Comparator.comparing(MyVertex::toString));

        assertEquals(2, adjacentToA.length);
        assertTrue(Arrays.asList(adjacentToA).contains(vB));
        assertTrue(Arrays.asList(adjacentToA).contains(vC));

        MyVertex[] adjacentToB = graph.getAdjacentVertices(idxB);
        assertEquals(1, adjacentToB.length);
        assertEquals(vA, adjacentToB[0]);
    }

    @Test
    void testGetAdjacentVerticesInvalidIndex() {
        int idxA = graph.insertVertex(vA);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getAdjacentVertices(idxA + 1));
    }

    @Test
    void testToStringWithEdges() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);

        graph.insertEdge(idxA, idxB, 10);
        graph.insertEdge(idxB, idxC, 20);

        String expected = "Graph with 3 vertices (max: 5)\n"
                + "  Vertex 0: DummyVertex{name='A'}\n"
                + "  Vertex 1: DummyVertex{name='B'}\n"
                + "  Vertex 2: DummyVertex{name='C'}\n"
                + "Edges:\n"
                + "  (0) --10-- (1)\n"
                + "  (1) --20-- (2)\n";

        assertEquals(expected, graph.toString());
    }

    @Test
    void testToStringEmptyGraph() {
        String expected = "Graph with 0 vertices (max: 5)\nEdges:\n";
        assertEquals(expected, graph.toString());
    }

    @Test
    void testToStringWithVertices() {
        graph.insertVertex(vA);
        graph.insertVertex(vB);

        String expected = "Graph with 2 vertices (max: 5)\n"
                + "  Vertex 0: DummyVertex{name='A'}\n"
                + "  Vertex 1: DummyVertex{name='B'}\n"
                + "Edges:\n";
        assertEquals(expected, graph.toString());
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    ///////
    ///////    Here starts part two of the exercise
    ///////
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    @Test
    void testIsConnectedSingleComponent() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);

        graph.insertEdge(idxA, idxB, 1);
        graph.insertEdge(idxB, idxC, 1);

        assertTrue(graph.isConnected());
    }

    @Test
    void testIsConnectedMultipleComponents() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);
        int idxD = graph.insertVertex(vD);

        graph.insertEdge(idxA, idxB, 1);
        graph.insertEdge(idxC, idxD, 1);

        assertFalse(graph.isConnected());
    }

    @Test
    void testGetNumberOfComponents() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);
        int idxD = graph.insertVertex(vD);

        graph.insertEdge(idxA, idxB, 1);
        graph.insertEdge(idxC, idxD, 1);

        assertEquals(2, graph.getNumberOfComponents());
    }

    @Test
    void testIsCyclicTrue() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);

        graph.insertEdge(idxA, idxB, 1);
        graph.insertEdge(idxB, idxC, 1);
        graph.insertEdge(idxC, idxA, 1);

        assertTrue(graph.isCyclic());
    }

    @Test
    void testIsCyclicFalse() {
        int idxA = graph.insertVertex(vA);
        int idxB = graph.insertVertex(vB);
        int idxC = graph.insertVertex(vC);

        graph.insertEdge(idxA, idxB, 1);
        graph.insertEdge(idxB, idxC, 1);

        // Make sure to assert that the graph is NOT cyclic
        assertFalse(graph.isCyclic());
    }

}
