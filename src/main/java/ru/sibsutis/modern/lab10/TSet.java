package ru.sibsutis.modern.lab10;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class TSet<T extends Comparable<T>> {

    private final TreeSet<T> data;

    public TSet() {
        data = new TreeSet<>();
    }

    public void clear() {
        data.clear();
    }

    public void add(T d) {
        data.add(d);
    }

    public void remove(T d) {
        data.remove(d);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean contains(T d) {
        return data.contains(d);
    }

    public TSet<T> union(TSet<T> q) {
        TSet<T> r = new TSet<>();
        r.data.addAll(this.data);
        r.data.addAll(q.data);
        return r;
    }

    public TSet<T> subtract(TSet<T> q) {
        TSet<T> r = new TSet<>();
        for (T item : this.data) {
            if (!q.contains(item)) {
                r.add(item);
            }
        }
        return r;
    }

    public TSet<T> intersect(TSet<T> q) {
        TSet<T> r = new TSet<>();
        for (T item : this.data) {
            if (q.contains(item)) {
                r.add(item);
            }
        }
        return r;
    }

    public int size() {
        return data.size();
    }

    public T get(int j) {
        if (j < 1 || j > data.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + j);
        }
        List<T> list = new ArrayList<>(data);
        return list.get(j - 1);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
