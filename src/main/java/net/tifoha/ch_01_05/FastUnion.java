package net.tifoha.ch_01_05;

/**
 * @author Vitalii Sereda
 */
public class FastUnion extends FastSearch {
    public FastUnion(int size) {
        super(size);
    }

    @Override
    public int find(int n) {
        while (ids[n] != n) {
            n = ids[n];
        }

        return n;
    }

    @Override
    public int union(int n, int m) {
        int nRoot = find(n);
        int mRoot = find(m);

        ids[mRoot] = nRoot;
        count--;

        return nRoot;
    }
}
