package net.tifoha.ch_02._04.task;

/**
 * @author Vitalii Sereda
 */

import net.tifoha.utils.StdOut;

import java.util.PriorityQueue;
import java.util.Queue;

/******************************************************************************
 *  Compilation:  javac Taxicab.java
 *  Execution:    java Taxicab n
 *  Dependencies: StdOut.java MinPQ.java
 *
 *  Find all nontrivial integer solutions to a^3 + b^3 = c^3 + d^3 where
 *  a, b, c, and d are between 0 and n. By nontrivial, we mean
 *  a <= b, c <= d, and a < c.
 *
 *  % java Taxicab 11
 *
 *  % java Taxicab 12
 *  1729 = 1^3 + 12^3 = 9^3 + 10^3
 *
 *  % java Taxicab 1000
 *  1729 = 1^3 + 12^3 = 9^3 + 10^3
 *  4104 = 2^3 + 16^3 = 9^3 + 15^3
 *  13832 = 2^3 + 24^3 = 18^3 + 20^3
 *  20683 = 10^3 + 27^3 = 19^3 + 24^3
 *  32832 = 4^3 + 32^3 = 18^3 + 30^3
 *  ...
 *  87539319 = 167^3 + 436^3 = 228^3 + 423^3 = 255^3 + 414^3
 *  ...
 *  1477354411 = 802^3 + 987^3 = 883^3 + 924^3
 *
 ******************************************************************************/

public class Taxicab implements Comparable<Taxicab> {
    private final int i;
    private final int j;
    private final long sum;   // i^3 + j^3, cached to avoid recomputation

    // create a new tuple (i, j, i^3 + j^3)
    public Taxicab(int i, int j) {
        this.sum = (long) i*i*i + (long) j*j*j;
        this.i = i;
        this.j = j;
    }

    // compare by i^3 + j^3, breaking ties by i
    public int compareTo(Taxicab that) {
        if      (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else if (this.i < that.i)     return -1;
        else if (this.i > that.i)     return +1;
        else                          return  0;
    }

    public String toString() {
        return i + "^3 + " + j + "^3";
    }


    public static void main(String[] args) {

        // find a^3 + b^3 = c^3 + d^3, where a, b, c, d <= n
        int n = 100;
//        int n = Integer.parseInt(args[0]);

        // initialize priority queue
        Queue<Taxicab> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            pq.offer(new Taxicab(i, i));
        }

        // enumerate sums in ascending order, look for repeated sums
        int run = 1;
        Taxicab prev = new Taxicab(0, 0);   // sentinel
        while (!pq.isEmpty()) {
            Taxicab curr = pq.poll();

            // current sum is same as previous sum
            if (prev.sum == curr.sum) {
                run++;
                if (run == 2) StdOut.print(prev.sum + " = " + prev);
                StdOut.print(" = " + curr);
            }
            else {
                if (run > 1) StdOut.println();
                run = 1;
            }
            prev = curr;

            if (curr.j < n) pq.offer(new Taxicab(curr.i, curr.j + 1));
        }
        if (run > 1) StdOut.println();
    }

}