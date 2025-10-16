import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.Lab2;

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
    public void testSumElements_ValidArrayWithMatches() {
        // Ветвь 3: условие i + j == maxColIndex выполняется
        double[][] A = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        assertEquals(3.0 + 5.0 + 7.0, Lab2.sumElements(A), 0.001);
    }

    @Test
    public void testSumElements_ValidArrayNoMatches() {
        // Ветвь 4: условие i + j == maxColIndex не выполняется (но циклы работают)
        double[][] A = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        assertEquals(2.0 + 3.0, Lab2.sumElements(A), 0.001);
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
        // Ветвь 4: A[i][j] >= min (не находим новый минимум, но элементы есть)
        double[][] A = {
                {5.0, 5.0},
                {5.0, 5.0}
        };
        assertEquals(5.0, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }

    @Test
    public void testMinAboveSecondaryDiagonal_MinAtFirst() {
        // Ветвь 5: минимум находится в первом элементе
        double[][] A = {
                {1.0, 5.0, 5.0},
                {2.0, 3.0, 4.0},
                {6.0, 7.0, 8.0}
        };
        assertEquals(1.0, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }

    @Test
    public void testMinAboveSecondaryDiagonal_MinAtLast() {
        // Ветвь 6: минимум находится в последнем проверяемом элементе
        double[][] A = {
                {5.0, 4.0, 3.0},
                {6.0, 2.0, 1.0},
                {1.0, 0.5, 0.1}
        };
        // Минимум 0.5 в [2,1] (последний проверяемый элемент)
        assertEquals(0.5, Lab2.minAboveSecondaryDiagonal(A), 0.001);
    }
}