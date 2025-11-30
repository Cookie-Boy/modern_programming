package ru.sibsutis.modern;

import org.junit.jupiter.api.Test;
import ru.sibsutis.modern.lab8.TProc;

import static org.junit.jupiter.api.Assertions.*;

public class TProcTest {

    @Test
    public void testConstructor() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        assertEquals("0/1", p.lopResRead().toString());
        assertEquals("0/1", p.ropRead().toString());
        assertEquals(TProc.TOprtn.None, p.oprtnRead());
    }

    @Test
    public void testReset() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        p.lopResSet(new TFrac(5,1));
        p.ropSet(new TFrac(7,1));
        p.oprtnSet(TProc.TOprtn.Add);

        p.reSet();
        assertEquals("0/1", p.lopResRead().toString());
        assertEquals("0/1", p.ropRead().toString());
        assertEquals(TProc.TOprtn.None, p.oprtnRead());
    }

    @Test
    public void testOperatorClear() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        p.oprtnSet(TProc.TOprtn.Mul);
        p.oprtnClear();
        assertEquals(TProc.TOprtn.None, p.oprtnRead());
    }

    @Test
    public void testOperationAdd() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        p.lopResSet(new TFrac(2,1));
        p.ropSet(new TFrac(3,1));
        p.oprtnSet(TProc.TOprtn.Add);
        p.oprtnRun();
        assertEquals("5/1", p.lopResRead().toString());
    }

    @Test
    public void testOperationMul() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        p.lopResSet(new TFrac(2,1));
        p.ropSet(new TFrac(4,1));
        p.oprtnSet(TProc.TOprtn.Mul);
        p.oprtnRun();
        assertEquals("8/1", p.lopResRead().toString());
    }

    @Test
    public void testFunctionSqr() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        p.ropSet(new TFrac(4,1));
        p.funcRun(TProc.TFunc.Sqr);
        assertEquals("16/1", p.ropRead().toString());
    }

    @Test
    public void testFunctionRev() {
        TProc<TFrac> p = new TProc<>(new TFrac());
        p.ropSet(new TFrac(2,3));
        p.funcRun(TProc.TFunc.Rev);
        assertEquals("3/2", p.ropRead().toString());
    }
}
