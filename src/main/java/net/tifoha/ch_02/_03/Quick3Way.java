package net.tifoha.ch_02._03;

import net.tifoha.ch_02.SortUtils;

import java.util.Arrays;

public class Quick3Way {
    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        SortUtils.IntPair margin = SortUtils.partition3(a, lo, hi);
        sort(a, lo, margin.getFirst());
        sort(a, margin.getSecond(), hi);
    }

    public static void main(String[] args) {
//        String[] a = "QUICKSORTEXAMPLE".split("");
        String[] a = "89451687431534613168413484534867984133146876".split("");
        sort(a);
        System.out.println(Arrays.toString(a));
    }
//    Key ﻿7epRpzF37AbRR5Sg9Gvpt1dXmXKxCvZueypinGS251YUPFzJ2GC
//    Outputs ﻿2544545ae7d65e93cb2f06c7a0b6ce4063e74ce8d5aad03f46d2d28bd4183b4b 0
}
