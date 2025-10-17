package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Lab2Test {

    @Test
    public void testMax_FirstGreater() {
        assertEquals(5.0, Lab2.max(5.0, 3.0), 0.001);
    }

    @Test
    public void testMax_SecondGreater() {
        assertEquals(7.0, Lab2.max(3.0, 7.0), 0.001);
    }

    @Test
    public void testSumElements_NullArray() {
        assertEquals(0.0, Lab2.sumElements(null), 0.001);
    }

    @Test
    public void testSumElements_EmptyArray() {
        assertEquals(0.0, Lab2.sumElements(new double[0][]), 0.001);
    }

    @Test
    public void testSumElements_ValidSquareArray() {
        double[][] A = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        assertEquals(3.0 + 5.0 + 7.0, Lab2.sumElements(A), 0.001);
    }

    @Test
    public void testSumElements_NoMatches() {
        double[][] A = {
                {1.0},
                {2.0}
        };
        assertEquals(1.0, Lab2.sumElements(A), 0.001);
    }

    @Test
    public void testMinAboveSecondaryDiagonal_NullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lab2.minAboveSecondaryDiagonal(null);
        });
    }

    @Test
    public void testMinAboveSecondaryDiagonal_EmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lab2.minAboveSecondaryDiagonal(new double[0][]);
        });
    }

    @Test
    public void testMinAboveSecondaryDiagonal_ValidArrayMinFound() {
        double[][] A = {
                {5.0, 2.0, 8.0},
                {1.0, 4.0, 6.0},
                {9.0, 7.0, 3.0}
        };
        assertEquals(1.0, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }

    @Test
    public void testMinAboveSecondaryDiagonal_AllEqual() {
        double[][] A = {
                {5.0, 5.0},
                {5.0, 5.0}
        };
        assertEquals(5.0, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }

    @Test
    public void testMinAboveSecondaryDiagonal_MinAtFirst() {
        double[][] A = {
                {1.0, 5.0, 5.0},
                {2.0, 3.0, 4.0},
                {6.0, 7.0, 8.0}
        };
        assertEquals(1.0, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }

    @Test
    public void testMinAboveSecondaryDiagonal_MinAtLast() {
        double[][] A = {
                {5.0, 4.0, 3.0},
                {6.0, 2.0, 1.0},
                {1.0, 0.5, 0.1}
        };
        assertEquals(1.0, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }
}