package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TEditorTest {

    @Test
    public void testInitialZero() {
        TEditor ed = new TEditor();
        assertEquals("0, i* 0,", ed.getString());
        assertTrue(ed.isZero());
    }

    @Test
    public void testAddDigitReal() {
        TEditor ed = new TEditor();
        ed.addDigit(5);
        assertEquals("5, i* 0,", ed.getString());
    }

    @Test
    public void testAddDigitImag() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        ed.addDigit(7);
        assertEquals("0, i* 7,", ed.getString());
    }

    @Test
    public void testAddZeroReal() {
        TEditor ed = new TEditor();
        ed.addDigit(3);
        ed.addZero();
        assertEquals("30, i* 0,", ed.getString());
    }

    @Test
    public void testAddZeroImag() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        ed.addDigit(3);
        ed.addZero();
        assertEquals("0, i* 30,", ed.getString());
    }

    @Test
    public void testAddDotReal() {
        TEditor ed = new TEditor();
        ed.addDot();
        ed.addDigit(5);
        assertEquals("0.5, i* 0,", ed.getString());
    }

    @Test
    public void testAddDotImag() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        ed.addDot();
        ed.addDigit(8);
        assertEquals("0, i* 0.8,", ed.getString());
    }

    @Test
    public void testAddSignReal() {
        TEditor ed = new TEditor();
        ed.addDigit(4);
        ed.addSign();
        assertEquals("-4, i* 0,", ed.getString());
    }

    @Test
    public void testAddSignImag() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        ed.addDigit(6);
        ed.addSign();
        assertEquals("0, i* -6,", ed.getString());
    }

    @Test
    public void testSeparator() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        assertEquals("0, i* 0,", ed.getString());
    }

    @Test
    public void testBackspaceReal() {
        TEditor ed = new TEditor();
        ed.addDigit(1);
        ed.addDigit(2);
        ed.backspace();
        assertEquals("1, i* 0,", ed.getString());
    }

    @Test
    public void testBackspaceImag() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        ed.addDigit(5);
        ed.backspace();
        assertEquals("0, i* 0,", ed.getString()); // возвращение к imag=0
    }

    @Test
    public void testBackspaceRemoveSeparator() {
        TEditor ed = new TEditor();
        ed.addImaginarySeparator();
        ed.backspace();         // удаляет imag → убирает разделитель → только real
        assertEquals("0", ed.getString());
    }

    @Test
    public void testClear() {
        TEditor ed = new TEditor();
        ed.addDigit(4);
        ed.clear();
        assertEquals("0, i* 0,", ed.getString());
    }
}
