package net.tifoha.ch_01_05;

/**
 * @author Vitalii Sereda
 */
public class FastSearch extends UnionFind {

    public FastSearch(int size) {
        super(size);
    }

    @Override
    public int find(int n) {
        return ids[n];
    }

    @Override
    public int union(int n, int m) {
        if (connected(n, m)) {
            return find(n);
        }

        int nSet = find(n);
        int mSet = find(m);

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == mSet) {
                ids[i] = nSet;
            }
        }

        count--;
        return nSet;
    }

}
