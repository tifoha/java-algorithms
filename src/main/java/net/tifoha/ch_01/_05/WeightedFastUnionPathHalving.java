package net.tifoha.ch_01._05;

/**
 * @author Vitalii Sereda
 */
public class WeightedFastUnionPathHalving extends WeightedFastUnion {
    public WeightedFastUnionPathHalving(int size) {
        super(size);
    }

    @Override
    public int find(int n) {
        while (ids[n] != n) {
            ids[n] = ids[ids[n]];
            n = ids[n];
        }

        return n;
    }
}
