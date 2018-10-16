package net.tifoha.ch_02;

import net.tifoha.utils.CyclicStopwatch;
import net.tifoha.utils.StdOut;
import net.tifoha.utils.StdRandom;

import java.time.Duration;
import java.util.function.Consumer;

public class SortUtils {
    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is a < b ?
    public static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    // exchange a[i] and a[j]
    public static void exch(Object[] a, int i, int j) {
        if (i == j) {
            return;
        }
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        if (isMerged(a, lo, mid, hi)) {
            return;
        }
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

//    /**
//     * Merge without creating new array
//     */
//    @SuppressWarnings("unchecked")
//    public static <T extends Comparable<T>> void merge(T[] a, int lo, int mid, int hi) {
////        if (isMerged(a, lo, mid, hi)) {
////            return;
////        }
//        int i = lo;
//        int j = mid < hi ? mid + 1 : hi;
//        T l = a[i];
//        T h = a[j];
//        for (int k = lo; k <= hi; k++) {
//            if (i > mid) {
//                a[k] = h;
//                j++;
//                if (j <= hi) {
//                    h = a[j];
//                }
//            } else if (j > hi) {
//                a[k] = l;
//                l = a[++i];
//            } else if (less(h, l)) {
//                a[k] = h;
//                j++;
//                if (j <= hi) {
//                    h = a[j];
//                }
//            } else {
//                a[k] = l;
//                l = a[++i];
//            }
//        }
//    }

    private static <T extends Comparable<T>> boolean isMerged(T[] a, int lo, int mid, int hi) {
        if (lo == hi) {
            return true;
        } else if (hi - lo == 1) {
            if (less(a[hi], a[lo])) {
                exch(a, hi, lo);
            }
            return true;
//        } else if (less(a[mid], a[mid + 1])) {
//            return true;
        }

        return false;
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    // print array to standard output
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static Duration timeRandomInput(Consumer<Double[]> consumer, int n, int trials) {
        double total = 0.0;
//        Double[][] a = new Double[10][n];
        // Perform one experiment (generate and sort an array).
        CyclicStopwatch stopwatch = new CyclicStopwatch();
        for (int t = 0; t < 10; t++) {
            Double[] a = new Double[n];
            for (int i = 0; i < n; i++) {
                a[i] = StdRandom.uniform(0.0, 1.0);
            }
            stopwatch.start();
            consumer.accept(a);
            stopwatch.stop();
        }
        return stopwatch.getDuration();
    }

    public static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo;
        int j = hi + 1;
        while (i < j) {
            while (i < hi) if (less(v, a[++i])) break;
            while (j > lo) if (less(a[--j], v)) break;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static <T extends Comparable<T>> IntPair partition3(T[] a, int lo, int hi) {
        Comparable v = a[lo];
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        while (i <= gt) {
            int cmp = compare(v, a[i]);
            if (cmp > 0) {
                exch(a, lt++, i++);
            } else if (cmp < 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        return pair(lt, gt + 1);
    }

    private static <T extends Comparable<T>> int compare(T a, T b) {
        return a.compareTo(b);
    }

    public static IntPair pair(int firs, int second) {
        return new IntPair(firs, second);
    }

    public static void main(String[] args) {
//        Integer[] a1 = {1, 2, 4, 6, 1, 2, 3, 5, 7, 8, 9};
//        Integer[] a1 = {1, 2, 4, 6, 1, 2, 3, 5};
//        Integer[] a2 = {1, 2, 3, 5, 7, 8, 9};
//        merge2(a1, 0, 3, a1.length - 1);
//        System.out.println(Arrays.toString(a1));
//        int n = 1000000;
        int trails = 100_000;
//        int trails = 100;
        for (int n = 2; n < 1_0_000_000; n *= 2) {
            int lo = 0;
            int mid = lo + n / 2;
            int hi = n - 1;

//            System.out.printf("%6d: merge->%s\tmerge2->%s%n",
//                    n,
//                    timeRandomInput(a -> merge(a, new Double[a.length], lo, mid, hi), n, trails),
//                    timeRandomInput(a -> merge(a, lo, mid, hi), n, trails));
        }
    }

    public static class IntPair {
        private int first;
        private int second;

        private IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }
}
