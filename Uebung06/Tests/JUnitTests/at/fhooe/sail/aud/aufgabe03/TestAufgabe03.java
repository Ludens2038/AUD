package at.fhooe.sail.aud.aufgabe03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class RoadmapTest {

    private Roadmap roadmap;

    @BeforeEach
    void setUp() {
        roadmap = new Roadmap(5);
    }

    @Test
    void testRoadmapKonstruktor() {
        assertEquals(0, roadmap.getNumberOfVertices());
    }

    @Test
    void testInsertStation() {
        Station wien = new Station("Wien");
        int index = roadmap.insertVertex(wien);

        assertEquals(0, index);
        assertEquals(1, roadmap.getNumberOfVertices());
        assertEquals("Wien", roadmap.getVertices()[0].toString());
    }

    @Test
    void testInsertStationsUndEdges() {
        Station wien = new Station("Wien");
        Station linz = new Station("Linz");
        Station graz = new Station("Graz");

        int idxWien = roadmap.insertVertex(wien);
        int idxLinz = roadmap.insertVertex(linz);
        int idxGraz = roadmap.insertVertex(graz);

        boolean edge1 = roadmap.insertEdge(idxWien, idxLinz, 200);
        boolean edge2 = roadmap.insertEdge(idxWien, idxGraz, 190);

        assertTrue(edge1);
        assertTrue(edge2);

        assertEquals(200, roadmap.getEdgeWeight(idxWien, idxLinz));
        assertEquals(190, roadmap.getEdgeWeight(idxWien, idxGraz));
    }

    @Test
    void testPrintShortestDistances() {
        Station wien = new Station("Wien");
        Station linz = new Station("Linz");
        Station stPoelten = new Station("St. Pölten");

        int idxWien = roadmap.insertVertex(wien);
        int idxLinz = roadmap.insertVertex(linz);
        int idxStPoelten = roadmap.insertVertex(stPoelten);

        roadmap.insertEdge(idxWien, idxLinz, 200);
        roadmap.insertEdge(idxWien, idxStPoelten, 80);
        roadmap.insertEdge(idxStPoelten, idxLinz, 120);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        roadmap.printShortestDistances(idxWien);

        System.setOut(originalOut);

        String printedOutput = outContent.toString();

        assertTrue(printedOutput.contains("from Wien"));
        assertTrue(printedOutput.contains("to Wien: 0km"));
        assertTrue(printedOutput.contains("to Linz: 200km")
                        || printedOutput.contains("to Linz: 200"));
        assertTrue(printedOutput.contains("to St. Pölten: 80km")
                        || printedOutput.contains("to St. Pölten: 80"));
    }

    @Test
    void testPrintShortestDistancesWithInvalidIndex() {
        Roadmap rm = new Roadmap(5);

        assertThrows(IllegalArgumentException.class, () -> {
            rm.printShortestDistances(99);
        });
    }

}

