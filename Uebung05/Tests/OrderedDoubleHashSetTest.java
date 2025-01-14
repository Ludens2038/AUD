import at.fhooe.mc.aud.aufgabe02.OrderedDoubleHashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderedDoubleHashSetTest {

    OrderedDoubleHashSet empty, filled;

    @BeforeEach
    void setUp() {
        empty = new OrderedDoubleHashSet(7);

        filled = new OrderedDoubleHashSet(7);
        filled.insert(7);
        filled.insert(1);
        filled.insert(2);
    }

    @Test
    void testInsert() {
        assertTrue(empty.insert(14));
        assertTrue(filled.insert(14));
        assertTrue(filled.insert(12));
        for (int i = 0; i < filled.size; i++) {
            System.out.println(filled.table[i]);
        }
        assertFalse(filled.insert(14));
    }

}
