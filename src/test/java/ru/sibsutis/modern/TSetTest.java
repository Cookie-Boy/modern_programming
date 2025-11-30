package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.lab10.TSet;

import static org.junit.jupiter.api.Assertions.*;

public class TSetTest {

    @Test
    public void testEmpty() {
        TSet<Integer> s = new TSet<>();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    @Test
    public void testAdd() {
        TSet<Integer> s = new TSet<>();
        s.add(5);
        assertFalse(s.isEmpty());
        assertTrue(s.contains(5));
    }

    @Test
    public void testRemove() {
        TSet<Integer> s = new TSet<>();
        s.add(3);
        s.remove(3);
        assertFalse(s.contains(3));
    }

    @Test
    public void testUnion() {
        TSet<Integer> a = new TSet<>();
        TSet<Integer> b = new TSet<>();

        a.add(1);
        a.add(2);

        b.add(3);
        b.add(2);

        TSet<Integer> c = a.union(b);

        assertEquals(3, c.size());
        assertTrue(c.contains(1));
        assertTrue(c.contains(2));
        assertTrue(c.contains(3));
    }

    @Test
    public void testSubtract() {
        TSet<Integer> a = new TSet<>();
        TSet<Integer> b = new TSet<>();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(2);

        TSet<Integer> c = a.subtract(b);

        assertEquals(2, c.size());
        assertTrue(c.contains(1));
        assertTrue(c.contains(3));
    }

    @Test
    public void testIntersect() {
        TSet<Integer> a = new TSet<>();
        TSet<Integer> b = new TSet<>();

        a.add(1);
        a.add(2);
        b.add(2);
        b.add(3);

        TSet<Integer> c = a.intersect(b);

        assertEquals(1, c.size());
        assertTrue(c.contains(2));
    }

    @Test
    public void testGet() {
        TSet<Integer> s = new TSet<>();
        s.add(5);
        s.add(1);
        s.add(3);

        // TreeSet: (1,3,5)
        assertEquals(1, s.get(1));
        assertEquals(3, s.get(2));
        assertEquals(5, s.get(3));
    }

    @Test
    public void testGetOutOfBounds() {
        TSet<Integer> s = new TSet<>();
        s.add(1);

        assertThrows(IndexOutOfBoundsException.class, () -> s.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> s.get(2));
    }
}
