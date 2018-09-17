package net.tifoha.ch_01_05;

import java.util.stream.IntStream;

/**
 * @author Vitalii Sereda
 */
public abstract class UnionFind {
    protected final int[] ids;
    protected int count;

    public UnionFind(int size) {
        ids = IntStream
                .range(0, size)
                .toArray();
        count = size;
    }

    public int count() {
        return count;
    }

    public abstract int find(int n);

    public boolean connected(int n, int m) {
        return find(n) == find(m);
    }

    public abstract int union(int n, int m);

    public int size() {
        return ids.length;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                count +
                ":" +
                ids.length +
                '}';
    }
}
