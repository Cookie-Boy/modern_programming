package ru.sibsutis.modern;

public class TFrac implements TNumber<TFrac>, Comparable<TFrac> {
    private final int num;
    private final int den;

    public TFrac(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
        this.num = num;
        this.den = den;
    }

    public TFrac() {
        this(0, 1);
    }

    @Override
    public TFrac add(TFrac o) {
        return new TFrac(
                num * o.den + o.num * den,
                den * o.den
        ).normalize();
    }

    @Override
    public TFrac sub(TFrac o) {
        return new TFrac(
                num * o.den - o.num * den,
                den * o.den
        ).normalize();
    }

    @Override
    public TFrac mul(TFrac o) {
        return new TFrac(num * o.num, den * o.den).normalize();
    }

    @Override
    public TFrac div(TFrac o) {
        if (o.num == 0) throw new ArithmeticException("Division by zero");
        return new TFrac(num * o.den, den * o.num).normalize();
    }

    @Override
    public TFrac rev() {
        if (num == 0) throw new ArithmeticException("Cannot invert zero");
        return new TFrac(den, num);
    }

    @Override
    public TFrac sqr() {
        return new TFrac(num * num, den * den);
    }

    @Override
    public TFrac copy() {
        return new TFrac(num, den);
    }

    @Override
    public TFrac defaultValue() {
        return new TFrac(0, 1);
    }

    private TFrac normalize() {
        int g = gcd(Math.abs(num), Math.abs(den));
        return new TFrac(num / g, den / g);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TFrac o)) return false;
        return num == o.num && den == o.den;
    }

    @Override
    public int compareTo(TFrac o) {
        return Integer.compare(this.num * o.den, o.num * this.den);
    }

}

