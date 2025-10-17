package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TPNumberTest {

    @Test
    public void testNumberConstructorValid() {
        TPNumber num = new TPNumber(10.4, 5, 2);
        assertEquals(5.8, num.getNum(), 0.001);
        assertEquals(5, num.getBase());
        assertEquals(2, num.getC());
    }

    @Test
    public void testStringConstructorValid() {
        TPNumber num = new TPNumber("10.4", "5", "2");
        assertEquals(5.8, num.getNum(), 0.001);
        assertEquals(5, num.getBase());
        assertEquals(2, num.getC());
    }

    @Test
    public void testConstructorInvalidBase() {
        assertThrows(NumberFormatException.class, () -> new TPNumber(10.5, 1, 2));
        assertThrows(NumberFormatException.class, () -> new TPNumber(10.5, 17, 2));
        assertThrows(NumberFormatException.class, () -> new TPNumber("10.5", "1", "2"));
        assertThrows(NumberFormatException.class, () -> new TPNumber("10.5", "17", "2"));
    }

    @Test
    public void testConstructorInvalidPrecision() {
        assertThrows(NumberFormatException.class, () -> new TPNumber(10.5, 10, -1));
        assertThrows(NumberFormatException.class, () -> new TPNumber("10.5", "10", "-1"));
    }

    @Test
    public void testConstructorInvalidNumberFormat() {
        assertThrows(NumberFormatException.class, () -> new TPNumber("12", "2", "2"));
        assertThrows(NumberFormatException.class, () -> new TPNumber("G", "16", "2"));
        assertThrows(NumberFormatException.class, () -> new TPNumber("10.5.3", "10", "2"));
    }

    @Test
    public void testAddValid() {
        TPNumber num1 = new TPNumber(5.0, 10, 2);
        TPNumber num2 = new TPNumber(3.5, 10, 2);
        TPNumber result = num1.add(num2);

        assertEquals(8.5, result.getNum(), 0.001);
        assertEquals(10, result.getBase());
        assertEquals(2, result.getC());
    }

    @Test
    public void testAddInvalid() {
        TPNumber num1 = new TPNumber(5.0, 10, 2);
        TPNumber num2 = new TPNumber(3.5, 8, 2);
        TPNumber num3 = new TPNumber(3.5, 10, 3);

        assertThrows(NumberFormatException.class, () -> num1.add(num2));
        assertThrows(NumberFormatException.class, () -> num1.add(num3));
    }

    @Test
    public void testSubtractValid() {
        TPNumber num1 = new TPNumber(10.0, 10, 2);
        TPNumber num2 = new TPNumber(3.5, 10, 2);
        TPNumber result = num1.subtract(num2);

        assertEquals(6.5, result.getNum(), 0.001);
    }

    @Test
    public void testSubtractInvalid() {
        TPNumber num1 = new TPNumber(5.0, 10, 2);
        TPNumber num2 = new TPNumber(3.5, 8, 2);
        TPNumber num3 = new TPNumber(3.5, 10, 3);

        assertThrows(NumberFormatException.class, () -> num1.subtract(num2));
        assertThrows(NumberFormatException.class, () -> num1.subtract(num3));
    }

    @Test
    public void testMultiply() {
        TPNumber num1 = new TPNumber(4.0, 10, 2);
        TPNumber num2 = new TPNumber(2.5, 10, 2);
        TPNumber result = num1.multiply(num2);

        assertEquals(10.0, result.getNum(), 0.001);
    }

    @Test
    public void testMultiplyInvalid() {
        TPNumber num1 = new TPNumber(5.0, 10, 2);
        TPNumber num2 = new TPNumber(3.5, 8, 2);
        TPNumber num3 = new TPNumber(3.5, 10, 3);

        assertThrows(NumberFormatException.class, () -> num1.multiply(num2));
        assertThrows(NumberFormatException.class, () -> num1.multiply(num3));
    }

    @Test
    public void testDivide() {
        TPNumber num1 = new TPNumber(10.0, 10, 2);
        TPNumber num2 = new TPNumber(4.0, 10, 2);
        TPNumber result = num1.divide(num2);

        assertEquals(2.5, result.getNum(), 0.001);
    }

    @Test
    public void testDivideInvalid() {
        TPNumber num1 = new TPNumber(5.0, 10, 2);
        TPNumber num2 = new TPNumber(3.5, 8, 2);
        TPNumber num3 = new TPNumber(3.5, 10, 3);

        assertThrows(NumberFormatException.class, () -> num1.divide(num2));
        assertThrows(NumberFormatException.class, () -> num1.divide(num3));
    }

    @Test
    public void testDivide_ByZero() {
        TPNumber num1 = new TPNumber(10.0, 10, 2);
        TPNumber num2 = new TPNumber(0.0, 10, 2);

        assertThrows(ArithmeticException.class, () -> num1.divide(num2));
    }

    @Test
    public void testInverse() {
        TPNumber num = new TPNumber(4.0, 10, 3);
        TPNumber result = num.inverse();

        assertEquals(0.25, result.getNum(), 0.001);
    }

    @Test
    public void testInverse_Zero() {
        TPNumber num = new TPNumber(0.0, 10, 2);
        assertThrows(ArithmeticException.class, num::inverse);
    }

    @Test
    public void testSquare() {
        TPNumber num = new TPNumber(5.0, 10, 2);
        TPNumber result = num.square();

        assertEquals(25.0, result.getNum(), 0.001);
    }

    // Тесты вспомогательных методов
    @Test
    public void testCopy() {
        TPNumber original = new TPNumber(7.5, 8, 3);
        TPNumber copy = original.copy();

        assertEquals(original.getNum(), copy.getNum(), 0.001);
        assertEquals(original.getBase(), copy.getBase());
        assertEquals(original.getC(), copy.getC());
        assertNotSame(original, copy);
    }

    @Test
    public void testGetNumberString() {
        TPNumber decNum = new TPNumber(10.75, 10, 2);
        assertEquals("10.75", decNum.getNumberString());
        TPNumber binNum = new TPNumber(10.5, 2, 4);
        assertEquals("1010.1000", binNum.getNumberString());
        TPNumber hexNum = new TPNumber(26.75, 16, 2);
        assertEquals("1A.C0", hexNum.getNumberString());
    }

    @Test
    public void testGetNumberString_EdgeCases() {
        // Нулевое значение
        TPNumber zero = new TPNumber(0.0, 10, 2);
        assertEquals("0.00", zero.getNumberString());

        // Только целая часть
        TPNumber integerOnly = new TPNumber(15.0, 10, 0);
        assertEquals("15", integerOnly.getNumberString());

        // Только дробная часть
        TPNumber fractionalOnly = new TPNumber(0.75, 10, 2);
        assertEquals("0.75", fractionalOnly.getNumberString());
    }

    @Test
    public void testFractionalPrecision() {
        TPNumber num1 = new TPNumber(10.123456, 10, 2);
        assertEquals(10.12, num1.getNum(), 0.001);

        TPNumber num2 = new TPNumber(10.129, 10, 2);
        assertEquals(10.13, num2.getNum(), 0.001);
    }

    @Test
    public void testDifferentBasesWithFractions() {
        TPNumber binFraction = new TPNumber("1010.101", "2", "3");
        assertEquals(10.625, binFraction.getNum(), 0.001);
        TPNumber octFraction = new TPNumber("12.34", "8", "2");
        assertEquals(10.4375, octFraction.getNum(), 0.001);
        TPNumber hexFraction = new TPNumber("A.BC", "16", "2");
        assertEquals(10.734375, hexFraction.getNum(), 0.001);
    }

    @Test
    public void testCommaSeparator() {
        // Тест с запятой как разделителем дробной части
        TPNumber num = new TPNumber("10,75", "10", "2");
        assertEquals(10.75, num.getNum(), 0.001);
    }
}