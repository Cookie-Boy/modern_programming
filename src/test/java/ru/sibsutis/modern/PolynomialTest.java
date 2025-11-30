package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.sibsutis.modern.lab9.TMember;
import ru.sibsutis.modern.lab9.TPoly;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    private TMember member;
    private TPoly poly;

    @BeforeEach
    void setUp() {
        member = new TMember();
        poly = new TPoly();
    }

    @Test
    void testTMemberDefaultConstructor() {
        assertEquals(0, member.getCoefficient());
        assertEquals(0, member.getDegree());
        assertTrue(member.isZero());
    }

    @Test
    void testTMemberParameterizedConstructor() {
        TMember m = new TMember(5, 3);
        assertEquals(5, m.getCoefficient());
        assertEquals(3, m.getDegree());
        assertFalse(m.isZero());
    }

    @Test
    void testTMemberCopyConstructor() {
        TMember original = new TMember(4, 2);
        TMember copy = new TMember(original);
        assertEquals(original.getCoefficient(), copy.getCoefficient());
        assertEquals(original.getDegree(), copy.getDegree());
        assertNotSame(original, copy);
    }

    @Test
    void testTMemberDifferentiate() {
        TMember m1 = new TMember(3, 2);
        TMember derivative1 = m1.differentiate();
        assertEquals(6, derivative1.getCoefficient());
        assertEquals(1, derivative1.getDegree());

        TMember m2 = new TMember(5, 0); // константа
        TMember derivative2 = m2.differentiate();
        assertTrue(derivative2.isZero());

        TMember m3 = new TMember(4, 1);
        TMember derivative3 = m3.differentiate();
        assertEquals(4, derivative3.getCoefficient());
        assertEquals(0, derivative3.getDegree());
    }

    @Test
    void testTMemberEvaluate() {
        TMember m = new TMember(2, 3); // 2x^3
        assertEquals(16.0, m.evaluate(2.0), 1e-10);
        assertEquals(0.0, m.evaluate(0.0), 1e-10);
        assertEquals(-2.0, m.evaluate(-1.0), 1e-10);
    }

    @Test
    void testTMemberToString() {
        assertEquals("0", new TMember(0, 5).toString());
        assertEquals("5", new TMember(5, 0).toString());
        assertEquals("3x", new TMember(3, 1).toString());
        assertEquals("-2x", new TMember(-2, 1).toString());
        assertEquals("x^2", new TMember(1, 2).toString());
        assertEquals("-x^3", new TMember(-1, 3).toString());
        assertEquals("4x^2", new TMember(4, 2).toString());
    }

    @Test
    void testTMemberEquals() {
        TMember m1 = new TMember(3, 2);
        TMember m2 = new TMember(3, 2);
        TMember m3 = new TMember(3, 3);
        TMember m4 = new TMember(4, 2);

        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
        assertFalse(m1.equals(m4));
    }

    @Test
    void testTPolyMonomialConstructor() {
        TPoly p = new TPoly(4, 3); // 4x^3
        assertFalse(p.isZero());
        assertEquals(3, p.getDegree());
        assertEquals(4, p.getCoefficient(3));
    }

    @Test
    void testTPolyZeroMonomialConstructor() {
        TPoly p = new TPoly(0, 5);
        assertTrue(p.isZero());
        assertEquals(0, p.getDegree());
    }

    @Test
    void testTPolyCopyConstructor() {
        TPoly original = new TPoly(3, 2);
        TPoly copy = new TPoly(original);
        assertTrue(original.equals(copy));
        assertNotSame(original, copy);
    }

    @Test
    void testTPolyGetDegree() {
        TPoly p1 = new TPoly(); // нулевой полином
        assertEquals(0, p1.getDegree());

        TPoly p2 = new TPoly(1, 0); // константа
        assertEquals(0, p2.getDegree());

        TPoly p3 = new TPoly(2, 3); // 2x^3
        assertEquals(3, p3.getDegree());

        // Полином 3x^2 + 4x^5
        List<TMember> members = Arrays.asList(
                new TMember(3, 2),
                new TMember(4, 5)
        );
        TPoly p4 = new TPoly(members);
        assertEquals(5, p4.getDegree());
    }

    @Test
    void testTPolyGetCoefficient() {
        // Полином 2x^3 + 3x^1 + 5
        List<TMember> members = Arrays.asList(
                new TMember(2, 3),
                new TMember(3, 1),
                new TMember(5, 0)
        );
        TPoly p = new TPoly(members);

        assertEquals(2, p.getCoefficient(3));
        assertEquals(3, p.getCoefficient(1));
        assertEquals(5, p.getCoefficient(0));
        assertEquals(0, p.getCoefficient(2));
        assertEquals(0, p.getCoefficient(4));
    }

    @Test
    void testTPolyClear() {
        TPoly p = new TPoly(3, 2);
        assertFalse(p.isZero());

        p.clear();
        assertTrue(p.isZero());
        assertEquals(0, p.getDegree());
    }

    @Test
    void testTPolyAdd() {
        // (2x^2 + 3x) + (x^2 + 4) = 3x^2 + 3x + 4
        TPoly p1 = new TPoly(Arrays.asList(
                new TMember(2, 2),
                new TMember(3, 1)
        ));
        TPoly p2 = new TPoly(Arrays.asList(
                new TMember(1, 2),
                new TMember(4, 0)
        ));

        TPoly result = p1.add(p2);

        assertEquals(3, result.getCoefficient(2));
        assertEquals(3, result.getCoefficient(1));
        assertEquals(4, result.getCoefficient(0));
    }

    @Test
    void testTPolyAddWithZero() {
        TPoly zero = new TPoly();
        TPoly p = new TPoly(3, 2);

        TPoly result1 = zero.add(p);
        TPoly result2 = p.add(zero);

        assertTrue(p.equals(result1));
        assertTrue(p.equals(result2));
    }

    @Test
    void testTPolyMultiply() {
        // (x + 1) * (x - 1) = x^2 - 1
        TPoly p1 = new TPoly(Arrays.asList(
                new TMember(1, 1),
                new TMember(1, 0)
        ));
        TPoly p2 = new TPoly(Arrays.asList(
                new TMember(1, 1),
                new TMember(-1, 0)
        ));

        TPoly result = p1.multiply(p2);

        assertEquals(1, result.getCoefficient(2));
        assertEquals(0, result.getCoefficient(1));
        assertEquals(-1, result.getCoefficient(0));
    }

    @Test
    void testTPolyMultiplyByZero() {
        TPoly zero = new TPoly();
        TPoly p = new TPoly(2, 3);

        TPoly result1 = zero.multiply(p);
        TPoly result2 = p.multiply(zero);

        assertTrue(result1.isZero());
        assertTrue(result2.isZero());
    }

    @Test
    void testTPolySubtract() {
        // (3x^2 + 2x) - (x^2 + x) = 2x^2 + x
        TPoly p1 = new TPoly(Arrays.asList(
                new TMember(3, 2),
                new TMember(2, 1)
        ));
        TPoly p2 = new TPoly(Arrays.asList(
                new TMember(1, 2),
                new TMember(1, 1)
        ));

        TPoly result = p1.subtract(p2);

        assertEquals(2, result.getCoefficient(2));
        assertEquals(1, result.getCoefficient(1));
        assertEquals(0, result.getCoefficient(0));
    }

    @Test
    void testTPolyNegate() {
        TPoly p = new TPoly(Arrays.asList(
                new TMember(3, 2),
                new TMember(-2, 1),
                new TMember(1, 0)
        ));

        TPoly negated = p.negate();

        assertEquals(-3, negated.getCoefficient(2));
        assertEquals(2, negated.getCoefficient(1));
        assertEquals(-1, negated.getCoefficient(0));
    }

    @Test
    void testTPolyEquals() {
        TPoly p1 = new TPoly(Arrays.asList(
                new TMember(2, 3),
                new TMember(3, 1)
        ));
        TPoly p2 = new TPoly(Arrays.asList(
                new TMember(2, 3),
                new TMember(3, 1)
        ));
        TPoly p3 = new TPoly(Arrays.asList(
                new TMember(2, 3),
                new TMember(4, 1)
        ));

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    @Test
    void testTPolyDifferentiate() {
        // 3x^2 + 2x + 1 -> 6x + 2
        TPoly p = new TPoly(Arrays.asList(
                new TMember(3, 2),
                new TMember(2, 1),
                new TMember(1, 0)
        ));

        TPoly derivative = p.differentiate();

        assertEquals(6, derivative.getCoefficient(1));
        assertEquals(2, derivative.getCoefficient(0));
        assertEquals(0, derivative.getCoefficient(2));
    }

    @Test
    void testTPolyEvaluate() {
        // x^2 + 2x + 1
        TPoly p = new TPoly(Arrays.asList(
                new TMember(1, 2),
                new TMember(2, 1),
                new TMember(1, 0)
        ));

        assertEquals(1.0, p.evaluate(0.0), 1e-10);
        assertEquals(4.0, p.evaluate(1.0), 1e-10);
        assertEquals(9.0, p.evaluate(2.0), 1e-10);
        assertEquals(0.0, p.evaluate(-1.0), 1e-10);
    }

    @Test
    void testTPolyGetMember() {
        TPoly p = new TPoly(Arrays.asList(
                new TMember(2, 3),
                new TMember(3, 1)
        ));

        TMember member1 = p.getMember(0);
        TMember member2 = p.getMember(1);

        assertNotNull(member1);
        assertNotNull(member2);

        // Проверяем, что возвращаются копии, а не оригиналы
        assertNotSame(p.getMember(0), p.getMember(0));
    }

    @Test
    void testTPolyGetMemberInvalidIndex() {
        TPoly p = new TPoly(1, 2);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p.getMember(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p.getMember(5);
        });
    }

    @Test
    void testTPolyToString() {
        assertEquals("0", new TPoly().toString());
        assertEquals("3", new TPoly(3, 0).toString());
        assertEquals("2x", new TPoly(2, 1).toString());
        assertEquals("3x^2", new TPoly(3, 2).toString());

        TPoly p = new TPoly(Arrays.asList(
                new TMember(2, 2),
                new TMember(-3, 1),
                new TMember(1, 0)
        ));
        assertEquals("2x^2 - 3x + 1", p.toString());
    }

    // Тесты из таблицы 3 (умножение полиномов)

    @Test
    void testMultiplicationFromTable() {
        // Тест 1: 0*X^0 * 0*X^0 = 0*X^0
        TPoly zero1 = new TPoly();
        TPoly zero2 = new TPoly();
        TPoly result1 = zero1.multiply(zero2);
        assertTrue(result1.isZero());

        // Тест 2: 0*X^0 * 1*X^0 = 0*X^0
        TPoly one = new TPoly(1, 0);
        TPoly result2 = zero1.multiply(one);
        assertTrue(result2.isZero());

        // Тест 3: 1*X^0 * 1*X^0 = 1*X^0
        TPoly result3 = one.multiply(one);
        assertEquals(1, result3.getCoefficient(0));
        assertEquals(1, result3.getMemberCount());

        // Тест 4: 1*X^0 * 2*X^1 = 2*X^1
        TPoly twoX = new TPoly(2, 1);
        TPoly result4 = one.multiply(twoX);
        assertEquals(2, result4.getCoefficient(1));
        assertEquals(0, result4.getCoefficient(0));

        // Тест 5: 1*X^0 * (2*X^1 + 3*X^2) = 2*X^1 + 3*X^2
        TPoly poly5 = new TPoly(Arrays.asList(
                new TMember(2, 1),
                new TMember(3, 2)
        ));
        TPoly result5 = one.multiply(poly5);
        assertTrue(poly5.equals(result5));

        // Тест 6: (1*X^0 + 1*X^1) * (1*X^0 - 1*X^1) = 1*X^0 - 1*X^2
        TPoly poly6a = new TPoly(Arrays.asList(
                new TMember(1, 0),
                new TMember(1, 1)
        ));
        TPoly poly6b = new TPoly(Arrays.asList(
                new TMember(1, 0),
                new TMember(-1, 1)
        ));
        TPoly result6 = poly6a.multiply(poly6b);
        assertEquals(1, result6.getCoefficient(0));
        assertEquals(0, result6.getCoefficient(1));
        assertEquals(-1, result6.getCoefficient(2));
    }

    @Test
    void testNormalization() {
        // Полином с нулевыми членами и дубликатами
        List<TMember> members = Arrays.asList(
                new TMember(2, 3),
                new TMember(0, 5),
                new TMember(3, 2),
                new TMember(2, 3), // дубликат
                new TMember(0, 1)
        );
        TPoly p = new TPoly(members);

        // После нормализации должны остаться только 4x^3 и 3x^2
        assertEquals(2, p.getMemberCount());
        assertEquals(4, p.getCoefficient(3)); // 2 + 2 = 4
        assertEquals(3, p.getCoefficient(2));
    }
}
