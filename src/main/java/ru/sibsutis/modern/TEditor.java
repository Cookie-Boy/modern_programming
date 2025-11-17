package ru.sibsutis.modern;

public class TEditor {

    public static final String DECIMAL_SEPARATOR = ".";
    public static final String IMAGINARY_SEPARATOR = ", i* ";
    public static final String ZERO_STRING = "0, i* 0,";

    private String numberString;
    private boolean editingImag; // false = редактируем действительную часть, true = мнимую

    public TEditor() {
        clear();
    }

    public boolean isZero() {
        return numberString.equals(ZERO_STRING);
    }

    private String[] splitParts() {
        if (!numberString.contains(IMAGINARY_SEPARATOR)) {
            return new String[]{numberString, ""};
        }

        String[] parts = numberString.split(IMAGINARY_SEPARATOR, 2);
        String real = parts[0];
        String imag = parts[1];

        if (imag.endsWith(",")) imag = imag.substring(0, imag.length() - 1);
        return new String[]{real, imag};
    }

    private void updateParts(String real, String imag) {
        if (imag.isEmpty()) {
            numberString = real;
        } else {
            numberString = real + IMAGINARY_SEPARATOR + imag + ",";
        }
    }

    private boolean realHasSeparator() {
        String real = splitParts()[0];
        return real.contains(DECIMAL_SEPARATOR);
    }

    private boolean imagHasSeparator() {
        String imag = splitParts()[1];
        return imag.contains(DECIMAL_SEPARATOR);
    }

    public String addSign() {
        String[] p = splitParts();
        String real = p[0];
        String imag = p[1];

        if (!editingImag) {
            if (real.startsWith("-")) real = real.substring(1);
            else real = "-" + real;
        } else {
            if (imag.isEmpty()) imag = "0"; // гарантия корректной части
            if (imag.startsWith("-")) imag = imag.substring(1);
            else imag = "-" + imag;
        }

        updateParts(real, imag);
        return numberString;
    }

    public String addImaginarySeparator() {
        if (numberString.contains(IMAGINARY_SEPARATOR)) return numberString;

        String real = numberString;
        String imag = "0";
        updateParts(real, imag);
        editingImag = true;

        return numberString;
    }

    public String addDot() {
        String[] p = splitParts();
        String real = p[0];
        String imag = p[1];

        if (!editingImag) {
            if (!real.contains(DECIMAL_SEPARATOR)) {
                real += DECIMAL_SEPARATOR;
            }
        } else {
            if (imag.isEmpty()) imag = "0";
            if (!imag.contains(DECIMAL_SEPARATOR)) {
                imag += DECIMAL_SEPARATOR;
            }
        }

        updateParts(real, imag);
        return numberString;
    }

    public String addDigit(int digit) {
        if (digit < 0 || digit > 9)
            throw new IllegalArgumentException("Digit must be 0..9");

        String d = String.valueOf(digit);
        String[] p = splitParts();
        String real = p[0];
        String imag = p[1];

        if (!editingImag) {
            if (real.equals("0")) real = d;
            else if (real.equals("-0")) real = "-" + d;
            else real += d;
        } else {
            if (imag.isEmpty() || imag.equals("0")) imag = d;
            else if (imag.equals("-0")) imag = "-" + d;
            else imag += d;
        }

        updateParts(real, imag);
        return numberString;
    }

    public String addZero() {
        return addDigit(0);
    }

    public String backspace() {
        if (isZero()) return numberString;

        String[] p = splitParts();
        String real = p[0];
        String imag = p[1];

        if (!editingImag) {
            real = removeLastChar(real);

            if (real.isEmpty() || real.equals("-")) {
                clear();
                return numberString;
            }

            updateParts(real, imag);
            return numberString;

        } else {
            if (imag.isEmpty()) {
                editingImag = false;
                numberString = real;
                return numberString;
            }

            imag = removeLastChar(imag);

            if (imag.isEmpty() || imag.equals("-")) {
                editingImag = false;
                numberString = real;
                return numberString;
            }

            updateParts(real, imag);
            return numberString;
        }
    }

    private String removeLastChar(String s) {
        if (s.length() <= 1) return "";
        return s.substring(0, s.length() - 1);
    }

    public String clear() {
        numberString = ZERO_STRING;
        editingImag = false;
        return numberString;
    }

    public String getString() {
        return numberString;
    }

    public void setString(String value) {
        this.numberString = value;
        editingImag = value.contains(IMAGINARY_SEPARATOR);
    }

    public String edit(int cmd) {
        switch (cmd) {
            case 0:  return clear();
            case 1:  return addSign();
            case 2:  return backspace();
            case 3:  return addImaginarySeparator();
            case 14: return addDot();
            case 13: return addZero();
        }
        if (cmd >= 4 && cmd <= 12) return addDigit(cmd - 3);
        throw new IllegalArgumentException("Invalid command");
    }
}
