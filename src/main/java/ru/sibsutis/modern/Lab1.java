package ru.sibsutis.modern;

// Вариант 1
public class Lab1 implements Lab {
    public static double oddMultiply(double[] array) {
        if (array == null || array.length < 2)
            return 0;

        double product = 1;
        for (int i = 1; i < array.length; i += 2) {
            product *= array[i];
        }
        return product;
    }

    public static void cyclicShiftRight(double[] array, int shift) {
        if (array == null || array.length == 0)
            return;

        if (shift < 0)
            shift = array.length - ((-shift) % array.length);
        else
            shift = shift % array.length;

        if (shift == 0) return;

        double[] temp = new double[shift];
        System.arraycopy(array, array.length - shift, temp, 0, shift);
        System.arraycopy(array, 0, array, shift, array.length - shift);
        System.arraycopy(temp, 0, array, 0, shift);
    }

    public static double convertFractionFromBase(int b, String s) {
        if (b < 2 || b > 36)
            throw new IllegalArgumentException("Основание системы счисления должно быть от 2 до 36");

        if (s == null || s.isEmpty())
            return 0;

        double result = 0;
        double basePower = 1.0 / b;

        for (char c : s.toCharArray()) {
            int digit = charToDigit(c);
            if (digit >= b)
                throw new IllegalArgumentException("Символ '" + c + "' недопустим в системе счисления с основанием " + b);

            result += digit * basePower;
            basePower /= b;
        }

        return result;
    }

    private static int charToDigit(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'A' && c <= 'Z')
            return c - 'A' + 10;
        if (c >= 'a' && c <= 'z')
            return c - 'a' + 10;

        throw new IllegalArgumentException("Недопустимый символ: " + c);
    }

    @Override
    public void execute() {
        double[] array1 = {1.0, 2.0, 3.0, 4.0, 5.0};
        System.out.println("Исходный массив: " + java.util.Arrays.toString(array1));
        double product = oddMultiply(array1);
        System.out.println("Произведение элементов с нечетными индексами: " + product);

        double[] array2 = {1.0, 2.0, 3.0, 4.0, 5.0};
        System.out.println("\nМассив до сдвига: " + java.util.Arrays.toString(array2));
        cyclicShiftRight(array2, 2);
        System.out.println("Массив после сдвига на 2 вправо: " + java.util.Arrays.toString(array2));

        String binaryFraction = "101";
        double decimalValue = convertFractionFromBase(2, binaryFraction);
        System.out.println("\nДвоичная дробь '0." + binaryFraction + "' в десятичной системе: " + decimalValue);

        String hexFraction = "A5";
        double decimalValueHex = convertFractionFromBase(16, hexFraction);
        System.out.println("Шестнадцатеричная дробь '0." + hexFraction + "' в десятичной системе: " + decimalValueHex);
    }
}

