package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.lab7.TMemory;

import static org.junit.jupiter.api.Assertions.*;

public class TMemoryTest {

    @Test
    public void testConstructor() {
        TMemory<TFrac> m = new TMemory<>(new TFrac());
        assertEquals("0/1", m.readNumber().toString());
        assertEquals("(_Off".replace("(", "").replace(")", ""), m.readState());
    }

    @Test
    public void testStore() {
        TMemory<TFrac> m = new TMemory<>(new TFrac());
        m.store(new TFrac(3, 4));
        assertEquals("3/4", m.readNumber().toString());
        assertEquals("(_On".replace("(", "").replace(")", ""), m.readState());
    }

    @Test
    public void testLoad() {
        TMemory<TFrac> m = new TMemory<>(new TFrac());
        m.store(new TFrac(2, 3));
        TFrac v = m.load();
        assertEquals("2/3", v.toString());
        assertEquals("(_On".replace("(", "").replace(")", ""), m.readState());
    }

    @Test
    public void testAdd() {
        TMemory<TFrac> m = new TMemory<>(new TFrac());
        m.store(new TFrac(1, 2));
        m.add(new TFrac(1, 3)); // 1/2 + 1/3 = 5/6
        assertEquals("5/6", m.readNumber().toString());
        assertEquals("(_On".replace("(", "").replace(")", ""), m.readState());
    }

    @Test
    public void testClear() {
        TMemory<TFrac> m = new TMemory<>(new TFrac());
        m.store(new TFrac(5, 7));
        m.clear();
        assertEquals("0/1", m.readNumber().toString());
        assertEquals("(_Off".replace("(", "").replace(")", ""), m.readState());
    }
}

