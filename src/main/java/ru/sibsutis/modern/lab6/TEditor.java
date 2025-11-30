package ru.sibsutis.modern.lab6;

public class TEditor {

    private String value;

    public static final String FRACTION = ",";
    public static final String SEP_PLUS = " +i ";
    public static final String SEP_MINUS = " -i ";
    public static final String ZERO = "0, +i 0,";

    public TEditor() {
        clear();
    }

    public String read() {
        return value;
    }

    public void write(String s) {
        if (s == null || s.isEmpty())
            throw new IllegalArgumentException("empty string");
        value = s;
    }

    // ---------------- ВСПОМОГАТЕЛЬНЫЕ ----------------
    public boolean isZero() {
        return value.equals(ZERO);
    }

    private boolean editingImag() {
        return value.contains("i ");
    }

    private boolean realHasFraction() {
        int idx = value.indexOf(" ");
        String real = value.substring(0, idx == -1 ? (value.length() - 1) : idx - 1); // последнюю запятую не берем
        return real.contains(FRACTION);
    }

    private boolean imagHasFraction() {
        int idx = value.indexOf("i ");
        String imag = value.substring(idx + 2, value.length() - 1); // последнюю запятую не берем
        return imag.contains(FRACTION);
    }

    public boolean hasPartsSeparator() {
        return value.contains(SEP_PLUS) || value.contains(SEP_MINUS);
    }

    public String addDigit(int d) {
        if (d < 0 || d > 9)
            throw new IllegalArgumentException("digit must be 0..9");

        value += d;
        return value;
    }

    public String addZero() {
        value += "0";
        return value;
    }

    public String toggleRealSign() {
        if (value.startsWith("-")) value = value.substring(1);
        else value = "-" + value;
        return value;
    }

    public String toggleImagSign() {
        if (value.contains(SEP_PLUS))
            value = value.replace(SEP_PLUS, SEP_MINUS);
        else if (value.contains(SEP_MINUS))
            value = value.replace(SEP_MINUS, SEP_PLUS);

        return value;
    }

    public String addFractionSeparator() {
        if (!editingImag() && !realHasFraction()) {
            value += FRACTION;
        } else if (editingImag() && !imagHasFraction()) {
            value += FRACTION;
        }
        return value;
    }

    public String addPartsSeparator() {
        if (!hasPartsSeparator()) {
            value += SEP_PLUS;
        }
        return value;
    }

    public String backspace() {
        if (!value.isEmpty()) {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    public String clear() {
        value = ZERO;
        return value;
    }

    public String edit(int cmd) {
        return switch (cmd) {
            case 1 -> toggleRealSign();
            case 2 -> toggleImagSign();
            case 3 -> addDigit(1);
            case 4 -> addZero();
            case 5 -> addFractionSeparator();
            case 6 -> addPartsSeparator();
            case 7 -> backspace();
            case 8 -> clear();
            default -> throw new IllegalArgumentException("unknown command " + cmd);
        };
    }
}
