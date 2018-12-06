package net.tifoha.ch_01._05;

/**
 * @author Vitalii Sereda
 */
public class WeightedFastUnionPathCompressing extends WeightedFastUnion {
    public WeightedFastUnionPathCompressing(int size) {
        super(size);
    }

    @Override
    public int find(int n) {
        int start = n;
        while (ids[n] != n) {
            n = ids[n];
        }

        while (ids[start] != n) {
            start = ids[start];
            ids[start] = n;
        }

        return n;
    }
}
