package net.tifoha.ch_01_05;

/**
 * @author Vitalii Sereda
 */
public class WeightedFastUnionPathCompressingRecursion extends WeightedFastUnion {
    public WeightedFastUnionPathCompressingRecursion(int size) {
        super(size);
    }

    @Override
    public int find(int n) {
        if (ids[n] != n) {
            ids[n] = find(ids[n]);
            return ids[n];
        }

        return n;
//        int start = n;
//        while (ids[n] != n) {
//            n = ids[n];
//        }
//
//        while (ids[start] != n) {
//            start = ids[start];
//            ids[start] = n;
//        }
//
//        return n;
    }
}
