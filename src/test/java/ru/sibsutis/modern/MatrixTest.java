package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void constructor_WithValidData() {
        int[][] input = {{1, 2}, {3, 4}};

        Matrix matrix = new Matrix(input);

        assertEquals(2, matrix.getRows());
        assertEquals(2, matrix.getCols());
        assertEquals(1, matrix.get(0, 0));
        assertEquals(4, matrix.get(1, 1));
    }

    @Test
    void constructor_InvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(null));
        int[][] emptyData = {};
        assertThrows(IllegalArgumentException.class, () -> new Matrix(emptyData));
    }

    @Test
    void constructor_WithEmptyFirstRow() {
        int[][] invalidData = {{}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix(invalidData));
    }

    @Test
    void constructor_WithDifferentRowLengths() {
        int[][] invalidData = {{1, 2}, {3}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix(invalidData));
    }

    @Test
    void constructor_WithValidDimensions() {
        Matrix matrix = new Matrix(3, 4);
        assertEquals(3, matrix.getRows());
        assertEquals(4, matrix.getCols());
        assertEquals(0, matrix.get(0, 0));
    }

    @Test
    void constructor_WithInvalidRows() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(-1, 5));
    }

    @Test
    void constructor_WithInvalidCols() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(5, 0));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(5, -1));
    }

    @Test
    void get_ValidIndexes() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}, {3, 4}});

        assertEquals(1, matrix.get(0, 0));
        assertEquals(4, matrix.get(1, 1));
    }

    @Test
    void get_RowInvalidIndexes() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}, {3, 4}});
        assertThrows(IllegalArgumentException.class, () -> matrix.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> matrix.get(2, 0));
    }


    @Test
    void get_ColInvalidIndexes() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}, {3, 4}});
        assertThrows(IllegalArgumentException.class, () -> matrix.get(0, -1));
        assertThrows(IllegalArgumentException.class, () -> matrix.get(0, 2));

    }

    @Test
    void add_ValidMatrices() {
        Matrix a = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new int[][]{{5, 6}, {7, 8}});

        Matrix result = a.add(b);
        Matrix expected = new Matrix(new int[][]{{6, 8}, {10, 12}});

        assertEquals(expected, result);
    }

    @Test
    void add_DifferentDimensions() {
        Matrix a = new Matrix(new int[][]{{1, 2}});
        Matrix b = new Matrix(new int[][]{{1, 2, 3}});
        Matrix c = new Matrix(new int[][]{{1, 2}, {3, 4}});

        assertThrows(IllegalArgumentException.class, () -> a.add(b));
        assertThrows(IllegalArgumentException.class, () -> a.add(c));
    }

    @Test
    void subtract_ValidMatrices() {
        Matrix a = new Matrix(new int[][]{{5, 6}, {7, 8}});
        Matrix b = new Matrix(new int[][]{{1, 2}, {3, 4}});

        Matrix result = a.subtract(b);
        Matrix expected = new Matrix(new int[][]{{4, 4}, {4, 4}});

        assertEquals(expected, result);
    }

    @Test
    void subtract_DifferentDimensions() {
        Matrix a = new Matrix(new int[][]{{1, 2}});
        Matrix b = new Matrix(new int[][]{{1, 2, 3}});
        Matrix c = new Matrix(new int[][]{{1, 2}, {3, 4}});

        assertThrows(IllegalArgumentException.class, () -> a.subtract(b));
        assertThrows(IllegalArgumentException.class, () -> a.subtract(c));
    }

    @Test
    void multiply_ValidMatrices() {
        Matrix a = new Matrix(new int[][]{{1, 2}});
        Matrix b = new Matrix(new int[][]{{5, 6}, {7, 8}});

        Matrix result = a.multiply(b);
        Matrix expected = new Matrix(new int[][]{{19, 22}});

        assertEquals(expected, result);
    }

    @Test
    void multiply_IncompatibleDimensions() {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}});
        Matrix b = new Matrix(new int[][]{{1, 2}});

        assertThrows(IllegalArgumentException.class, () -> a.multiply(b));
    }

    @Test
    void equals_SameObject() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}});
        assertTrue(matrix.equals(matrix));
    }

    @Test
    void equals_NullObject() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}});
        assertFalse(matrix.equals(null));
    }

    @Test
    void equals_DifferentClass() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}});
        assertFalse(matrix.equals("not a matrix"));
    }

    @Test
    void equals_DifferentDimensions() {
        Matrix a = new Matrix(new int[][]{{1, 2}});
        Matrix b = new Matrix(new int[][]{{1, 2, 3}});
        assertFalse(a.equals(b));
    }

    @Test
    void equals_DifferentData_ShouldReturnFalse() {
        Matrix a = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new int[][]{{1, 2}, {3, 5}});
        assertFalse(a.equals(b));
    }

    @Test
    void equals_EqualMatrices() {
        Matrix a = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new int[][]{{1, 2}, {3, 4}});
        assertTrue(a.equals(b));
    }

    @Test
    void transpose_SquareMatrix() {
        Matrix original = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix transposed = original.transpose();
        Matrix expected = new Matrix(new int[][]{{1, 3}, {2, 4}});

        assertEquals(expected, transposed);
    }

    @Test
    void transpose_RectangularMatrix() {
        Matrix original = new Matrix(new int[][]{{1, 2, 3}});
        Matrix transposed = original.transpose();
        Matrix expected = new Matrix(new int[][]{{1}, {2}, {3}});

        assertEquals(expected, transposed);
        assertEquals(1, original.getRows());
        assertEquals(1, transposed.getCols());
    }

    @Test
    void min_ValidMatrix() {
        Matrix matrix = new Matrix(new int[][]{{5, 2}, {8, 1}});
        assertEquals(1, matrix.min());
    }

    @Test
    void toString_SingleElement() {
        Matrix matrix = new Matrix(new int[][]{{5}});
        String result = matrix.toString();

        assertEquals("{{5}}", result);
    }

    @Test
    void toString_NotASingle() {
        Matrix matrix = new Matrix(new int[][]{{1, 2}, {3, 4}});
        String result = matrix.toString();

        assertEquals("{{1,2},{3,4}}", result);
    }
}