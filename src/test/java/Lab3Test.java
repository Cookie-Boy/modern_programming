import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.Lab3;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab3Test {
    @Test
    public void testRadixCreator_Zero() {
        assertEquals(Integer.valueOf(0), Lab3.radixCreator(0));
    }

    @Test
    public void testRadixCreator_SingleDigit() {
        assertEquals(Integer.valueOf(5), Lab3.radixCreator(5));
    }

    @Test
    public void testRadixCreator_TwoDigits_OddPositions() {
        assertEquals(Integer.valueOf(531), Lab3.radixCreator(12345));
    }

    @Test
    public void testRadixCreator_NegativeNumber() {
        assertEquals(Integer.valueOf(135), Lab3.radixCreator(-654321));
    }

    @Test
    public void testMaxEvenInEvenPos_Zero() {
        assertEquals(-1, Lab3.maxEvenInEvenPos(0));
    }

    @Test
    public void testMaxEvenInEvenPos_SingleDigit_OddPosition() {
        assertEquals(-1, Lab3.maxEvenInEvenPos(2));
    }

    @Test
    public void testMaxEvenInEvenPos_FirstEvenPosition_EvenDigit() {
        assertEquals(-1, Lab3.maxEvenInEvenPos(14));
    }

    @Test
    public void testMaxEvenInEvenPos_EvenPosition_OddDigit() {
        assertEquals(-1, Lab3.maxEvenInEvenPos(13));
    }

    @Test
    public void testMaxEvenInEvenPos_MultipleEvenPositions_UpdateMax() {
        assertEquals(-1, Lab3.maxEvenInEvenPos(123456));
    }

    @Test
    public void testMaxEvenInEvenPos_NegativeNumber() {
        assertEquals(-1, Lab3.maxEvenInEvenPos(-1234));
    }

    @Test
    public void testCyclicShiftRight_Zero() {
        assertEquals(0, Lab3.cyclicShiftRight(0, 2));
    }

    @Test
    public void testCyclicShiftRight_SingleDigit_ZeroPositions() {
        assertEquals(5, Lab3.cyclicShiftRight(5, 1));
    }

    @Test
    public void testCyclicShiftRight_SingleDigit_NonZeroPositions() {
        assertEquals(5, Lab3.cyclicShiftRight(5, 2));
    }

    @Test
    public void testCyclicShiftRight_MultipleDigits_ZeroShift() {
        assertEquals(123, Lab3.cyclicShiftRight(123, 3));
    }

    @Test
    public void testCyclicShiftRight_MultipleDigits_PositiveShift() {
        assertEquals(561234, Lab3.cyclicShiftRight(123456, 2));
    }

    @Test
    public void testCyclicShiftRight_MultipleDigits_LargeShift() {
        assertEquals(456123, Lab3.cyclicShiftRight(123456, 9)); // 9 % 6 = 3
    }

    @Test
    public void testCyclicShiftRight_NegativeNumber() {
        assertEquals(561234, Lab3.cyclicShiftRight(-123456, 2));
    }

    @Test
    public void testSumEvenElements_NullArray() {
        assertEquals(0, Lab3.sumEvenElements(null));
    }

    @Test
    public void testSumEvenElements_EmptyArray() {
        assertEquals(0, Lab3.sumEvenElements(new int[0][]));
    }

    @Test
    public void testSumEvenElements_SingleRow_NullRow() {
        int[][] A = {null};
        assertEquals(0, Lab3.sumEvenElements(A));
    }

    @Test
    public void testSumEvenElements_SingleRow_EmptyRow() {
        int[][] A = {new int[0]};
        assertEquals(0, Lab3.sumEvenElements(A));
    }

    @Test
    public void testSumEvenElements_SingleElement_Odd() {
        int[][] A = {{1}};
        assertEquals(0, Lab3.sumEvenElements(A));
    }

    @Test
    public void testSumEvenElements_SingleElement_Even() {
        int[][] A = {{2}};
        assertEquals(2, Lab3.sumEvenElements(A));
    }

    @Test
    public void testSumEvenElements_MultipleRows_EvenElements() {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertEquals(2 + 4, Lab3.sumEvenElements(A));
    }

    @Test
    public void testSumEvenElements_AllOddElements() {
        int[][] A = {
                {1, 3, 5},
                {7, 9, 11}
        };
        assertEquals(0, Lab3.sumEvenElements(A));
    }

    @Test
    public void testSumEvenElements_RectangularArray() {
        int[][] A = {
                {2, 4, 6},
                {8, 10}
        };
        assertEquals(12, Lab3.sumEvenElements(A));
    }
}