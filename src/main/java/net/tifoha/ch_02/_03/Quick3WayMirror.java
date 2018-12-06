package net.tifoha.ch_02._03;

import net.tifoha.ch_02.SortUtils.IntPair;
import net.tifoha.ch_02._01.InsertionArrayCopy;

import java.util.Arrays;

import static net.tifoha.ch_02.SortUtils.partition3mirror;

public class Quick3WayMirror {
    private static final int INSERTION_SORT_CUTOFF = 8;

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
        int n = hi - lo + 1;
        if (n < 2) {
            return;
        } else if (n <= INSERTION_SORT_CUTOFF) {
            // cutoff to insertion sort
            InsertionArrayCopy.sort(a, lo, hi);
            return;
        }

        IntPair margin = partition3mirror(a, lo, hi);
        sort(a, lo, margin.getFirst());
        sort(a, margin.getSecond(), hi);
    }

    public static void main(String[] args) {
//        String[] a = "QUICKSORTEXAMPLE".split("");
//        String[] a = "89451687431534613168413484534867984133146876".split("");
        String[] a = "89451687431534613168413484534867984133146899".split("");
        sort(a);
        System.out.println(Arrays.toString(a));
    }
//    Key ﻿7epRpzF37AbRR5Sg9Gvpt1dXmXKxCvZueypinGS251YUPFzJ2GC
//    Outputs ﻿2544545ae7d65e93cb2f06c7a0b6ce4063e74ce8d5aad03f46d2d28bd4183b4b 0
}
