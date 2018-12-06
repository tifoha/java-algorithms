package net.tifoha.ch_03;

/**
 * @author Vitalii Sereda
 */

/******************************************************************************
 *  Compilation:  javac FrequencyCounter.java
 *  Execution:    java FrequencyCounter L < input.txt
 *  Dependencies: ST.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/31elementary/tnyTale.txt
 *                https://algs4.cs.princeton.edu/31elementary/tale.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 *
 *  Read in a list of words from standard input and print out
 *  the most frequently occurring word that has length greater than
 *  a given threshold.
 *
 *  % java FrequencyCounter 1 < tinyTale.txt
 *  it 10
 *
 *  % java FrequencyCounter 8 < tale.txt
 *  business 122
 *
 *  % java FrequencyCounter 10 < leipzig1M.txt
 *  government 24763
 *
 *
 ******************************************************************************/

import net.tifoha.ch_03._01.BinarySearchST;
import net.tifoha.ch_03._01.SequentialSearchST;
import net.tifoha.utils.StdIn;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * The {@code FrequencyCounter} class provides a client for
 * reading in a sequence of words and printing a word (exceeding
 * a given length) that occurs most frequently. It is useful as
 * a test client for various symbol table implementations.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SearchCompare {

    // Do not instantiate.
    private SearchCompare() {
    }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int minlen = 2;
        Collection<Supplier<SearchTable<String, Integer>>> tables = Arrays.asList(
                ST::new,
                BinarySearchST::new,
                SequentialSearchST::new
        );
        for (Supplier<SearchTable<String, Integer>> supplier : tables) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1; i++) {
                SearchTable<String, Integer> st = supplier.get();
                processTable(minlen, st);
            }
            Duration duration = Duration.ofMillis(System.currentTimeMillis() - start);
            System.out.printf("%s: %s%n", supplier.get().getClass().getSimpleName(), duration);
        }
    }

    private static void processTable(int minlen, SearchTable<String, Integer> st) {
        // compute frequency counts
        int distinct = 0, words = 0;
        StdIn.setFile("src\\main\\resources\\mobydick.txt");
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

//        StdOut.println(max + " " + st.get(max));
//        StdOut.println("distinct = " + distinct);
//        StdOut.println("words    = " + words);
    }
}
