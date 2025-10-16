package ru.sibsutis.modern;

public class Lab2 implements Lab {

    public static double max(double a, double b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static double sumElements(double[][] A) {
        if (A == null || A.length == 0) {
            return 0.0;
        }

        int maxColIndex = A[0].length - 1;
        double sum = 0.0;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (i + j == maxColIndex) {
                    sum += A[i][j];
                }
            }
        }

        return sum;
    }

    public static double minAboveSecondaryDiagonal(double[][] A) {
        if (A == null || A.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        int n = A.length;
        double min = Double.MAX_VALUE;
        boolean found = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (A[i][j] < min) {
                    min = A[i][j];
                    found = true;
                }
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Нет элементов на или выше побочной диагонали");
        }

        return min;
    }

    @Override
    public void execute() {
        System.out.println("Hello!");
    }
}