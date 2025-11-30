package ru.sibsutis.modern.lab4;

public class TPNumber {
    private final double num;
    private final int base;
    private final int c;

    public TPNumber(double a, int b, int c) {
        if(b < 2 || b > 16)
            throw new NumberFormatException();

        if(c < 0)
            throw new NumberFormatException();

        this.num = parseNum(a, b, c);
        this.base = b;
        this.c = c;
    }

    public TPNumber(String a, String b, String c) {
        int base = Integer.parseInt(b);
        int acc = Integer.parseInt(c);
        if(base < 2 || base > 16)
            throw new NumberFormatException();

        if(acc < 0)
            throw new NumberFormatException();

        this.base = base;
        this.c = acc;
        this.num = parseNum(a, base, acc);
    }

    private double parseNum(double a, int b, int c) {
        return parseNum(String.valueOf(a), b, c);
    }
    private double parseNum(String a, int b, int c) {
        a = a.replace(',', '.');
        String[] parts = a.split("\\.");
        if(parts.length > 2)
            throw new NumberFormatException();
        double result = Integer.parseInt(parts[0], b);
        double num;
        if(parts.length == 2) {
            for(int i = 0; i < c && i < parts[1].length(); i++) {
                if(parts[1].charAt(i) < '9')
                    num = parts[1].charAt(i) - '0';
                else
                    num = parts[1].charAt(i) - 'A' + 10;
                if(num >= b)
                    throw new NumberFormatException();
                result += num * Math.pow(1.0 / b, i + 1);
            }
        }
        double scale = Math.pow(10, c);
        return Math.round(result * scale) / scale;
    }

    public double getNum() {
        return num;
    }

    public int getBase() {
        return base;
    }

    public int getC() {
        return c;
    }

    public TPNumber copy() {
        return new TPNumber(convertToBase(this.num, this.base, this.c), String.valueOf(this.base), String.valueOf(this.c));
    }

    public TPNumber add(TPNumber d) {
        if(base != d.base || c != d.c)
            throw new NumberFormatException();
        double result = this.num + d.getNum();
        return new TPNumber(result, this.base, this.c);
    }

    public TPNumber multiply(TPNumber d) {
        if(base != d.base || c != d.c)
            throw new NumberFormatException();
        double result = this.num * d.getNum();
        return new TPNumber(result, this.base, this.c);
    }

    public TPNumber subtract(TPNumber d) {
        if(base != d.base || c != d.c)
            throw new NumberFormatException();
        double result = this.num - d.getNum();
        return new TPNumber(result, this.base, this.c);
    }

    public TPNumber divide(TPNumber d) {
        if(base != d.base || c != d.c)
            throw new NumberFormatException();
        if (d.getNum() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double result = this.num / d.getNum();
        return new TPNumber(result, this.base, this.c);
    }

    public TPNumber inverse() {
        if (this.num == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new TPNumber(1, this.base, this.c).divide(this);
    }

    public TPNumber square() {
        double result = this.num * this.num;
        return new TPNumber(result, this.base, this.c);
    }

    public String getNumberString() {
        return convertToBase(this.num, this.base, this.c);
    }

    private String convertToBase(double number, int base, int precision) {
        int integerPart = (int) number;
        double fractionalPart = Math.abs(number - integerPart);

        String integerStr = Integer.toString(integerPart, base).toUpperCase();

        StringBuilder fractionalStr = new StringBuilder();
        double tempFractional = fractionalPart;

        for (int i = 0; i < precision; i++) {
            tempFractional *= base;
            int digit = (int) tempFractional;
            if (digit < 10) {
                fractionalStr.append(digit);
            } else {
                fractionalStr.append((char) ('A' + digit - 10));
            }
            tempFractional -= digit;
        }

        if (fractionalStr.length() > 0) {
            return integerStr + "." + fractionalStr;
        } else {
            return integerStr;
        }
    }
}
