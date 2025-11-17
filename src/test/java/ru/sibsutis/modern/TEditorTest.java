package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TEditorTest {

    @Test
    public void testInitialState() {
        TEditor editor = new TEditor();
        assertTrue(editor.isZero());
        assertEquals("0, i*0,", editor.getString());
    }

    @Test
    public void testAddSign() {
        TEditor editor = new TEditor();
        editor.setString("5, i*3,");
        assertEquals("-5, -i*3,", editor.addSign());
        assertEquals("5, i*3,", editor.addSign());
    }

    @Test
    public void testAddDigit() {
        TEditor editor = new TEditor();
        editor.clear();
        assertEquals("5, i*0,", editor.addDigit(5));
        assertEquals("53, i*0,", editor.addDigit(3));
    }

    @Test
    public void testAddZero() {
        TEditor editor = new TEditor();
        editor.clear();
        assertEquals("0, i*0,", editor.addZero());
        assertNotEquals("00, i*0,", editor.addZero());
    }

    @Test
    public void testBackspace() {
        TEditor editor = new TEditor();
        editor.setString("123");
        assertEquals("12", editor.backspace());
        assertEquals("1", editor.backspace());
        assertEquals("0, i*0,", editor.backspace());
    }

    @Test
    public void testClear() {
        TEditor editor = new TEditor();
        editor.setString("15, i*7,");
        assertEquals("0, i*0,", editor.clear());
        assertTrue(editor.isZero());
    }

    @Test
    public void testEditCommands() {
        TEditor editor = new TEditor();
        editor.clear();
        assertEquals("5, i*0,", editor.edit(8)); // 8 = 5+3
        assertEquals("-5, -i*0,", editor.edit(1)); // add sign
        assertEquals("-", editor.edit(2)); // backspace
        assertEquals("0, i*0,", editor.edit(0)); // clear
        assertEquals("0, i*0,", editor.edit(13)); // add zero
    }

    @Test
    public void testInvalidDigit() {
        TEditor editor = new TEditor();

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> editor.addDigit(10));
        assertEquals("Digit must be between 0 and 9", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> editor.addDigit(-1));
        assertEquals("Digit must be between 0 and 9", exception2.getMessage());
    }

    @Test
    public void testInvalidCommand() {
        TEditor editor = new TEditor();

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> editor.edit(100));
        assertEquals("Invalid command", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> editor.edit(-5));
        assertEquals("Invalid command", exception2.getMessage());
    }

    @Test
    public void testComplexNumberFormat() {
        TEditor editor = new TEditor();

        editor.setString("3, i*5,");
        assertEquals("3, i*5,", editor.getString());

        editor.setString("3, i*5,");
        assertEquals("-3, -i*5,", editor.addSign());

        editor.setString("3");
        assertEquals("-3", editor.addSign());
    }

    @Test
    public void testAddDot() {
        TEditor editor = new TEditor();

        editor.setString("0");
        assertEquals("0.", editor.addDot());

        editor.setString("12");
        assertEquals("12.", editor.addDot());

        editor.setString("12.3");
        assertEquals("12.3", editor.addDot()); // повторная точка запрещена
    }

    @Test
    public void testRealWithDigits() {
        TEditor editor = new TEditor();

        editor.setString("0");
        editor.addDot();
        editor.addDigit(5);
        editor.addDigit(7);

        assertEquals("0.57", editor.getString());
    }

    @Test
    public void testBackspaceReal() {
        TEditor editor = new TEditor();

        editor.setString("12.3");
        assertEquals("12.", editor.backspace());
        assertEquals("12", editor.backspace());
    }

    @Test
    public void testDotWithNegative() {
        TEditor editor = new TEditor();

        editor.setString("-0");
        assertEquals("-0.", editor.addDot());
        assertEquals("-0.5", editor.addDigit(5));
    }
}