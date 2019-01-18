package net.tifoha.ch_02._04;

import java.util.Comparator;

import static net.tifoha.ch_02.SortUtils.*;

/**
 * @author Vitalii Sereda
 */
public class Heap {
    public static <T extends Comparable<T>> void sort(T[] a) {
        Comparator<T> comparator = Comparator.naturalOrder();
        constructHeap(a, comparator);
        sortDown2(a, comparator);
    }

    private static <T extends Comparable<T>> void sortDown(T[] a, Comparator<T> comparator) {
        int n = a.length;
        while (n > 1) {
            exch(a, 0, --n);
            sink0(a, 1, n, comparator);
        }
    }

    private static <T extends Comparable<T>> void sortDown2(T[] a, Comparator<T> comparator) {
        int n = a.length;
        while (n > 1) {
            exch(a, 0, --n);
            int index = 0;
            while (index * 2 + 1 < n) {
                int left = index * 2 + 1;
                int right = left + 1;
                //check end of heap and choose biggest node
                if (right >= n || less(a[right], a[left], comparator)) {
                    exch(a, index, left);
                    index = left;
                } else {
                    exch(a, index, right);
                    index = right;
                }
            }
            swim0(a, index, comparator);
        }
    }

    private static <T extends Comparable<T>> void constructHeap(T[] a, Comparator<T> comparator) {
        int size = a.length;
        for (int i = size / 2; i >= 1; i--) {
            sink0(a, i, size, comparator);
        }
    }


    public static void main(String[] args) {
        //        String[] a = StdIn.readAllStrings();
//        String[] a = "SORTEXAMPLE".split("");
        String[] a = "923456783567894416131676174974654168746841349741531687464687097941316796413497".split("");
        Heap.sort(a);
        show(a);
    }
}
