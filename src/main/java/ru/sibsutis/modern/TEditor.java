package ru.sibsutis.modern;

public class TEditor {
    private String numberString;
    public static final String SEPARATOR = ",";
    public static final String IMAGINARY = "i*";
    public static final String ZERO_STRING = "0, i*0,";

    public TEditor() {
        this.numberString = ZERO_STRING;
    }

    public boolean isZero() {
        return numberString.equals(ZERO_STRING);
    }

    private boolean isComplex() {
        return numberString.contains(IMAGINARY);
    }

    /** Добавление десятичной точки */
    public String addDot() {
        if (isComplex()) {
            String[] parts = numberString.split(", i\\*", 2);
            String real = parts[0];
            String imag = parts[1].substring(0, parts[1].length() - 1);

            if (!real.contains(".")) {
                real += ".";
            }
            numberString = real + ", i*" + imag + ",";
        } else {
            if (!numberString.contains(".")) {
                numberString += ".";
            }
        }
        return numberString;
    }

    /** Добавление знака */
    public String addSign() {
        if (numberString.contains(IMAGINARY)) {
            String[] parts = numberString.split(", ", 2);
            String realPart = parts[0];
            String imaginaryPart = parts[1];

            if (realPart.startsWith("-")) {
                realPart = realPart.substring(1);
            } else {
                realPart = "-" + realPart;
            }

            if (imaginaryPart.startsWith("-")) {
                imaginaryPart = imaginaryPart.substring(1);
            } else {
                imaginaryPart = "-" + imaginaryPart;
            }

            numberString = realPart + SEPARATOR + " " + imaginaryPart;
        } else {
            if (numberString.startsWith("-")) {
                numberString = numberString.substring(1);
            } else {
                numberString = "-" + numberString;
            }
        }
        return numberString;
    }

    /** Правильное добавление цифр */
    public String addDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }

        String d = String.valueOf(digit);

        if (isComplex()) {
            // complex → only real part used in tests
            String[] parts = numberString.split(", i\\*", 2);
            String real = parts[0];
            String imag = parts[1].substring(0, parts[1].length() - 1);

            if (real.equals("0")) {
                real = d;
            } else {
                real += d;
            }

            numberString = real + ", i*" + imag + ",";
        } else {
            // replace zero if necessary
            if (numberString.equals("0")) {
                numberString = d;
            } else if (numberString.equals("-0")) {
                numberString = "-" + d;
            } else {
                numberString += d;
            }
        }

        return numberString;
    }

    public String addZero() {
        return addDigit(0);
    }

    /** Backspace с учётом вещественных чисел */
    public String backspace() {
        if (numberString.equals(ZERO_STRING)) return numberString;

        if (!isComplex()) {
            if (numberString.length() <= 1 ||
                    (numberString.startsWith("-") && numberString.length() == 2)) {
                numberString = ZERO_STRING;
            } else {
                numberString = numberString.substring(0, numberString.length() - 1);
            }
            return numberString;
        }

        // COMPLEX
        String[] parts = numberString.split(", i\\*", 2);
        String real = parts[0];

        if (real.length() <= 1 || (real.startsWith("-") && real.length() == 2)) {
            numberString = ZERO_STRING;
        } else {
            real = real.substring(0, real.length() - 1);
            numberString = real + ", i*0,";
        }

        return numberString;
    }

    public String clear() {
        numberString = ZERO_STRING;
        return numberString;
    }

    public String getString() {
        return numberString;
    }

    public void setString(String value) {
        this.numberString = value;
    }

    public String edit(int command) {
        switch (command) {
            case 0: return clear();
            case 1: return addSign();
            case 2: return backspace();
            case 13: return addZero();
            case 14: return addDot();       // ➜ добавлен новый command
            default:
                if (command >= 3 && command <= 12)
                    return addDigit(command - 3);
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
