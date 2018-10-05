package net.tifoha.ch_02._02;

/******************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                https://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Merge < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 ******************************************************************************/

import static net.tifoha.ch_02.SortUtils.*;

/**
 * The {@code Merge} class provides static methods for sorting an
 * array using mergesort.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/22mergesort">Section 2.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
//Эта сортировка хороша для сортировки связаных списков
public class MergeBottomUp {

    // This class should not be instantiated.
    private MergeBottomUp() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort2(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        int n = a.length;
        int end = n - 1;
        for (int i = 1; i < n; i <<= 1) {
            for (int j = 0; j < n / i; j += 2) {
                int lo = j * i;
                int mid = (j + 1) * i - 1;
                int hi = Math.min((j + 2) * i - 1, end);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len <<= 1) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }

    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
//        String[] a = "MERGE".split("");
        String[] a = "MERGESORTEXAMPLE".split("");
        MergeBottomUp.sort(a);
        show(a);
//        for (int i = 1; i <= 256; i<<=1) {
//            System.out.println(i);
//        }
    }
}
