package ru.sibsutis.modern;

public interface TNumber<T> {
    T add(T other);
    T sub(T other);
    T mul(T other);
    T div(T other);

    T rev();  // 1/x
    T sqr();  // x^2

    T copy();
    T defaultValue();
}