package ru.sibsutis.modern;

public class Matrix {
    private final int[][] data;
    private final int rows;
    private final int cols;

    public Matrix(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Матрица не может быть пустой");
        }

        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (data[i].length != cols) {
                throw new IllegalArgumentException("Все строки матрицы должны иметь одинаковую длину");
            }
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    public Matrix(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("недопустимое значение строки = " + rows);
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("недопустимое значение столбца = " + cols);
        }

        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int get(int i, int j) {
        if (i < 0 || i >= rows) {
            throw new IllegalArgumentException("неверное значение i = " + i);
        }
        if (j < 0 || j >= cols) {
            throw new IllegalArgumentException("неверное значение j = " + j);
        }
        return data[i][j];
    }

    public Matrix set(int i, int j, int value) {
        if (i < 0 || i >= rows) {
            throw new IllegalArgumentException("неверное значение i = " + i);
        }
        if (j < 0 || j >= cols) {
            throw new IllegalArgumentException("неверное значение j = " + j);
        }

        int[][] newData = new int[rows][cols];
        for (int x = 0; x < rows; x++) {
            System.arraycopy(data[x], 0, newData[x], 0, cols);
        }
        newData[i][j] = value;

        return new Matrix(newData);
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать для сложения");
        }

        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix subtract(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать для вычитания");
        }

        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Матрицы не согласованы для умножения");
        }

        int[][] result = new int[this.rows][other.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    result[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Matrix other = (Matrix) obj;
        if (this.rows != other.rows || this.cols != other.cols) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.data[i][j] != other.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = rows;
        result = 31 * result + cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result = 31 * result + data[i][j];
            }
        }
        return result;
    }

    public Matrix transpose() {
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = data[i][j];
            }
        }
        return new Matrix(result);
    }

    public int min() {
        if (rows == 0 || cols == 0) {
            throw new IllegalStateException("Матрица пуста");
        }

        int min = data[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] < min) {
                    min = data[i][j];
                }
            }
        }
        return min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < rows; i++) {
            sb.append("{");
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]);
                if (j < cols - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
            if (i < rows - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void show() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("\t" + data[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}