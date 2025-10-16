package ru.sibsutis.modern;

public class Lab3 implements Lab {
    static Integer radixCreator(Integer a) {
        if (a == 0) {
            return 0;
        }

        int temp = Math.abs(a);
        int digitCount = 0;
        while (temp > 0) {
            digitCount++;
            temp /= 10;
        }

        temp = Math.abs(a);
        StringBuilder digits = new StringBuilder();

        for (int position = 1; position <= digitCount; position++) {
            int power = digitCount - position;
            int digit = temp / (int)Math.pow(10, power) % 10;

            if (position % 2 != 0) {
                digits.append(digit);
            }
        }

        String resultStr = digits.toString();
        return resultStr.isEmpty() ? 0 : Integer.parseInt(resultStr);
    }

    public static int cyclicShiftRight(int n, int positions) {
        if (n == 0) {
            return 0;
        }

        int temp = Math.abs(n);
        int digitCount = 0;
        while (temp > 0) {
            digitCount++;
            temp /= 10;
        }

        positions = positions % digitCount;
        if (positions == 0) {
            return Math.abs(n);
        }


        int divisor = (int) Math.pow(10, positions);
        int rightPart = Math.abs(n) % divisor;
        int leftPart = Math.abs(n) / divisor;


        int result = rightPart * (int) Math.pow(10, digitCount - positions) + leftPart;

        return result;
    }


    public static int sumEvenElements(int[][] A) {
        if (A == null) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < A.length - i; i++) {
            if (A[i] != null) {
                for (int j = 0; j < A[i].length - i; j++) {
                    if (A[i][j] % 2 == 0) {
                        sum += A[i][j];
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public void execute() {
        System.out.println(radixCreator(654321));
        System.out.println("cyclicShiftRight(123456, 2) = " + cyclicShiftRight(123456, 2)); // 561234
        System.out.println("cyclicShiftRight(123, 1) = " + cyclicShiftRight(123, 1)); // 312

        // Тест четвертой функции
        int[][] testArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("sumEvenElements(testArray) = " + sumEvenElements(testArray)); // 2+4+6+8 = 20
    }
}
