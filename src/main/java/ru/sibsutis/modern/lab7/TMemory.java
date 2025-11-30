package ru.sibsutis.modern.lab7;

import ru.sibsutis.modern.TNumber;

public class TMemory<T extends TNumber<T>> {

    public enum MemState { _On, _Off }

    private T FNumber;
    private MemState FState;

    public TMemory(T prototype) {
        this.FNumber = prototype.defaultValue();
        this.FState = MemState._Off;
    }

    public void store(T e) {
        this.FNumber = e.copy();
        this.FState = MemState._On;
    }

    public T load() {
        this.FState = MemState._On;
        return FNumber.copy();
    }

    public void add(T e) {
        this.FNumber = this.FNumber.add(e).copy();
        this.FState = MemState._On;
    }

    public void clear() {
        this.FNumber = FNumber.defaultValue();
        this.FState = MemState._Off;
    }

    public String readState() {
        return FState.toString();
    }

    public T readNumber() {
        return FNumber.copy();
    }
}
