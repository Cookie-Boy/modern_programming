import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.Lab1;

import static org.junit.jupiter.api.Assertions.*;

public class Lab1Test {

    @Test
    public void testOddMultiply_WithValidArray() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        double expected = 2.0 * 4.0;
        double result = Lab1.oddMultiply(array);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testOddMultiply_WithEmptyArray() {
        double[] array = new double[0];
        double result = Lab1.oddMultiply(array);
        assertEquals(0, result, 0.0001);
    }

    @Test
    public void testOddMultiply_WithNullArray() {
        double result = Lab1.oddMultiply(null);
        assertEquals(0, result, 0.0001);
    }

    @Test
    public void testCyclicShiftRight_WithPositiveShift() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] expected = {4.0, 5.0, 1.0, 2.0, 3.0};
        Lab1.cyclicShiftRight(array, 2);
        assertArrayEquals(expected, array, 0.0001);
    }

    @Test
    public void testCyclicShiftRight_WithNegativeShift() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] expected = {3.0, 4.0, 5.0, 1.0, 2.0};
        Lab1.cyclicShiftRight(array, -2);
        assertArrayEquals(expected, array, 0.0001);
    }

    @Test
    public void testCyclicShiftRight_WithZeroShift() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] expected = {1.0, 2.0, 3.0, 4.0, 5.0};
        Lab1.cyclicShiftRight(array, 0);
        assertArrayEquals(expected, array, 0.0001);
    }

    @Test
    public void testCyclicShiftRight_WithEmptyArray() {
        double[] array = new double[0];
        assertDoesNotThrow(() -> Lab1.cyclicShiftRight(array, 3));
    }

    @Test
    public void testCyclicShiftRight_WithNullArray() {
        assertDoesNotThrow(() -> Lab1.cyclicShiftRight(null, 3));
    }

    @Test
    public void testConvertFractionFromBase_Binary() {
        double result = Lab1.convertFractionFromBase(2, "101");
        double expected = 1*0.5 + 0*0.25 + 1*0.125;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testConvertFractionFromBase_EmptyString() {
        double result = Lab1.convertFractionFromBase(10, "");
        assertEquals(0, result, 0.0001);
    }

    @Test
    public void testConvertFractionFromBase_InvalidBase() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lab1.convertFractionFromBase(1, "101");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Lab1.convertFractionFromBase(37, "101");
        });
    }

    @Test
    public void testConvertFractionFromBase_InvalidCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lab1.convertFractionFromBase(10, "12A5");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Lab1.convertFractionFromBase(5, "67");
        });
    }

    @Test
    public void testConvertFractionFromBase_CaseInsensitive() {
        double result1 = Lab1.convertFractionFromBase(16, "a5");
        double result2 = Lab1.convertFractionFromBase(16, "A5");
        assertEquals(result1, result2, 0.0001);
    }
}