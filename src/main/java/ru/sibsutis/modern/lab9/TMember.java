package ru.sibsutis.modern.lab9;

import java.util.Objects;

public class TMember {
    private int coefficient;
    private int degree;

    public TMember() {
        this.coefficient = 0;
        this.degree = 0;
    }

    public TMember(int coefficient, int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    public TMember(TMember other) {
        this.coefficient = other.coefficient;
        this.degree = other.degree;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean equals(TMember other) {
        if (this == other) return true;
        if (other == null) return false;
        return this.coefficient == other.coefficient && this.degree == other.degree;
    }

    public TMember differentiate() {
        if (degree == 0) {
            return new TMember(0, 0); // Производная константы = 0
        }
        return new TMember(coefficient * degree, degree - 1);
    }

    public double evaluate(double x) {
        return coefficient * Math.pow(x, degree);
    }

    public String toString() {
        if (coefficient == 0) {
            return "0";
        }

        if (degree == 0) {
            return Integer.toString(coefficient);
        }

        if (degree == 1) {
            if (coefficient == 1) {
                return "x";
            } else if (coefficient == -1) {
                return "-x";
            } else {
                return coefficient + "x";
            }
        }

        if (coefficient == 1) {
            return "x^" + degree;
        } else if (coefficient == -1) {
            return "-x^" + degree;
        } else {
            return coefficient + "x^" + degree;
        }
    }

    public boolean isZero() {
        return coefficient == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TMember tMember = (TMember) obj;
        return coefficient == tMember.coefficient && degree == tMember.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficient, degree);
    }
}
