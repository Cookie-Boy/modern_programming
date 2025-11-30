package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.lab6.TEditor;

import static org.junit.jupiter.api.Assertions.*;

public class TEditorTest {

    @Test
    void testClear() {
        TEditor e = new TEditor();
        e.addDigit(3);
        e.clear();
        assertEquals(TEditor.ZERO, e.read());
    }

    @Test
    void testIsZero() {
        TEditor e = new TEditor();
        assertTrue(e.isZero());
        e.addDigit(5);
        assertFalse(e.isZero());
    }

    @Test
    void testAddDigit() {
        TEditor e = new TEditor();
        e.addDigit(7);
        assertTrue(e.read().endsWith("7"));
    }

    @Test
    void testAddDigitInvalid() {
        TEditor e = new TEditor();
        assertThrows(IllegalArgumentException.class, () -> e.addDigit(12));
    }

    @Test
    void testAddZero() {
        TEditor e = new TEditor();
        e.addZero();
        assertTrue(e.read().endsWith("0"));
    }

    @Test
    void testToggleRealSign() {
        TEditor e = new TEditor();
        e.toggleRealSign();
        assertTrue(e.read().startsWith("-"));
        e.toggleRealSign();
        assertFalse(e.read().startsWith("-"));
    }

    @Test
    void testToggleImagSign() {
        TEditor e = new TEditor();
        assertTrue(e.read().contains(" +i "));
        e.toggleImagSign();
        assertTrue(e.read().contains(" -i "));
        e.toggleImagSign();
        assertTrue(e.read().contains(" +i "));
    }

    @Test
    void testFractionSeparatorReal() {
        TEditor e = new TEditor();
        e.write("2,");
        e.addFractionSeparator();
        assertTrue(e.read().startsWith("2,,"));
    }

    @Test
    void testFractionSeparatorRealOnlyOnce() {
        TEditor e = new TEditor();
        e.addFractionSeparator();
        String before = e.read();
        e.addFractionSeparator();
        assertEquals(before, e.read());
    }

    @Test
    void testFractionSeparatorImag() {
        TEditor e = new TEditor();
        e.addPartsSeparator();
        e.addFractionSeparator();
        assertTrue(e.read().contains("i 0,,"));
    }

    @Test
    void testAddPartsSeparator() {
        TEditor e = new TEditor();
        e.addPartsSeparator();
        assertEquals(TEditor.ZERO, e.read());
    }

    @Test
    void testBackspace() {
        TEditor e = new TEditor();
        e.addDigit(3);
        String before = e.read();
        e.backspace();
        assertEquals(before.substring(0, before.length() - 1), e.read());
    }

    @Test
    void testEditCommands() {
        TEditor e = new TEditor();

        e.edit(1); // sign real
        assertTrue(e.read().startsWith("-"));

        e.edit(2); // sign imag
        assertTrue(e.read().contains(" -i "));

        e.edit(3); // digit 1
        assertTrue(e.read().endsWith("1"));

        e.edit(4); // zero
        assertTrue(e.read().endsWith("0"));

        e.edit(5); // запятая
        assertTrue(e.read().contains(","));

        e.edit(6); // parts sep
        assertTrue(e.hasPartsSeparator());

        e.edit(7); // backspace
        assertFalse(e.read().endsWith(","));

        e.edit(8); // clear
        assertEquals(TEditor.ZERO, e.read());
    }

    @Test
    void testEditInvalidCommand() {
        TEditor e = new TEditor();
        assertThrows(IllegalArgumentException.class, () -> e.edit(99));
    }
}

