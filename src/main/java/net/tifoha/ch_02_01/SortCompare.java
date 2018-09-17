package net.tifoha.ch_02_01;

/**
 * @author Vitalii Sereda
 */
/******************************************************************************
 *  Compilation:  javac SortCompare.java
 *  Execution:    java SortCompare alg1 alg2 n trials
 *  Dependencies: StdOut.java Stopwatch.java
 *
 *  Sort n random real numbers, trials times using the two
 *  algorithms specified on the command line.
 *
 *  % java SortCompare Insertion Selection 1000 100
 *  For 1000 random Doubles
 *    Insertion is 1.7 times faster than Selection
 *
 *  Note: this program is designed to compare two sorting algorithms with
 *  roughly the same order of growth, e,g., insertion sort vs. selection
 *  sort or mergesort vs. quicksort. Otherwise, various system effects
 *  (such as just-in-time compiliation) may have a significant effect.
 *  One alternative is to execute with "java -Xint", which forces the JVM
 *  to use interpreted execution mode only.
 *
 ******************************************************************************/

import net.tifoha.utils.StdOut;
import net.tifoha.utils.StdRandom;
import net.tifoha.utils.Stopwatch;

import java.util.Arrays;
import java.util.stream.Stream;

public class SortCompare {

    public static <T extends Comparable> double time(String alg, T[] a) {
        Stopwatch sw = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
//        else if (alg.equals("InsertionX")) InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("Selection")) Selection.sort(a);
//        else if (alg.equals("Bubble")) Bubble.sort(a);
        else if (alg.equals("Shell")) Shell.sort(a);
//        else if (alg.equals("Merge")) Merge.sort(a);
//        else if (alg.equals("MergeX")) MergeX.sort(a);
//        else if (alg.equals("MergeBU")) MergeBU.sort(a);
//        else if (alg.equals("Quick")) Quick.sort(a);
//        else if (alg.equals("Quick3way")) Quick3way.sort(a);
//        else if (alg.equals("QuickX")) QuickX.sort(a);
//        else if (alg.equals("Heap")) Heap.sort(a);
        else if (alg.equals("System")) Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return sw.elapsedTime();
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeRandomInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
//        String alg1 = args[0];
//        String alg2 = args[1];
//        int n = Integer.parseInt(args[2]);
//        int trials = Integer.parseInt(args[3]);
        Stream<String> sorts = Stream.of(
                "Selection",
                "Insertion",
                "Shell",
                "System");
        int n = 10_000;
        int trials = 100;
//        boolean sorted = true;
        boolean sorted = false;
        if (args.length == 5 && sorted) {
            sorts
                    .forEach(alg -> {
                        double time = timeSortedInput(alg, n, trials);
                        StdOut.printf("%s time:%5.3f%n", alg, time);
                    });
        } else {
            sorts
                    .forEach(alg -> {
                        double time = timeRandomInput(alg, n, trials);
                        StdOut.printf("%s time:%5.3f%n", alg, time);
                    });
        }

//        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
//        StdOut.printf(" %.1f times faster than %s\n", time2 / time1, alg2);
//        StdOut.printf("%s time:%5.3f%n", alg1, time1);
//        StdOut.printf("%s time:%5.3f%n", alg2, time2);
    }
}
