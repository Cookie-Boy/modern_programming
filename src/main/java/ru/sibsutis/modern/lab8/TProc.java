package ru.sibsutis.modern.lab8;

import ru.sibsutis.modern.TNumber;

public class TProc<T extends TNumber<T>> {

    public enum TOprtn { None, Add, Sub, Mul, Dvd }
    public enum TFunc   { Rev, Sqr }

    private T Lop_Res;
    private T Rop;
    private TOprtn Operation;

    public TProc(T prototype) {
        this.Lop_Res = prototype.defaultValue();
        this.Rop = prototype.defaultValue();
        this.Operation = TOprtn.None;
    }

    // Сброс процессора
    public void reSet() {
        Lop_Res = Lop_Res.defaultValue();
        Rop = Rop.defaultValue();
        Operation = TOprtn.None;
    }

    public void oprtnClear() {
        Operation = TOprtn.None;
    }

    public void lopResSet(T operand) {
        Lop_Res = operand.copy();
    }

    public T lopResRead() {
        return Lop_Res.copy();
    }

    public void ropSet(T operand) {
        Rop = operand.copy();
    }

    public T ropRead() {
        return Rop.copy();
    }

    public void oprtnSet(TOprtn op) {
        this.Operation = op;
    }

    public TOprtn oprtnRead() {
        return Operation;
    }

    public void oprtnRun() {
        if (Operation == TOprtn.None) return;

        switch (Operation) {
            case Add -> Lop_Res = Lop_Res.add(Rop);
            case Sub -> Lop_Res = Lop_Res.sub(Rop);
            case Mul -> Lop_Res = Lop_Res.mul(Rop);
            case Dvd -> Lop_Res = Lop_Res.div(Rop);
        }
    }

    public void funcRun(TFunc f) {
        switch (f) {
            case Rev -> Rop = Rop.rev();
            case Sqr -> Rop = Rop.sqr();
        }
    }
}
