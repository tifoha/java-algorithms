package net.tifoha.ch_01._05;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitalii Sereda
 */
public class WeightedFastUnion extends FastUnion {
    private final int[] depth;

    public WeightedFastUnion(int size) {
        super(size);
        depth = new int[size];
    }

    @Override
    public int union(int n, int m) {
        int nRoot = find(n);
        int mRoot = find(m);

        if (nRoot == mRoot) {
            return nRoot;
        } else if (depth[nRoot] == depth[mRoot]) {
            ids[mRoot] = nRoot;
            depth[nRoot]++;
        } else if (depth[nRoot] > depth[mRoot]) {
            ids[mRoot] = nRoot;
        } else {
            ids[nRoot] = mRoot;

        }
        count--;

        return nRoot;
    }

    public int[] getMaxSizes(int count) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            int n = i;
            int h = 0;
            while (n != ids[n]) {
                h++;
                n = ids[n];
            }
            int height = h;
            result.compute(i, (key, value) -> value == null || height > value ? height : value);
        }

        return result
                .values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(count)
                .mapToInt(i -> i)
                .toArray();
//        int[] tmp = Arrays.copyOf(depth, depth.length);
//        Arrays.sort(tmp);
//        int[] result = new int[count];
//        for (int i = 0, j = tmp.length - 1; i < count; i++, j--) {
//            result[i] = tmp[j];
//        }
//        return result;
    }
}
